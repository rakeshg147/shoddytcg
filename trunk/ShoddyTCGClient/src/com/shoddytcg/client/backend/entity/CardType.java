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

/**
 * @author Nushio
 * Empty class that represents the different card types. 
 */
public class CardType {

	public enum Type { POKEMON, TRAINER, STADIUM, SUPPORTER, ENERGY}
	
	private Type type;
	CardType(Type type){
		this.type=type;
	}
	
	public Type getType(){
		return type;
	}
	
	public boolean isPokemon(){
		try{
			if(type.equals(Type.POKEMON))
				return true;
			else
				return false;
		}catch(Exception e){
			return false;
		}
	}
	
	public boolean isTrainer(){
		try{
			if(type.equals(Type.TRAINER))
				return true;
			else
				return false;
		}catch(Exception e){
			return false;
		}
	}
	
	public boolean isSupporter(){
		try{
			if(type.equals(Type.SUPPORTER))
				return true;
			else
				return false;
		}catch(Exception e){
			return false;
		}
	}
	
	public boolean isStadium(){
		try{
			if(type.equals(Type.STADIUM))
				return true;
			else
				return false;
		}catch(Exception e){
			return false;
		}
	}
	
	public boolean isEnergy(){
		try{
			if(type.equals(Type.ENERGY))
				return true;
			else
				return false;
		}catch(Exception e){
			return false;
		}
	}
}