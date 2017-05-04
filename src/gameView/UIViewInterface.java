package gameView;

import java.util.Set;
import gameView.userManagement.IUserManager;
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
	 * returns the current stage
	 * @return
	 */
	public Stage getStage();
	
	/**
	 * Called by WorldAnimator through GameScreen to tell the backend to progress one frame
	 * @param keysPressed - current keys being pressed by the user
	 */
	public void step(Set<KeyCode> keysPressed);

	/**
	 * Sets a new stage to be shown, pauses the original stage until the user closes out of the new stage
	 * @param view - view to be displayed
	 * @param s - stage to display the view through
	 */
	public void newStage(AbstractViewer view, Stage s);
	
	/**
	 * Retrieve manager containing all current users
	 * @return interface of user manager
	 */
	public IUserManager getUserManager();

}
