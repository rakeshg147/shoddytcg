package org.pokenet.server.network;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.Queue;

import org.apache.mina.core.session.IoSession;
import org.pokenet.server.GameServer;

/**
 * Handles registrations
 * @author shadowkanji
 *
 */
public class RegistrationManager implements Runnable {
	private Queue<IoSession> m_queue;
	private Thread m_thread;
	private boolean m_isRunning;
	private MySqlManager m_database;
	
	/**
	 * Constructor
	 */
	public RegistrationManager() {
		m_database = new MySqlManager();
		m_thread = null;
		m_queue = new LinkedList<IoSession>();
	}
	
	/**
	 * Queues a registration
	 * @param session
	 * @param packet
	 */
	public void queueRegistration(IoSession session, String packet) {
		if(m_thread == null || !m_thread.isAlive())
			start();
		if(!m_queue.contains(session)) {
			session.setAttribute("reg", packet);
			m_queue.offer(session);
		}
		session.suspendRead();
		session.suspendWrite();
	}
	
	/**
	 * Registers a new player
	 * @param session
	 */
	public void register(IoSession session) throws Exception {
		if(!session.isConnected() || session.isClosing()) {
			return;
		}
		String [] info = ((String) session.getAttribute("reg")).split(",");
		m_database = new MySqlManager();
		if(m_database.connect(GameServer.getDatabaseHost(), GameServer.getDatabaseUsername(), GameServer.getDatabasePassword())) {
			m_database.selectDatabase(GameServer.getDatabaseName());
			/*
			 * Check if the user exists
			 */
			ResultSet data = m_database.query("SELECT * FROM users WHERE username='" + MySqlManager.parseSQL(info[0]) + "'");
			data.first();
			try {				
				if(data != null && data.getString("username") != null && data.getString("username").equalsIgnoreCase(MySqlManager.parseSQL(info[0]))) {
					session.resumeRead();
					session.resumeWrite();
					session.write("r2");
					return;
				}
			} catch (Exception e) {}
			/*
			 * Check if an account is already registered with the email
			 */
			data = m_database.query("SELECT * FROM users WHERE email='" + MySqlManager.parseSQL(info[2]) + "'");
			data.first();
			try {				
				if(data != null && data.getString("email") != null && data.getString("email").equalsIgnoreCase(MySqlManager.parseSQL(info[2]))) {
					session.resumeRead();
					session.resumeWrite();
					session.write("r5");
					return;
				}
				if(info[2].length() > 52) {
					session.resumeRead();
					session.resumeWrite();
					session.write("r6");
					return;
				}
			} catch (Exception e) {}
			/*
			 * Create the player in the database
			 */
			String badges = "";
			for(int i = 0; i < 50; i++)
				badges = badges + "0";
			m_database.query("INSERT INTO users (username, password, email) VALUE " +
					"('" + MySqlManager.parseSQL(info[0]) + "', '" + MySqlManager.parseSQL(info[1]) + "', '" + MySqlManager.parseSQL(info[2]) + "')");
			/*
			 * Retrieve the player's unique id
			 */
			data = m_database.query("SELECT * FROM users WHERE username='" + MySqlManager.parseSQL(info[0]) + "'");
			data.first();
//			int playerId = data.getInt("id");
			/*
			 * Finish
			 */
			m_database.close();
			
			session.resumeRead();
			session.resumeWrite();
			session.write("rs");
		} else {
			session.resumeRead();
			session.resumeWrite();
			session.write("r1");
		}
	}

	/**
	 * Called by m_thread.start()
	 */
	public void run() {
		IoSession session;
		while(m_isRunning) {
			synchronized(m_queue) {
				if(m_queue.peek() != null) {
					session = m_queue.poll();
					try {
						this.register(session);
					} catch (Exception e) {
						e.printStackTrace();
						session.resumeRead();
						session.resumeWrite();
						session.write("r3");
					}
				}
			}
			try {
				Thread.sleep(250);
			} catch (Exception e) {}
		}
	}
	
	/**
	 * Start the registration manager
	 */
	public void start() {
		if(m_thread == null || !m_thread.isAlive()) {
			m_thread = new Thread(this);
			m_isRunning = true;
			m_thread.start();
		}
	}

	/**
	 * Stop the registration manager
	 */
	public void stop() {
		m_isRunning = false;
	}
}
