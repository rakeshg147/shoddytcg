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
package com.shoddytcg.server.backend.entity;

import java.util.HashMap;

/**
 * Represents a card
 * @author nushio
 *
 */
public class CardSet {
	
	private String code;
	private HashMap<String, Card> cardset;
	
	public CardSet() {
		cardset = new HashMap<String,Card>();
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the cardset
	 */
	public HashMap<String, Card> getCardset() {
		return cardset;
	}

	/**
	 * @param cardset the cardset to set
	 */
	public void setCardset(HashMap<String, Card> cardset) {
		this.cardset = cardset;
	}
	/**
	 * @param code, card. Add cards to the cardset. 
	 */
	public void addCard(String code, Card card) {
		this.cardset.put(code,card);
	}
}
