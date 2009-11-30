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
