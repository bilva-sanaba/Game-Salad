package gameView.gameScreen;

import entity.restricted.IRestrictedEntityManager;

public interface IGameScreenEntity {

	/**
	 * Add an entity 
	 * @param entity - entity to add
	 */
	public void addEntity(IRestrictedEntityManager entity);
	
}
