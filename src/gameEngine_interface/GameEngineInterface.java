package gameEngine_interface;


import entitiy.restricted.IRestrictedEntityManager;
import entitiy.restricted.RestrictedEntityManager;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;

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
	public void handleUpdates(ArrayList<KeyCode> keyPressed);
	/**
	 * 
	 * @return Encapsulation of all Entities
	 */
	public RestrictedEntityManager getRestrictedEntityManager();

	
}
