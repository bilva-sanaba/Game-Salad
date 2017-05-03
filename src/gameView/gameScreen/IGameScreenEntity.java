package gameView.gameScreen;

import gameView.gameDataManagement.GameDataManager;

public interface IGameScreenEntity {

	/**
	 * Add Game Data to initialize game 
	 * @param entity - data to add
	 */
	public void addData(GameDataManager entity);
	
}
