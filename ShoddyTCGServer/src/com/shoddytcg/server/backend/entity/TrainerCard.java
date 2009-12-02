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

/**
 * @author Nushio
 *
 */
public class TrainerCard extends CardType{
	/**
	 * @param type
	 */
	TrainerCard() {
		super(Type.TRAINER);
	}

	public enum TrainerType { TRAINER, TOOL, TM, POKEMON}
	
	private TrainerType type;
	private Attack attack;
	private String Text;
	private String hp="0";

	/**
	 * @return the attack
	 */
	public Attack getAttack() {
		return attack;
	}

	/**
	 * @param attack the attack to set
	 */
	public void setAttack(Attack attack) {
		this.attack = attack;
	}

	/**
	 * @return the hp
	 */
	public String getHP() {
		return hp;
	}

	/**
	 * @param hp the hp to set
	 */
	public void setHP(String hp) {
		this.hp = hp;
	}

	/**
	 * @return the type
	 */
	public TrainerType getTrainerType() {
		return type;
	}
	/**
	 * @return the type
	 */
	public String getTrainerTypeText() {
		return TrainerTypeToString(type);
	}
	/**
	 * @param type the type to set
	 */
	public void setTrainerType(TrainerType type) {
		this.type = type;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return Text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		Text = text;
	}
	
	public static TrainerType StringToTrainerType(String type){
		try{
			if(type.equalsIgnoreCase("Tool"))
				return TrainerType.TOOL;
			else if(type.equalsIgnoreCase("TM"))
				return TrainerType.TM;
			else if(type.equalsIgnoreCase("Pokemon"))
				return TrainerType.POKEMON;
			else 
				return TrainerType.TRAINER;
		}catch(Exception e){
			return TrainerType.TRAINER;
		}
	}
	public static String TrainerTypeToString(TrainerType type){
		try{
			if(type.equals(TrainerType.TOOL))
				return "tool";
			else if(type.equals(TrainerType.TM))
				return "tm";
			else if(type.equals(TrainerType.POKEMON))
				return "pokemon";
			else 
				return "trainer";
		}catch(Exception e){
			return "trainer";
		}
	}
}
