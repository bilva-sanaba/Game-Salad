package gameView;

import java.util.Set;

import entity.restricted.IRestrictedEntityManager;
import gamedata.GameData;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

/**
 * View for the playing version of the front end. Will be the controller for
 * everything for the front end from handling button presses, to moving the game
 * objects.
 * 
 * @author Henry
 *
 */

public interface UIViewInterface {

	

	/**
	 * Starts the animation to run the game
	 */
	public void runGame();
	
	/**
	 * Start the Game Authoring environment
	 */
	public void authorGame();
	
	/**
	 * Load a game with the given file path
	 * @param filePath - file path to the correct game
	 */
	public void loadGame(String filePath);
	
	/**
	 * Restart the current game using the saved filePath in Controller
	 */
	public void restart();
	
	/**
	 * Save the Game and its current states
	 */
	public void saveGame();
	
	/**
	 * Set the splash screen
	 */
	public void getSplashScreen();
	
	/**
	 * returns the current stage
	 * @return
	 */
	public Stage getStage();
	
	/**
	 * Add an entity to the manager
	 * @param entity - entity to add
	 */
//	public void addData(GameData data);
	
	public void step(Set<KeyCode> keysPressed);

	

}
