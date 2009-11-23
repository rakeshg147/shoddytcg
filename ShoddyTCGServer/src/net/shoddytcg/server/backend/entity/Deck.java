package net.shoddytcg.server.backend.entity;

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
