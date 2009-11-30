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

import com.shoddytcg.server.backend.entity.PlayerChar.RequestType;

/**
 * A request packet
 * @author shadowkanji
 *
 */
public class RequestMessage extends ShoddyMessage {
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
