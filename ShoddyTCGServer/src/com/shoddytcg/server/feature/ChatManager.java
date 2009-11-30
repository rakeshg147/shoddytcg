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
package com.shoddytcg.server.feature;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;


import org.apache.mina.core.session.IoSession;

import com.shoddytcg.server.network.TcpProtocolHandler;
import com.shoddytcg.server.network.message.ChatMessage;
import com.shoddytcg.server.network.message.ChatMessage.ChatMessageType;

/**
 * Handles chat messages sent by players
 * @author shadowkanji
 *
 */
public class ChatManager implements Runnable {
	private Thread m_thread;
	@SuppressWarnings("unused")
	private boolean m_isRunning;
	/*
	 * Local chat queue
	 * [Message, x, y]
	 */
	private Queue<Object []> m_localQueue;
	/*
	 * Private chat queue
	 * [session, sender, message]
	 */
	private Queue<Object []> m_privateQueue;
	
	/**
	 * Default Constructor
	 */
	public ChatManager() {
		m_thread = new Thread(this);
		m_localQueue = new ConcurrentLinkedQueue<Object []>();
		m_privateQueue = new ConcurrentLinkedQueue<Object []>();
	}
	
	/**
	 * Returns how many messages are queued in this chat manager
	 * @return
	 */
	public int getProcessingLoad() {
		return m_localQueue.size() + m_privateQueue.size();
	}
	
	/**
	 * Queues a local chat message
	 * @param message
	 */
	public void queueLocalChatMessage(String message) {
		m_localQueue.add(new Object[]{message});
	}
	
	/**
	 * Queues a private chat message
	 * @param message
	 * @param receiver
	 * @param sender
	 */
	public void queuePrivateMessage(String message, IoSession receiver, String sender) {
		m_privateQueue.add(new Object[]{receiver, sender, message});
	}
	
	/**
	 * Called by m_thread.start()
	 */
	public void run() {
		Object [] o;
		IoSession s;
		while(true) {
			//Send next local chat message
			if(m_localQueue.peek() != null) {
				o = m_localQueue.poll();
			}
			//Send next private chat message
			if(m_privateQueue.peek() != null) {
				o = m_privateQueue.poll();
				s = (IoSession) o[0]; 
				if(s.isConnected() && !s.isClosing())
					TcpProtocolHandler.writeMessage(s, new ChatMessage(
							ChatMessageType.PRIVATE, ((String) o[1]) + "," + ((String) o[2])));
			}
			try {
				Thread.sleep(250);
			} catch (Exception e) {}
		}
	}
	
	/**
	 * Start this chat manager
	 */
	public void start() {
		m_isRunning = true;
		m_thread.start();
	}
	
	/**
	 * Stop this chat manager
	 */
	public void stop() {
		m_isRunning = false;
	}

}
