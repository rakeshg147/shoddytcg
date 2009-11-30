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
package com.shoddytcg.server.network.message;

/**
 * A chat message
 * @author shadowkanji
 *
 */
public class ChatMessage extends ShoddyMessage {
	public enum ChatMessageType { LOCAL, PRIVATE, NPC }
	
	/**
	 * Constructor
	 * @param c
	 * @param message
	 */
	public ChatMessage(ChatMessageType c, String message) {
		switch(c) {
		case LOCAL:
			m_message = "Cl" + message;
			break;
		case PRIVATE:
			m_message = "Cp" + message;
			break;
		case NPC:
			m_message = "Cn" + message;
			break;
		}
	}
}
