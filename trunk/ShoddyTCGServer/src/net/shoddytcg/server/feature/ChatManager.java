package net.shoddytcg.server.feature;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import net.shoddytcg.server.network.TcpProtocolHandler;
import net.shoddytcg.server.network.message.ChatMessage;
import net.shoddytcg.server.network.message.ChatMessage.ChatMessageType;

import org.apache.mina.core.session.IoSession;

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
	 * @param mapX
	 * @param mapY
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
