package gameView;

import javafx.scene.Scene;
import gameView.tools.DisplayManager;
import gameView.userInput.IUserInputData;
import gameView.userManagement.IUserManager;

public interface ICommandView {

	/**
	 * @return Viewer Scene to be displayed on stage
	 */
	public Scene getScene();
	
	/**
	 * run the game -- mostly used in GameScreen to start the timeline
	 */
	public void runGame();
	
	/**
	 * Load a new game given the parameter filePath
	 * @param filepath - file to load
	 */
	public void loadGame(String filepath);
	
	/**
	 * Restart current game
	 */
	public void restart();
	
	/**
	 * Save current game
	 */
	public void saveGame();
	
	/**
	 * Make new game in authoring environment
	 */
	public void makeGame();
	
	/**
	 * Return DisplayManager containing all current elements in heads-up-display
	 * @return
	 */
	public DisplayManager getComponents();
	
	/**
	 * Pause the current game
	 */
	public void pauseGame();
	
	/**
	 * Go to login screen to allow for user login
	 */
	public void loginScreen();
	
	/**
	 * Get user manager to view current user, sign out, etc. 
	 * @return
	 */
	public IUserManager getUserManager();
	
	/**
	 * Get user input data that needs to be communicated with backend (such as rewind)
	 * @return
	 */
	public IUserInputData getUserInput();
	
	/**
	 * Get current user's profile screen
	 */
	public void profileScreen();
	
}
