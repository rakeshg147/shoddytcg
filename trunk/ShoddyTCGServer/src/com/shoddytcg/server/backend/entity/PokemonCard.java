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
 * @author Nushio
 *
 */
public class PokemonCard extends CardType{
	public enum Stage { BASIC, STAGE1, STAGE2, LVLX}
	public enum Type { GRASS, FIRE, WATER, LIGHTNING, PSYCHIC, FIGHTING, COLORLESS, DARK, METAL}
	
	private Stage stage;
	private int hp;
	private String preStage;
	private Type type;
	private Item item;
	private ArrayList<PokePower> pokepowers = new ArrayList<PokePower>();
	private ArrayList<PokeBody> pokebodies = new ArrayList<PokeBody>();
	private ArrayList<Attack> attacks = new ArrayList<Attack>();
	private String weakness;
	private String resistance;
	private String retreat;
	
	
	/**
	 * @return the item
	 */
	public Item getItem() {
		return item;
	}
	/**
	 * @param item the item to set
	 */
	public void setItem(Item item) {
		this.item = item;
	}
	/**
	 * @return the stage
	 */
	public Stage getStage() {
		return stage;
	}
	/**
	 * @param stage the stage to set
	 */
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	/**
	 * @return the hp
	 */
	public int getHP() {
		return hp;
	}
	/**
	 * @param hp the hp to set
	 */
	public void setHP(int hp) {
		this.hp = hp;
	}
	/**
	 * @return the preStage
	 */
	public String getPreStage() {
		return preStage;
	}
	/**
	 * @param preStage the preStage to set
	 */
	public void setPreStage(String preStage) {
		this.preStage = preStage;
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
	 * @return the attacks
	 */
	public ArrayList<Attack> getAttacks() {
		return attacks;
	}
	/**
	 * @param attacks the attacks to set
	 */
	public void setAttacks(ArrayList<Attack> attacks) {
		this.attacks = attacks;
	}
	/**
	 * @param attack the attack to add
	 */
	public void addAttack(Attack attack) {
		attacks.add(attack);
	}
	/**
	 * @return the pokepowers
	 */
	public ArrayList<PokePower> getPokepowers() {
		return pokepowers;
	}
	/**
	 * @param pokepower the pokepower to add
	 */
	public void addPokepower(PokePower pokepower) {
		pokepowers.add(pokepower);
	}
	/**
	 * @param pokepowers the pokepowers to set
	 */
	public void setPokepowers(ArrayList<PokePower> pokepowers) {
		this.pokepowers = pokepowers;
	}
	/**
	 * @return the pokebodies
	 */
	public ArrayList<PokeBody> getPokebodies() {
		return pokebodies;
	}
	/**
	 * @param pokebodies the pokebodies to set
	 */
	public void setPokebodies(ArrayList<PokeBody> pokebodies) {
		this.pokebodies = pokebodies;
	}
	/**
	 * @param pokebody the pokebody to add
	 */
	public void addPokebody(PokeBody pokebody) {
		pokebodies.add(pokebody);
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
	public String getRetreat() {
		return retreat;
	}
	/**
	 * @param retreat the retreat to set
	 */
	public void setRetreat(String retreat) {
		this.retreat = retreat;
	}
	
	public static Type returnType(String name){
		try{
			name = name.toUpperCase();
			if(name.equals("C"))
				return Type.COLORLESS;
			else if(name.equals("D"))
				return Type.DARK;
			else if(name.equals("F"))
				return Type.FIGHTING;
			else if(name.equals("R"))
				return Type.FIRE;
			else if(name.equals("G"))
				return Type.GRASS;
			else if(name.equals("L"))
				return Type.LIGHTNING;
			else if(name.equals("M"))
				return Type.METAL;
			else if(name.equals("P"))
				return Type.PSYCHIC;
			else if(name.equals("W"))
				return Type.WATER;
			else
				return null;
		}catch(Exception e){
			return null;
		}
	}
	public static Stage returnStage(String stage){
		try{
			if(stage.equalsIgnoreCase("basic"))
				return Stage.BASIC;
			else if(stage.equalsIgnoreCase("stage 1"))
				return Stage.STAGE1;
			else if(stage.equalsIgnoreCase("stage 2"))
				return Stage.STAGE2;
			else if(stage.equalsIgnoreCase("lv.x"))
				return Stage.LVLX;
			else
				return null;
		}catch(Exception e){
			return null;
		}
	}
}

