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

import java.util.LinkedList;
import java.util.Queue;

import com.shoddytcg.server.GameServer;
import com.shoddytcg.server.backend.entity.PlayerChar;



/**
 * Handles logging players out
 * @author shadowkanji
 *
 */
public class LogoutManager implements Runnable {
	private Queue<PlayerChar> m_logoutQueue;
	private Thread m_thread;
	private boolean m_isRunning;
	private MySqlManager m_database;
	
	/**
	 * Default constructor
	 */
	public LogoutManager() {
		m_database = new MySqlManager();
		m_logoutQueue = new LinkedList<PlayerChar>();
		m_thread = null;
	}
	
	/**
	 * Returns how many players are in the save queue
	 * @return
	 */
	public int getPlayerAmount() {
		return m_logoutQueue.size();
	}
	
	/**
	 * Attempts to logout a player by saving their data. Returns true on success
	 * @param player
	 */
	private boolean attemptLogout(PlayerChar player) {
		TcpProtocolHandler.removePlayer(player);
		GameServer.getInstance().updatePlayerCount();
		m_database = new MySqlManager();
		if(!m_database.connect(GameServer.getDatabaseHost(), GameServer.getDatabaseUsername(), GameServer.getDatabasePassword()))
			return false;
		m_database.selectDatabase(GameServer.getDatabaseName());
		//Store all player information
		if(!savePlayer(player)) {
			m_database.close();
			return false;
		}
		//Finally, store that the player is logged out and close connection
		m_database.close();
		//Close the session fully if its not closed already
		if(player.getTcpSession() != null && player.getTcpSession().isConnected())
			player.getTcpSession().close(true);
		return true;
	}
	
	/**
	 * Queues a player to be logged out
	 * @param player
	 */
	public void queuePlayer(PlayerChar player) {
		if(m_thread == null || !m_thread.isAlive())
			start();
		m_logoutQueue.offer(player);
	}

	/**
	 * Called by m_thread.start()
	 */
	public void run() {
		while(m_isRunning) {
			synchronized(m_logoutQueue) {
				if(m_logoutQueue.peek() != null) {
					PlayerChar p = m_logoutQueue.poll();
					synchronized(p) {
						if(p != null) {
							if(!attemptLogout(p)) {
								m_logoutQueue.add(p);
							} else {
								System.out.println("INFO: " + p.getName() + " logged out.");
								p = null;
							}
						}
					}
				}
			}
			try {
				Thread.sleep(500);
			} catch (Exception e) {}
		}
		m_thread = null;
		System.out.println("INFO: All player data saved successfully.");
	}
	
	/**
	 * Start this logout manager
	 */
	public void start() {
		if(m_thread == null || !m_thread.isAlive()) {
			m_thread = new Thread(this);
			m_isRunning = true;
			m_thread.start();
		}
	}
	
	/**
	 * Stop this logout manager
	 */
	public void stop() {
		//Stop the thread
		m_isRunning = false;
	}
	
	/**
	 * Saves a player object to the database (Updates an existing player)
	 * @param p
	 * @return
	 */
	private boolean savePlayer(PlayerChar p) {
		try {
			/*
			 * First, check if they have logged in somewhere else.
			 * This is useful for when as server loses its internet connection
			 */
//			ResultSet data = m_database.query("SELECT * FROM pn_members WHERE id='" + p.getId() +  "'");
//			data.first();
//			if(data.getLong("lastLoginTime") == p.getLastLoginTime()) {
//				m_database.query("UPDATE pn_members SET " +
//						"muted='" + p.isMuted() + "', " +
//						"WHERE id='" + p.getId() + "'");
//				return true;
//			} else
//				return true;
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
