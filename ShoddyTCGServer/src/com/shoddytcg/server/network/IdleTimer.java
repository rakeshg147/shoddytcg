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

/**
 * A class which kicks players if they've been idle for too long. 
 * Hope to not use it. 
 */
public class IdleTimer implements Runnable {
	private boolean m_isRunning = false;
	
	public void run() {
		while(m_isRunning) {
			/*
			 * Loop through all players and check for idling
			 * If they've idled, disconnect them
			 */
			TcpProtocolHandler.kickIdlePlayers();
			try {
				Thread.sleep(30000);
			} catch (Exception e) {}
			
		}
	}
	
	/**
	 * Starts the idle timer
	 */
	public void start() {
		m_isRunning = true;
		new Thread(this).start();
	}
	
	/**
	 * Stops the idle timer
	 */
	public void stop() {
		m_isRunning = false;
	}
}
