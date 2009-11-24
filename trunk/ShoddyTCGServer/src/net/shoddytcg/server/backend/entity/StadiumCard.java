/**
 * 
 */
package net.shoddytcg.server.backend.entity;

/**
 * @author Nushio
 *
 */
public class StadiumCard extends CardType{
	public enum Type { BASIC, SPECIAL}
	
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