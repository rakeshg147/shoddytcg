/**
 * 
 */
package org.pokenet.server.backend.entity;

/**
 * @author Nushio
 *
 */
public class TrainerCard extends CardType{
	public enum Type { TRAINER, TOOL}
	
	private String Text;

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
	
	
}
