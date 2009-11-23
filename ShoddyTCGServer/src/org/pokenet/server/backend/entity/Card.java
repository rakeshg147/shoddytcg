package org.pokenet.server.backend.entity;

import java.util.ArrayList;

/**
 * Represents a card
 * @author nushio
 *
 */
public class Card {
	public enum Type { POKEMON, ENERGY, TRAINER, STADIUM, SUPPORTER}
	public enum SubType { BASIC, STAGE1, STAGE2, LVLUP, SPECIAL, TOOL}
	public enum Color { GRASS, FIRE, WATER, LIGHTNING, PSYCHIC, FIGHTING, COLORLESS, DARK, METAL}
	
	private String id;
	private int set;
	private String name;
	private CardType cardType;
	
	private Type type;
	private SubType subtype;
	private int hp;
	private String evolvesFrom;
	private Color color;
	private String attack1;
	private String attack2;
	private String attack3;
	private String attack4;
	private String weakness;
	private String resistance;
	private int retreat;
	private ArrayList<Card> attachments;
	private boolean faceUp = false; 
	
	public Card() {
		
	}
	
	public Card(String cardId) {
		id = cardId;
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
	 * @return the type
	 */
	public Type getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Type type) {
		this.type = type;
	}

	/**
	 * @return the subtype
	 */
	public SubType getSubtype() {
		return subtype;
	}

	/**
	 * @param subtype the subtype to set
	 */
	public void setSubtype(SubType subtype) {
		this.subtype = subtype;
	}

	/**
	 * @return the hp
	 */
	public int getHp() {
		return hp;
	}

	/**
	 * @param hp the hp to set
	 */
	public void setHp(int hp) {
		this.hp = hp;
	}

	/**
	 * @return the evolvesFrom
	 */
	public String getEvolvesFrom() {
		return evolvesFrom;
	}

	/**
	 * @param evolvesFrom the evolvesFrom to set
	 */
	public void setEvolvesFrom(String evolvesFrom) {
		this.evolvesFrom = evolvesFrom;
	}

	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * @return the attack1
	 */
	public String getAttack1() {
		return attack1;
	}

	/**
	 * @param attack1 the attack1 to set
	 */
	public void setAttack1(String attack1) {
		this.attack1 = attack1;
	}

	/**
	 * @return the attack2
	 */
	public String getAttack2() {
		return attack2;
	}

	/**
	 * @param attack2 the attack2 to set
	 */
	public void setAttack2(String attack2) {
		this.attack2 = attack2;
	}

	/**
	 * @return the attack3
	 */
	public String getAttack3() {
		return attack3;
	}

	/**
	 * @param attack3 the attack3 to set
	 */
	public void setAttack3(String attack3) {
		this.attack3 = attack3;
	}

	/**
	 * @return the attack4
	 */
	public String getAttack4() {
		return attack4;
	}

	/**
	 * @param attack4 the attack4 to set
	 */
	public void setAttack4(String attack4) {
		this.attack4 = attack4;
	}

	/**
	 * @return the weakness
	 */
	public String getWeakness() {
		return weakness;
	}

	/**
	 * @param weakness the weakness to set
	 */
	public void setWeakness(String weakness) {
		this.weakness = weakness;
	}

	/**
	 * @return the resistance
	 */
	public String getResistance() {
		return resistance;
	}

	/**
	 * @param resistance the resistance to set
	 */
	public void setResistance(String resistance) {
		this.resistance = resistance;
	}

	/**
	 * @return the retreat
	 */
	public int getRetreat() {
		return retreat;
	}

	/**
	 * @param retreat the retreat to set
	 */
	public void setRetreat(int retreat) {
		this.retreat = retreat;
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
