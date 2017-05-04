package data_interfaces;

import java.util.Collection;
import java.util.List;

import engines.infinite.InfiniteEnum;
import entity.Entity;
import entity.IEntityManager;

public interface EngineCommunication {

	/**
	 * Returns the list of entity managers in level order
	 * from the instantiated communicator
	 * @return the list of entity managers
	 */
	public List<IEntityManager> getIEntityManagers();

	/**
	 * Returns the Enum indicating the Infinite quality of the game
	 * stored in the first LevelEntity
	 * @return
	 */
	public InfiniteEnum getInfinite();
	
	/**
	 * Returns the music for the game
	 * @return the string for the music file
	 */
	public String getMusic();
	
	/**
	 * Returns the amount of lives for the player as set for the
	 * game
	 * @return the number of lives
	 */
	public int getLives();
	
	/**
	 * Return whether the camera is on or off
	 * @return true if camera should be on, false if it should be off
	 */
	public boolean getCameraOn();
}
