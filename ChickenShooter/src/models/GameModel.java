package models;

import other.GameComplexity;

public class GameModel {
	private Integer playerHp;
	private GameComplexity complexity;
	private boolean gameIsOver = false;

	private final static Integer MIN_PLAYER_HP = 0;
	private final static Integer PLAYER_HP_ON_START = 10;

	public GameModel(GameComplexity complexity) {
		this.setPlayerHp(PLAYER_HP_ON_START);
		this.setComplexity(complexity);
	}

	public boolean gameIsOver() {
		return gameIsOver;
	}

	public void endGame() {
		this.gameIsOver = true;
		System.out.println("Game is over");
	}

	public void decreasePlayerHpByOne() {
		if (this.playerHp - 1 == MIN_PLAYER_HP) {
			this.endGame();
		} else {
			setPlayerHp(playerHp - 1);
		}
	}

	public Integer getPlayerHp() {
		return playerHp;
	}

	private void setPlayerHp(Integer playerHp) {
		if (playerHp < MIN_PLAYER_HP) {
			throw new IllegalArgumentException(String.format("Player Hp can't be less than min: %d", MIN_PLAYER_HP));
		} else {
			this.playerHp = playerHp;
		}
	}

	public GameComplexity getComplexity() {
		return complexity;
	}

	private void setComplexity(GameComplexity complexity) {
		if (complexity == null) {
			throw new IllegalArgumentException("Complexity can't be null");
		} else {
			this.complexity = complexity;
		}
	}

}
