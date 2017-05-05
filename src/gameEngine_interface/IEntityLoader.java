package gameEngine_interface;

import entity.IEntityManager;

public interface IEntityLoader {
	/**
	 * Modifies gameState given a newEntityManager
	 * Useful for changing an EntityManager to something 
	 * incorporating another one. 
	 * @param newManager
	 */
	void loadNew(IEntityManager newManager);

}