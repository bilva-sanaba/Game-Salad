package gameEngine_interface;
import java.util.Collection;
import gameView.UIImageProperty;

/**
 * A game engine is created in the gameplayer which uses this game engine to handle collisions between objects as well as
 * Keyboard inputs
 * @author Bilva
 *
 */
public interface GameEngineInterface {
	/**
	 * Method takes interactingObjects, currently limited to two colliding UIImageProperties, 
	 * or KeyCodeInput and loops through all other UIImageProperties in the game and updates them 
	 * appropriately based on the collision 
	 * @param collide1
	 * @param collide2
	 * @param allActive
	 */
	public void handleInteraction(Collection<Object> interactingObjects, Collection<UIImageProperty> allActive);

	
}
