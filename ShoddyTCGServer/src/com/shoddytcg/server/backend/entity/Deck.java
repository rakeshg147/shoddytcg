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

import java.util.ArrayList;

/**
 * Represents a deck
 * @author nushio
 *
 */
public class Deck {
	
	private int id;
	private String name;
	private ArrayList<Card> cards;
	
	public Deck() {
		
	}
	
	public Deck(String name) {
		this.name = name;
		cards = new ArrayList<Card>();
	}
	/**
	 * Returns the deck name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the deck Name
	 * @param b
	 */
	public void setName(String Name) {
		name = Name;
	}

	/**
	 * Returns the id of this deck
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Sets the deck Id
	 * @param id
	 */
	public void setId(int Id) {
		id = Id;
	}
	
	/**
	 * Returns the deck's cards
	 */
	public ArrayList<Card> getCards() {
		return cards;
	}
	
	/**
	 * Sets the Deck's cards
	 * @param id
	 */
	public void setCards(ArrayList<Card> mCards) {
		cards = mCards;
	}
	
	/**
	 * Adds a Card to the player's Deck. 
	 * @param id
	 */
	public void addCard(Card mCard){
		cards.add(mCard);
	}
	
}
