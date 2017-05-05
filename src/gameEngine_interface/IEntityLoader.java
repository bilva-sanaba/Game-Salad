package gameEngine_interface;

import entity.IEntityManager;
/**
 * Interface of EntityLoader which is used to modify an entity manager in order to load a new state
 * @author Bilva
 *
 */
public interface IEntityLoader {
	/**
	 * Modifies gameState given a newEntityManager
	 * Useful if IEntityLoader holds the EntityManager displayed by the game and can then 
	 * take in a new state and replace  the old EntityManager with the new one without pointers
	 * @param newManager
	 */
	void loadNew(IEntityManager newManager);

}