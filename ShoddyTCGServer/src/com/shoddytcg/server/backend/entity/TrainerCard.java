/**
 * 
 */
package com.shoddytcg.server.backend.entity;


/**
 * @author Nushio
 *
 */
public class TrainerCard extends CardType{
	public enum Type { TRAINER, TOOL, TM, POKEMON}
	
	private Type type;
	private String Text;

	
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
	
	public static Type returnType(String type){
		try{
			if(type.equalsIgnoreCase("Tool"))
				return Type.TOOL;
			else if(type.equalsIgnoreCase("TM"))
				return Type.TM;
			else if(type.equalsIgnoreCase("Pokemon"))
				return Type.POKEMON;
			else 
				return Type.TRAINER;
		}catch(Exception e){
			return Type.TRAINER;
		}
	}
}
