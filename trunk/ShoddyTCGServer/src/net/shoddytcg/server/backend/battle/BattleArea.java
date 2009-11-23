package net.shoddytcg.server.backend.battle;

import java.util.ArrayList;

import net.shoddytcg.server.backend.entity.Card;
import net.shoddytcg.server.backend.entity.PlayerChar;

/**
 * @author Nushio
 *
 */
public class BattleArea {

	private PlayerChar player;
	private Card activePoke;
	private ArrayList<Card> bench;
	private ArrayList<Card> prizes;
	private ArrayList<Card> hand;
	private ArrayList<Card> deck;
	private ArrayList<Card> discard;
	private Card stadium;
	private Card supporter;
	/**
	 * @param player1
	 */
	public BattleArea(PlayerChar player) {
		this.player = player;
	}
	/**
	 * @return the player
	 */
	public PlayerChar getPlayer() {
		return player;
	}
	/**
	 * @param player the player to set
	 */
	public void setPlayer(PlayerChar player) {
		this.player = player;
	}
	/**
	 * @return the bench
	 */
	public ArrayList<Card> getBench() {
		return bench;
	}
	/**
	 * @param bench the bench to set
	 */
	public void setBench(ArrayList<Card> bench) {
		this.bench = bench;
	}
	/**
	 * @return the active
	 */
	public Card getActivePokemon() {
		return activePoke;
	}
	/**
	 * @param active the active to set
	 */
	public void setActivePokemon(Card active) {
		this.activePoke = active;
	}
	/**
	 * @return the prizes
	 */
	public ArrayList<Card> getPrizes() {
		return prizes;
	}
	/**
	 * @param prizes the prizes to set
	 */
	public void setPrizes(ArrayList<Card> prizes) {
		this.prizes = prizes;
	}
	/**
	 * @return the hand
	 */
	public ArrayList<Card> getHand() {
		return hand;
	}
	/**
	 * @param hand the hand to set
	 */
	public void setHand(ArrayList<Card> hand) {
		this.hand = hand;
	}
	/**
	 * @return the deck
	 */
	public ArrayList<Card> getDeck() {
		return deck;
	}
	/**
	 * @param deck the deck to set
	 */
	public void setDeck(ArrayList<Card> deck) {
		this.deck = deck;
	}
	/**
	 * @return the discard
	 */
	public ArrayList<Card> getDiscard() {
		return discard;
	}
	/**
	 * @param discard the discard to set
	 */
	public void setDiscard(ArrayList<Card> discard) {
		this.discard = discard;
	}
	/**
	 * @return the stadium
	 */
	public Card getStadium() {
		return stadium;
	}
	/**
	 * @param stadium the stadium to set
	 */
	public void setStadium(Card stadium) {
		this.stadium = stadium;
	}
	/**
	 * @return the supporter
	 */
	public Card getSupporter() {
		return supporter;
	}
	/**
	 * @param supporter the supporter to set
	 */
	public void setSupporter(Card supporter) {
		this.supporter = supporter;
	}

	public void shuffleDeck(){
		for ( int i = 0; i < getDeck().size(); i++ ) 
		{
			int j = ( int ) ( Math.random() * getDeck().size() );
			Card temp = getDeck().get(i); 		 // swap
			getDeck().set(i,getDeck().get(j));	 // the
			getDeck().set(j,temp); 			 	 // cards
		}
	}
}
