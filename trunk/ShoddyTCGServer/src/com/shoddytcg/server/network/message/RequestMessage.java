package com.shoddytcg.server.network.message;

import com.shoddytcg.server.backend.entity.PlayerChar.RequestType;

/**
 * A request packet
 * @author shadowkanji
 *
 */
public class RequestMessage extends PokenetMessage {
	/**
	 * Constructor
	 * @param t
	 * @param playerName
	 */
	public RequestMessage(RequestType t, String playerName) {
		switch(t) {
		case BATTLE:
			m_message = "rb" + playerName;
			break;
		}
	}
}
