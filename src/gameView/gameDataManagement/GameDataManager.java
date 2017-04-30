package gameView.gameDataManagement;

import gamedata.IRestrictedGameData;

public class GameDataManager {

	private IRestrictedGameData myGameData;
	//private Observers
	
	public GameDataManager(IRestrictedGameData gameData) {
		myGameData = gameData;
		
	}
}
