package gameView.gameDataManagement;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import gameView.UIView;
import gameView.tools.MusicManager;
import gamedata.IRestrictedGameData;

public class GameDataManager {

	private IRestrictedGameData myGameData;
	private MusicManager myMusic;
	private UIView myView;
	
	public GameDataManager(UIView view, IRestrictedGameData gameData) {
		myView = view;
		myGameData = gameData;
		initializeManagers();
		setLevelListener();
		setLifeListener();
		
	}
	
	/**
	 * Returns restricted data
	 * @return
	 */
	public IRestrictedGameData getData() {
		return myGameData;
	}
	
	/**
	 * Returns music manager to handle music activation/deactivation
	 * @return
	 */
	public MusicManager getMusic() {
		return myMusic;
	}
	
	private void setLevelListener() {
		myGameData.getLevel().addListener(new ChangeListener<Number>(){
	        public void changed(ObservableValue<? extends Number> o,Number oldVal, 
	                 Number newVal){
	             if (newVal.intValue() == -1) {
	            	myView.ending("YOU WON!");
	             }
	        }
	      });
	}
	
	private void setLifeListener() {
		myGameData.getLives().addListener(new ChangeListener<Number>(){
	        public void changed(ObservableValue<? extends Number> o,Number oldVal, 
	                 Number newVal){
	        	if (newVal.intValue() <= 0) {
	        		 myView.ending("GAME OVER!");
	        	}
	        }
	      });
	}
	
	
	private void initializeManagers() {
		myMusic = new MusicManager(myGameData.getMusic());
	}
}
