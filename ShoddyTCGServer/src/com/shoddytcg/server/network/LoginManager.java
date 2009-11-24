package com.shoddytcg.server.network;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;


import org.apache.mina.core.session.IoSession;

import com.shoddytcg.server.GameServer;
import com.shoddytcg.server.backend.entity.PlayerChar;
import com.shoddytcg.server.backend.entity.PlayerChar.Language;

/**
 * Handles logging players in
 * @author shadowkanji
 *
 */
public class LoginManager implements Runnable {
	private Queue<Object []> m_loginQueue;
	private Thread m_thread;
	private boolean m_isRunning;
	private MySqlManager m_database;
	
	private Queue<Object []> m_passChangeQueue;
	
	/**
	 * Default constructor. Requires a logout manager to be passed in so the server
	 * can check if player's data is not being saved as they are logging in.
	 * @param manager
	 */
	public LoginManager(LogoutManager manager) {
		m_database = new MySqlManager();
		m_loginQueue = new LinkedList<Object []>();
		m_passChangeQueue = new LinkedList<Object []>();
		m_thread = null;
	}
	
	/**
	 * Attempts to login a player. Upon success, it sends a packet to the player to inform them they are logged in.
	 * @param session
	 * @param l
	 * @param username
	 * @param password
	 */
	private void attemptLogin(IoSession session, char l, String username, String password, boolean force) {
		try {
			//Check if we haven't reach the player limit
			if(TcpProtocolHandler.getPlayerCount() >= GameServer.getMaxPlayers()) {
				session.write("l2");
				return;
			}
			//First connect to the database
			m_database = new MySqlManager();
			if(!m_database.connect(GameServer.getDatabaseHost(), GameServer.getDatabaseUsername(), GameServer.getDatabasePassword())) {
				session.write("l1");
				return;
			}
			//Select the database
			if(!m_database.selectDatabase(GameServer.getDatabaseName())) {
				session.write("l1");
				return;
			}
			//Find the member's information
			ResultSet result = m_database.query("SELECT * FROM users WHERE username='" + MySqlManager.parseSQL(username) + "'");
			if(!result.first()){
				//Member doesn't exist, say user or pass wrong. We don't want someone to guess usernames. 
				session.write("le");
				return;
			}
			//Check if the password is correct
			if(result.getString("password").compareTo(password) == 0) {
				//Now check if they are logged in anywhere else	
				this.login(username, l, session, result);
			} else {
				//Password is wrong, say so.
				session.write("le");
				return;
			}
			m_database.close();
		} catch (Exception e) {
			e.printStackTrace();
			session.write("lu");
			/*
			 * Something went wrong so make sure the player is registered as logged out
			 */
			m_database.close();
		}
	}
	
	/**
	 * Places a player in the login queue
	 * @param session
	 * @param username
	 * @param password
	 * @param forceLogin - true if player wants to force login
	 */
	public void queuePlayer(IoSession session, String username, String password, boolean forceLogin) {
		if(m_thread == null || !m_thread.isAlive()) {
			start();
		}
		m_loginQueue.offer(new Object[] {session, username, password, String.valueOf(forceLogin)});
	}
	
	/**
	 * Places a player in the queue to update their password
	 * @param session
	 * @param username
	 * @param newPassword
	 * @param oldPassword
	 */
	public void queuePasswordChange(IoSession session, String username, String newPassword, String oldPassword) {
		if(m_thread == null || !m_thread.isAlive()) {
			start();
		}
		m_passChangeQueue.offer(new Object[] {session, username, newPassword, oldPassword});
	}

	/**
	 * Called by Thread.start()
	 */
	public void run() {
		Object [] o;
		IoSession session;
		String username;
		String password;
		String newPassword;
		char l;
		while(m_isRunning) {
			synchronized(m_loginQueue) {
				try {
					if(m_loginQueue.peek() != null) {
						o = m_loginQueue.poll();
						session = (IoSession) o[0];
						l = ((String) o[1]).charAt(0);
						username = ((String) o[1]).substring(1);
						password = (String) o[2];
						boolean force = Boolean.valueOf((String) o[3]);
						this.attemptLogin(session, l, username, password, force);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			try {
				Thread.sleep(500);
			} catch (Exception e) {}
			
			synchronized(m_passChangeQueue) {
				try {
					if(m_passChangeQueue.peek() != null) {
						o = m_passChangeQueue.poll();
						session = (IoSession) o[0];
						username = (String) o[1];
						newPassword = (String) o[2];
						password = (String) o[3];
						this.changePass(username, newPassword, password, session);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			try {
				Thread.sleep(500);
			} catch (Exception e) {}
		}
		m_thread = null;
	}
	
	/**
	 * Starts the login manager
	 */
	public void start() {
		if(m_thread == null || !m_thread.isAlive()) {
			m_thread = new Thread(this);
			m_isRunning = true;
			m_thread.start();
		}
	}
	
	/**
	 * Stops the login manager
	 */
	public void stop() {
		m_isRunning = false;
	}
	
	/**
	 * Changes the password of a player
	 * @param username
	 * @param newPassword
	 * @param oldPassword
	 * @param session
	 */
	private void changePass(String username, String newPassword, String oldPassword, IoSession session) {
		m_database = new MySqlManager();
	
		if(m_database.connect(GameServer.getDatabaseHost(), GameServer.getDatabaseUsername(), GameServer.getDatabasePassword())) {
			if(m_database.selectDatabase(GameServer.getDatabaseName())) {
				ResultSet result = m_database.query("SELECT * FROM users WHERE username='" + MySqlManager.parseSQL(username) + "'");
				try {
					if(result.first()){
						// if we got a result, compare their old password to the one we have stored for them
						if(result.getString("password").compareTo(oldPassword) == 0) {
							// old password matches the one on file, therefore they got their old password correct, so it can be changed to their new one
							m_database.query("UPDATE users SET password='" + MySqlManager.parseSQL(newPassword) + "' WHERE username='" + MySqlManager.parseSQL(username) + "'");
							// tell them their password was changed successfully
							session.write("ps");
							return;
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				m_database.close();
			}
		}
		// tell them we failed to change their password
		session.write("pe");
	}
	
	/**
	 * Logs in a player
	 * @param username
	 * @param language
	 * @param session
	 * @param result
	 */
	private void login(String username, char language, IoSession session, ResultSet result) {
		//They are not logged in elsewhere, set the current login to the current server
		long time = System.currentTimeMillis();
		/*
		 * Attempt to log the player in
		 */
		PlayerChar p = getPlayerObject(result);
		p.setLastLoginTime(time);
		p.setTcpSession(session);
		p.setLanguage(Language.values()[Integer.parseInt(String.valueOf(language))]);
		session.setAttribute("player", p);
		/*
		 * Send success packet to player, set their map and add them to a movement service
		 */
		this.initialiseClient(p, session);
		/*
		 * Add them to the list of players
		 */
		TcpProtocolHandler.addPlayer(p);
		GameServer.getInstance().updatePlayerCount();
		System.out.println("INFO: " + username + " logged in.");
	}
	
	/**
	 * Sends initial information to the client
	 * @param p
	 * @param session
	 */
	private void initialiseClient(PlayerChar p, IoSession session) {
		session.write("ls" + p.getName());

	}

	/**
	 * Returns a playerchar object from a resultset of player data
	 * @param data
	 * @return
	 */
	private PlayerChar getPlayerObject(ResultSet result) {
		try {
			PlayerChar p = new PlayerChar();
			
			p.setName(result.getString("username"));
			
			switch(result.getInt("status")){
				case 0:
					p.setMuted(true);
					p.setAdminLevel(0);
					break;
				case 1:
					p.setMuted(true);
					p.setAdminLevel(0);
					break;
				case 2:
					p.setMuted(false);
					p.setAdminLevel(1);
					break;
				case 3:
					p.setMuted(false);
					p.setAdminLevel(2);
					break;
			}
			return p;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
