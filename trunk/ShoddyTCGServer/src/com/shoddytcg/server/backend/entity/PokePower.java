/**
 * 
 */
package com.shoddytcg.server.backend.entity;

/**
 * @author Nushio
 *
 */
public class PokePower {
	private String name;
	private String text;
	
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
	 * @return the description
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param description the description to set
	 */
	public void setText(String text) {
		this.text = text;
	}
}
