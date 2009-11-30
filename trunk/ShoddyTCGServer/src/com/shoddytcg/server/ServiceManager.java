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
package com.shoddytcg.server;

import java.util.HashMap;

import com.shoddytcg.server.backend.entity.CardReader;
import com.shoddytcg.server.backend.entity.CardSet;
import com.shoddytcg.server.network.IdleTimer;
import com.shoddytcg.server.network.NetworkService;

/**
 * Handles all services on the game server
 * @author shadowkanji
 *
 */
public class ServiceManager {
	private NetworkService m_networkService;
	private IdleTimer m_idleTimer;
	private HashMap<String,CardSet> cardsets;
	
	/**
	 * Default constructor
	 */
	public ServiceManager() {
		/*
		 * Initialize all the services
		 */
		m_networkService = new NetworkService();
		m_idleTimer = new IdleTimer();
		cardsets = new CardReader().getCardsets();
	}
	
	/**
	 * Returns the network service
	 * @return
	 */
	public NetworkService getNetworkService() {
		return m_networkService;
	}
	
	/**
	 * Returns the card sets
	 * @return
	 */
	public HashMap<String, CardSet> getCardSets() {
		return cardsets;
	}	
	/**
	 * Returns a particular CardSet
	 * @return
	 */
	public CardSet getCardSet(String code) {
		return cardsets.get(code);
	}
	/**
	 * Starts all services
	 */
	public void start() {
		/*
		 * Start the network service first as it needs to bind the address/port to the game server.
		 * Then start all other services with TimeService last.
		 */
		m_networkService.start();
		m_idleTimer.start();
		System.out.println("INFO: Service Manager startup completed.");
	}
	
	/**
	 * Stops all services
	 */
	public void stop() {
		/*
		 * Stopping services is very delicate and must be done in the following order to avoid
		 * leaving player objects in a non-concurrent state.
		 */
		m_idleTimer.stop();
		m_networkService.stop();
		System.out.println("INFO: Service Manager stopped.");
	}
}
