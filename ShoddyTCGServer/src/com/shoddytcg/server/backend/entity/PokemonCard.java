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
	/**
	 * @param type
	 */
	PokemonCard() {
		super(CardType.Type.POKEMON);
	}
	public enum Stage { BASIC, STAGE1, STAGE2, LVLX}
	public enum PokemonType { GRASS, FIRE, WATER, LIGHTNING, PSYCHIC, FIGHTING, COLORLESS, DARK, METAL}
	
	private Stage stage;
	private int hp;
	private String preStage;
	private PokemonType pokemonType;
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
	 * @return the stage
	 */
	public String getStageText() {
		return StageToString(stage);
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
	public PokemonType getPokemonType() {
		return pokemonType;
	}
	/**
	 * @return the type
	 */
	public String getPokemonTypeText() {
		return PokemonTypeToString(this.pokemonType);
	}
	/**
	 * @param type the type to set
	 */
	public void setPokemonType(PokemonType type) {
		this.pokemonType = type;
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
	
	public static PokemonType StringToPokemonType(String name){
		try{
			name = name.toUpperCase();
			if(name.equals("C"))
				return PokemonType.COLORLESS;
			else if(name.equals("D"))
				return PokemonType.DARK;
			else if(name.equals("F"))
				return PokemonType.FIGHTING;
			else if(name.equals("R"))
				return PokemonType.FIRE;
			else if(name.equals("G"))
				return PokemonType.GRASS;
			else if(name.equals("L"))
				return PokemonType.LIGHTNING;
			else if(name.equals("M"))
				return PokemonType.METAL;
			else if(name.equals("P"))
				return PokemonType.PSYCHIC;
			else if(name.equals("W"))
				return PokemonType.WATER;
			else
				return null;
		}catch(Exception e){
			return null;
		}
	}
	
	public static String PokemonTypeToString(PokemonType name){
		try{
			if(name.equals(PokemonType.COLORLESS))
				return "C";
			else if(name.equals(PokemonType.DARK))
				return "D";
			else if(name.equals(PokemonType.FIGHTING))
				return "F";
			else if(name.equals(PokemonType.FIRE))
				return "R";
			else if(name.equals(PokemonType.GRASS))
				return "G";
			else if(name.equals(PokemonType.LIGHTNING))
				return "L";
			else if(name.equals(PokemonType.METAL))
				return "M";
			else if(name.equals(PokemonType.PSYCHIC))
				return "P";
			else if(name.equals(PokemonType.WATER))
				return "W";
			else
				return "";
		}catch(Exception e){
			return "";
		}
	}
	
	public static Stage StringToStage(String stage){
		try{
			if(stage.equalsIgnoreCase("basic"))
				return Stage.BASIC;
			else if(stage.equalsIgnoreCase("stage 1"))
				return Stage.STAGE1;
			else if(stage.equalsIgnoreCase("stage 2"))
				return Stage.STAGE2;
			else if(stage.equalsIgnoreCase("lv.x"))
				return Stage.LVLX;
			else if(stage.equalsIgnoreCase("Level-Up"))
				return Stage.LVLX;
			else
				return null;
		}catch(Exception e){
			return null;
		}
	}
	public static String StageToString(Stage stage){
		try{
			if(stage.equals(Stage.BASIC))
				return "Basic";
			else if(stage.equals(Stage.STAGE1))
				return "Stage 1";
			else if(stage.equals(Stage.STAGE2))
				return "Stage 2";
			else if(stage.equals(Stage.LVLX))
				return "LV.X";
			else 
				return "";
		}catch(Exception e){
			return "";
		}
	}
}

