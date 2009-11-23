package net.shoddytcg.server.backend.battle;

import java.util.ArrayList;

import net.shoddytcg.server.backend.entity.PlayerChar;

/**
 * @author Nushio
 *
 */
public class BattleField {
	
	private BattleArea player1;
	private BattleArea player2;
	private ArrayList<PlayerChar> spectators;
	/**
	 * @param playerChar
	 * @param otherPlayer
	 */
	public BattleField(PlayerChar player1, PlayerChar player2) {
		this.player1 = new BattleArea(player1);
		this.player2 = new BattleArea(player2);
	}
	/**
	 * @return the player1
	 */
	public BattleArea getPlayer1() {
		return player1;
	}
	/**
	 * @param player1 the player1 to set
	 */
	public void setPlayer1(BattleArea player1) {
		this.player1 = player1;
	}
	/**
	 * @return the player2
	 */
	public BattleArea getPlayer2() {
		return player2;
	}
	/**
	 * @param player2 the player2 to set
	 */
	public void setPlayer2(BattleArea player2) {
		this.player2 = player2;
	}
	/**
	 * @return the spectators
	 */
	public ArrayList<PlayerChar> getSpectators() {
		return spectators;
	}
	/**
	 * @param spectators the spectators to set
	 */
	public void setSpectators(ArrayList<PlayerChar> spectators) {
		this.spectators = spectators;
	}
}
