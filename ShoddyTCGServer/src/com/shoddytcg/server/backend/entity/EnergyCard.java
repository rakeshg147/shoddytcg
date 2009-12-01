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
	
	/**
	 * @param type
	 */
	EnergyCard() {
		super(CardType.Type.ENERGY);
	}
	public enum EnergyType { BASIC, SPECIAL}
	public enum Energy { GRASS, FIRE, WATER, LIGHTNING, PSYCHIC, FIGHTING, COLORLESS, DARK, METAL, EMPTY}
	
	private EnergyType type;
	private ArrayList<Energy> provides = new ArrayList<Energy>();
	private String text;
	
	/**
	 * @return the type
	 */
	public EnergyType getEnergyType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setEnergyType(EnergyType type) {
		this.type = type;
	}
	/**
	 * @return the provides
	 */
	public ArrayList<Energy> getProvides() {
		return provides;
	}
	/**
	 * @return the provides
	 */
	public String getProvidesText() {
		String provides = "";
		for(int i = 0;i<this.provides.size();i++){
			provides+=EnergyToString(this.provides.get(i));
		}
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
			energyList.add(StringToEnergy(energies[i]));
		}
		return energyList;
	}
	
	public static EnergyType returnEnergyType(String type){
		try{
			if(type.equalsIgnoreCase("Basic"))
				return EnergyType.BASIC;
			else if(type.equalsIgnoreCase("Special"))
				return EnergyType.SPECIAL;
			else 
				return EnergyType.BASIC;
		}catch(Exception e){
			return EnergyType.BASIC;
		}
	}
	public static Energy StringToEnergy(String energy){
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
	public static String EnergyToString(Energy energy){
		try{
			if(energy.equals(Energy.COLORLESS))
				return "C";
			else if(energy.equals(Energy.DARK))
				return "D";
			else if(energy.equals(Energy.FIGHTING))
				return "F";
			else if(energy.equals(Energy.FIRE))
				return "R";
			else if(energy.equals(Energy.GRASS))
				return "G";
			else if(energy.equals(Energy.LIGHTNING))
				return "L";
			else if(energy.equals(Energy.METAL))
				return "M";
			else if(energy.equals(Energy.PSYCHIC))
				return "P";
			else if(energy.equals(Energy.WATER))
				return "W";
			else
				return "0";
		}catch(Exception e){
			return "0";
		}
	}
}
