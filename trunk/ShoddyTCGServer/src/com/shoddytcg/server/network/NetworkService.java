/**
 *  Copyright (C) 2009 ShoddyTCG Developer Team
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as
 *  published by the Free Software Foundation, either version 3 of the
 *  License, or (at your option) any later version.
 *  
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 *  
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.shoddytcg.server.network;

import java.net.InetSocketAddress;


import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.shoddytcg.server.GameServer;
import com.shoddytcg.server.feature.ChatManager;
import com.shoddytcg.server.network.codec.PokenetCodecFactory;

/**
 * Handles all networking
 * @author shadowkanji
 */
public class NetworkService {
	private TcpProtocolHandler m_tcpProtocolHandler;
	private LoginManager m_loginManager;
	private LogoutManager m_logoutManager;
	private IoAcceptor m_tcpAcceptor;
	private ChatManager [] m_chatManager;
	
	/**
	 * Default constructor
	 */
	public NetworkService() {
		m_logoutManager = new LogoutManager();
		m_loginManager = new LoginManager(m_logoutManager);
		m_chatManager = new ChatManager[3];
		m_tcpProtocolHandler = new TcpProtocolHandler(m_loginManager, m_logoutManager);
	}
	
	/**
	 * Returns the login manager
	 * @return
	 */
	public LoginManager getLoginManager() {
		return m_loginManager;
	}
	
	/**
	 * Returns the logout manager
	 * @return
	 */
	public LogoutManager getLogoutManager() {
		return m_logoutManager;
	}
	
	/**
	 * Returns the chat manager with the least amount of processing to be done
	 * @return
	 */
	public ChatManager getChatManager() {
		int smallest = 0;
		for(int i = 1; i < m_chatManager.length; i++) {
			if(m_chatManager[i].getProcessingLoad() < m_chatManager[smallest].getProcessingLoad())
				smallest = i;
		}
		return m_chatManager[smallest];
	}
	
	/**
	 * Returns the connection manager (packet handler)
	 * @return
	 */
	public TcpProtocolHandler getConnectionManager() {
		return m_tcpProtocolHandler;
	}
	
	/**
	 * Start this network service by starting all threads.
	 */
	public void start() {
		//Load MySQL JDBC Driver
        try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*
		 * Ensure anyone still marked as logged in on this server
		 * is unmarked
		 */
		MySqlManager m = new MySqlManager();
		if(m.connect(GameServer.getDatabaseHost(), 
				GameServer.getDatabaseUsername(),
				GameServer.getDatabasePassword())) {
			m.selectDatabase(GameServer.getDatabaseName());
			m.close();
		}
		m = null;
		/*
		 * Start the login/logout managers
		 */
		m_logoutManager.start();
		m_loginManager.start();
		/*
		 * Start the chat managers
		 */
		for(int i = 0; i < m_chatManager.length; i++) {
			m_chatManager[i] = new ChatManager();
			m_chatManager[i].start();
		}
		/*
		 * Bind the TCP port
		 */
		m_tcpAcceptor = new NioSocketAcceptor();
		m_tcpAcceptor.getFilterChain().addLast("logger", new LoggingFilter());
		m_tcpAcceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new PokenetCodecFactory()));
		m_tcpAcceptor.setHandler(m_tcpProtocolHandler);
		m_tcpAcceptor.getSessionConfig().setIdleTime(IdleStatus.READER_IDLE, 900000);
		try {
			m_tcpAcceptor.bind(new InetSocketAddress(7002)); 
			System.out.println("INFO: TCP acceptor started.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("WARNING: Port 7002 busy.");
			System.exit(1);
			return;
		}
		System.out.println("INFO: Network Service started.");
	}
	
	/**
	 * Stop this network service by stopping all threads.
	 */
	public void stop() {
		//Stop all threads (do not use thread.stop() )
		//Unbind network address
		for(int i = 0; i < m_chatManager.length; i++)
			m_chatManager[i].stop();
		m_tcpAcceptor.unbind();
		m_tcpProtocolHandler.logoutAll();
	}
}
