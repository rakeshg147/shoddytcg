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
public class EnergyCard extends CardType{
	public enum Type { BASIC, SPECIAL}
	public enum Energy { GRASS, FIRE, WATER, LIGHTNING, PSYCHIC, FIGHTING, COLORLESS, DARK, METAL, EMPTY}
	
	private Type type;
	private ArrayList<Energy> provides = new ArrayList<Energy>();
	private String text;
	
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
	 * @return the provides
	 */
	public ArrayList<Energy> getProvides() {
		return provides;
	}
	/**
	 * @param provides the provides to set
	 */
	public void setProvides(ArrayList<Energy> provides) {
		this.provides = provides;
	}
	/**
	 * @param provides the provides to add
	 */
	public void addProvides(Energy provides) {
		this.provides.add(provides);
	}
	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	
	public static ArrayList<Energy> returnProvides(String energy){
		ArrayList<Energy> energyList = new ArrayList<Energy>();
		String[] energies = energy.split("|");
		for(int i = 0; i<energies.length;i++){
			energyList.add(returnEnergy(energies[i]));
		}
		return energyList;
	}
	
	public static Type returnType(String type){
		try{
			if(type.equalsIgnoreCase("Basic"))
				return Type.BASIC;
			else if(type.equalsIgnoreCase("Special"))
				return Type.SPECIAL;
			else 
				return Type.BASIC;
		}catch(Exception e){
			return Type.BASIC;
		}
	}
	public static Energy returnEnergy(String energy){
		try{
			if(energy.equals("C"))
				return Energy.COLORLESS;
			else if(energy.equals("D"))
				return Energy.DARK;
			else if(energy.equals("F"))
				return Energy.FIGHTING;
			else if(energy.equals("R"))
				return Energy.FIRE;
			else if(energy.equals("G"))
				return Energy.GRASS;
			else if(energy.equals("L"))
				return Energy.LIGHTNING;
			else if(energy.equals("M"))
				return Energy.METAL;
			else if(energy.equals("P"))
				return Energy.PSYCHIC;
			else if(energy.equals("W"))
				return Energy.WATER;
			else
				return Energy.EMPTY;
		}catch(Exception e){
			return Energy.EMPTY;
		}
	}
}
