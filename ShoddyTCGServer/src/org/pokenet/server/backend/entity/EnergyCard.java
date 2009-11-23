/**
 * 
 */
package org.pokenet.server.backend.entity;

/**
 * @author Nushio
 *
 */
public class EnergyCard extends CardType{
	public enum Type { BASIC, SPECIAL}
	public enum Energy { GRASS, FIRE, WATER, LIGHTNING, PSYCHIC, FIGHTING, COLORLESS, DARK, METAL}
	
	private Energy energy;
	private String effects;

	/**
	 * @return the energy
	 */
	public Energy getEnergy() {
		return energy;
	}

	/**
	 * @param energy the energy to set
	 */
	public void setEnergy(Energy energy) {
		this.energy = energy;
	}

	/**
	 * @return the effects
	 */
	public String getEffects() {
		return effects;
	}

	/**
	 * @param effects the effects to set
	 */
	public void setEffects(String effects) {
		this.effects = effects;
	}	
}
