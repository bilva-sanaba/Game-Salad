package gameEngine_interface;


import entitiy.restricted.IRestrictedEntityManager;
import entitiy.restricted.RestrictedEntityManager;

/**
 * A game engine is created in the gameplayer which uses this game engine to handle collisions between objects as well as
 * Keyboard inputs
 * @author Bilva
 *
 */
public interface GameEngineInterface {
	/**
	 * When called by a higher class the GameEngine should utilize its stored data and update that data
	 */
	public void handleUpdates();
	/**
	 * 
	 * @return Encapsulation of all Entities
	 */
	public RestrictedEntityManager getRestrictedEntityManager();

	
}
