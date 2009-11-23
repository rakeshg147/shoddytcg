package net.shoddytcg.server;

import net.shoddytcg.server.network.IdleTimer;
import net.shoddytcg.server.network.NetworkService;

/**
 * Handles all services on the game server
 * @author shadowkanji
 *
 */
public class ServiceManager {
	private NetworkService m_networkService;
	private IdleTimer m_idleTimer;
	
	/**
	 * Default constructor
	 */
	public ServiceManager() {
		/*
		 * Initialize all the services
		 */
		m_networkService = new NetworkService();
		m_idleTimer = new IdleTimer();
	}
	
	/**
	 * Returns the network service
	 * @return
	 */
	public NetworkService getNetworkService() {
		return m_networkService;
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
