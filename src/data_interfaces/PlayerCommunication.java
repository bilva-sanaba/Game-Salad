package data_interfaces;

import java.util.List;

import entity.LevelEntity;
import entity.SplashData;

public interface PlayerCommunication {
	/**
	 * Returns all of the level entities in order
	 * @return the level entities
	 */
	public List<LevelEntity> getLevelEntities();
	
	/**
	 * Returns the SplashData for the game
	 * @return the SplashData
	 */
	public SplashData getSplashEntity();
}
