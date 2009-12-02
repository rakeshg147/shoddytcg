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
package com.shoddytcg.client.backend.entity;

import java.util.ArrayList;

/**
 * Represents a card
 * @author nushio
 *
 */
public class Card {
	public enum Color { GRASS, FIRE, WATER, LIGHTNING, PSYCHIC, FIGHTING, COLORLESS, DARK, METAL}
	
	private String id;
	private int set;
	private String uniqueName;
	private String name;
	private CardType cardType;
	private String rarity;
	private String illustrator;
	
	private ArrayList<Card> attachments;
	private boolean faceUp = false; 
	
	public Card() {
	}
	
	public Card(String cardId) {
		id = cardId;
	}
	
	/**
	 * @return the rarity
	 */
	public String getRarity() {
		return rarity;
	}

	/**
	 * @param rarity the rarity to set
	 */
	public void setRarity(String rarity) {
		this.rarity = rarity;
	}

	/**
	 * @return the illustrator
	 */
	public String getIllustrator() {
		return illustrator;
	}

	/**
	 * @param illustrator the illustrator to set
	 */
	public void setIllustrator(String illustrator) {
		this.illustrator = illustrator;
	}

	/**
	 * @return the uniqueName
	 */
	public String getUniqueName() {
		return uniqueName;
	}

	/**
	 * @param uniqueName the uniqueName to set
	 */
	public void setUniqueName(String uniqueName) {
		this.uniqueName = uniqueName;
	}

	/**
	 * @return the cardType
	 */
	public CardType getCardType() {
		return cardType;
	}
	
//	public String getCardTypeName() {
//		
//		if(cardType.isPokemon())
//			return "pokemon";
//		else if(cardType.)
//			return "Unown";
//	}
	/**
	 * @param cardType the cardType to set
	 */
	public void setCardType(CardType cardType) {
		this.cardType = cardType;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the set
	 */
	public int getSet() {
		return set;
	}

	/**
	 * @param set the set to set
	 */
	public void setSet(int set) {
		this.set = set;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the attachments
	 */
	public ArrayList<Card> getAttachments() {
		return attachments;
	}

	/**
	 * @param attachments the attachments to set
	 */
	public void setAttachments(ArrayList<Card> attachments) {
		this.attachments = attachments;
	}

	/**
	 * @return the faceUp
	 */
	public boolean isFaceUp() {
		return faceUp;
	}

	/**
	 * @param faceUp the faceUp to set
	 */
	public void setFaceUp(boolean faceUp) {
		this.faceUp = faceUp;
	}
}
