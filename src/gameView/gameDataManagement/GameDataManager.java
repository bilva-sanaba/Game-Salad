package gameView.gameDataManagement;

import gameView.tools.MusicManager;
import gamedata.IRestrictedGameData;

public class GameDataManager {

	private IRestrictedGameData myGameData;
	private MusicManager myMusic;
	
	public GameDataManager(IRestrictedGameData gameData) {
		myGameData = gameData;
		initializeManagers();
		
	}
	
	public IRestrictedGameData getData() {
		return myGameData;
	}
	
	public MusicManager getMusic() {
		return myMusic;
	}
	
	private void initializeManagers() {
		myMusic = new MusicManager(myGameData.getMusic());
	}
}
