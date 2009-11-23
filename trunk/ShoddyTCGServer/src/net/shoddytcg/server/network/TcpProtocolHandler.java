package net.shoddytcg.server.network;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import net.shoddytcg.server.GameServer;
import net.shoddytcg.server.backend.entity.Card;
import net.shoddytcg.server.backend.entity.Deck;
import net.shoddytcg.server.backend.entity.PlayerChar;
import net.shoddytcg.server.backend.entity.PlayerChar.RequestType;
import net.shoddytcg.server.network.message.PokenetMessage;
import net.shoddytcg.server.network.message.RequestMessage;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

/**
 * Handles packets received from players over TCP
 * @author shadowkanji
 * @author Nushio
 *
 */
public class TcpProtocolHandler extends IoHandlerAdapter {
	private static HashMap<String, PlayerChar> m_players;
	private LoginManager m_loginManager;
	private LogoutManager m_logoutManager;
	private RegistrationManager m_regManager;

	/**
	 * Constructor
	 * @param login
	 * @param logout
	 */
	public TcpProtocolHandler(LoginManager login, LogoutManager logout) {
		m_loginManager = login;
		m_logoutManager = logout;
		m_regManager = new RegistrationManager();
		m_regManager.start();
	}

	static {
		m_players = new HashMap<String, PlayerChar>();
	}

	/**
	 * Handles any exceptions involving a player's session
	 */
	public void exceptionCaught(IoSession session, Throwable t)
	throws Exception {
		/*
		 * Attempt to disconnect and logout the player (save their data)
		 */
		try {
			PlayerChar p = (PlayerChar) session.getAttribute("player");
			if(p != null) {
				if (p.isBattling())
					p.lostBattle();
				m_logoutManager.queuePlayer(p);
				m_players.remove(p);
			}
			if(!(t instanceof IOException) || session.isConnected() || !session.isClosing())
				session.close(true);
		} catch (Exception e) {
			e.printStackTrace();
			if(!(t instanceof IOException) || session.isConnected() || !session.isClosing())
				session.close(true);
		}
		t.printStackTrace();
	}

	/**
	 * Once the server receives a packet from the client, this method is run.
	 * @param IoSession session - A client session
	 * @param Object msg - The packet received from the client
	 */
	public void messageReceived(IoSession session, Object msg) throws Exception {
		String message = (String) msg;
		String[] details;
		System.out.println(message);
		if(session.getAttribute("player") == null) {
			/*
			 * The player hasn't been logged in, only allow login and registration packets
			 */
			switch(message.charAt(0)) {
			case 'l':
				//Login packet
				details = message.substring(1).split(",");
				m_loginManager.queuePlayer(session, details[0], details[1], false);
				break;
			case 'r':
				//Registration packet
				m_regManager.queueRegistration(session, message.substring(1));
				break;
			case 'c':
				//Change password packet
				details = message.substring(1).split(",");
				m_loginManager.queuePasswordChange(session, details[0], details[1], details[2]);
				break;
			}
		}else {
			/*
			 * Player is logged in, allow interaction with their player object
			 */
			PlayerChar p = (PlayerChar) session.getAttribute("player");
			p.lastPacket = System.currentTimeMillis();
			switch(message.charAt(0)) {
			case 'C':
				//Chat
				switch(message.charAt(1)) {
				case 'l':
					//Local chat
					String mes = message.substring(2);
					if(!p.isMuted())
						GameServer.getServiceManager().getNetworkService().getChatManager().
						queueLocalChatMessage(p.getName() + "," + mes);
					break;
				case 'p':
					//Private chat
					details = message.substring(2).split(",");
					if(m_players.containsKey(details[0])) {
						GameServer.getServiceManager().getNetworkService().getChatManager().
						queuePrivateMessage(details[1], m_players.get(details[0]).getTcpSession(), p.getName());
					}else{
						p.getTcpSession().write(details[0]+" is offline.");
					}
					break;
				}
				break;
			case 'r':
				String player = message.substring(2);
				//A request was sent
				switch(message.charAt(1)) {
				case 'b':
					//Battle Request rbUSERNAME
					if(m_players.containsKey(player)) {
						TcpProtocolHandler.writeMessage(m_players.get(player).getTcpSession(), 
								new RequestMessage(RequestType.BATTLE, p.getName()));
						p.addRequest(player, RequestType.BATTLE);
					}
					break;
				case 'a':
					//Request accepted raUSERNAME
					if(m_players.containsKey(player)) {
						m_players.get(player).requestAccepted(p.getName());
					}
					break;
				case 'c':
					//Request declined rcUSERNAME
					if(m_players.containsKey(player)) {
						m_players.get(player).removeRequest(p.getName());
					}
					break;
				case 'e':
					//Deny all requests
					p.clearRequests();
					break;
				}
				break;
			case 'D': 
			//D is for Deck. 
				switch(message.charAt(1)) {
				case 'n':
					//Register New Deck
					String mes = message.substring(2);
					String[] cards = mes.split(",");
					if(cards.length>1){
						Deck newDeck = new Deck(cards[0]);
						for(int i = 1; i<cards.length;i++){
							newDeck.addCard(new Card(cards[i].replace(" ","")));
						}
						p.addDeck(newDeck);
						p.getTcpSession().write("Dns1"); //Error, could not create deck. 
					}else{
						p.getTcpSession().write("Dne"); //Error, could not create deck. 
					}
					break;
				case 'd':
					//Display Deck
					String deck = message.substring(2);
					if(!deck.equals(null)&&!deck.equals("")){
						if(p.getDecks()!=null && p.getDecks().size()>0){
							for(int i = 0; i<p.getDecks().size();i++){
								if((p.getDecks().get(i).getName()+"").equals(deck)){
									if(p.getDecks().get(i).getCards()!=null && p.getDecks().get(i).getCards().size()>0){
										String deckContents = "";
										for (int j = 0; j<p.getDecks().get(i).getCards().size();j++){
											deckContents+=","+p.getDecks().get(i).getCards().get(j).getId();
										}
										deckContents+=";";
										p.getTcpSession().write("Ddf"+p.getDecks().get(i).getId()+","+p.getDecks().get(i).getName()+""+deckContents); //Deck registered succesfully.
									}else{
										p.getTcpSession().write("Ddf"+p.getDecks().get(i).getId()+","+p.getDecks().get(i).getName()+","+";"); //Deck registered, but its got no cards. 
									}
								}
							}
						}else{
							p.getTcpSession().write("Dde"); //Error, that deck is not registered. 
						}
					}else
						p.getTcpSession().write("Dde"); //Error, that deck is not registered. 
					break;
				}
			break;
			}
		}
	}

	/**
	 * When a user disconnects voluntarily, this method is called
	 */
	@Override
	public void sessionClosed(IoSession session) throws Exception {
		/*
		 * Attempt to save the player's data
		 */
		try {
			PlayerChar p = (PlayerChar) session.getAttribute("player");
			if(p != null) {
				if(p.isBattling()) {
					/* If in PvP battle, the player loses */
					p.lostBattle();
				}
				m_logoutManager.queuePlayer(p);
				m_players.remove(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Logs out all players and stops login/logout/registration managers
	 */
	public void logoutAll() {
		m_regManager.stop();
		m_loginManager.stop();
		/*
		 * Queue all players to be saved
		 */
		Iterator<PlayerChar> it = m_players.values().iterator();
		PlayerChar p;
		while(it.hasNext()) {
			p = it.next();
			m_logoutManager.queuePlayer(p);
		}
		/*
		 * Since the method is called during a server shutdown, wait for all players to be logged out
		 */
		while(m_logoutManager.getPlayerAmount() > 0);
		m_logoutManager.stop();
	}

	/**
	 * Adds a player to the player list
	 * @param p
	 */
	public static void addPlayer(PlayerChar p) {
		synchronized(m_players) {
			m_players.put(p.getName(), p);
		}
	}

	/**
	 * Removes a player from the player list
	 * @param p
	 */
	public static void removePlayer(PlayerChar p) {
		synchronized(m_players) {
			m_players.remove(p.getName());
		}
	}

	/**
	 * Returns a player
	 * @param username
	 * @return
	 */
	public static PlayerChar getPlayer(String username) {
		synchronized(m_players) {
			return m_players.get(username);
		}
	}

	/**
	 * Returns true if the player list contains a player
	 * @param username
	 * @return
	 */
	public static boolean containsPlayer(String username) {
		synchronized(m_players) {
			return m_players.containsKey(username);
		}
	}

	/**
	 * Kicks idle players
	 */
	public static void kickIdlePlayers() {
		synchronized(m_players) {
			for(PlayerChar p : m_players.values()) {
				if(System.currentTimeMillis() - p.lastPacket >= 900000)
					p.forceLogout();
			}
		}
	}

	/**
	 * Returns how many players are logged in
	 * @return
	 */
	public static int getPlayerCount() {
		synchronized(m_players) {
			return m_players.keySet().size();
		}
	}

	/**
	 * Writes the message to the session
	 * @param session
	 * @param m
	 */
	public static void writeMessage(IoSession session, PokenetMessage m) {
		session.write(m.getMessage());
	}
}
