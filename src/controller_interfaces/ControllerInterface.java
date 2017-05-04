package controller_interfaces;

import java.util.Set;

import javafx.scene.input.KeyCode;
import data_interfaces.XMLException;
import entity.SplashData;
import entity.restricted.IRestrictedEntityManager;
import gameEngine_interface.GameEngine;
import gameView.gameScreen.SpecificGameSplashView;
import gamedata.GameData;
import gamedata.IRestrictedGameData;

/**
 * This class defines the behavior of the controller. The controller runs most
 * of the game itself, and will only need to be called by View to handle user
 * interactions, such as starting a new game, saving a current game, resetting a
 * game, and handling a collision on the front end between two UI objects and
 * having the backend deal with it.
 * 
 * @author Henry
 *
 */
public interface ControllerInterface {

	/**
	 * handCollision is called by the front end, and will also pass back the
	 * three parameters to the back end to deal with two UI objects colliding.
	 * Back end will deal with any special actions and movements
	 * 
	 * @param coll1
	 *            - first item involved in collision
	 * @param coll2
	 *            - second item involved in collision
	 * @param allActive
	 *            - all active items on the screen
	 * @return Map of the old UIImageProperty to the new one
	 */
	/*
	 * public UIImageProperty handleCollision(UIImageProperty coll1,
	 * UIImageProperty coll2, Collection<UIImageProperty> allActive);
	 */

	/**
	 * Called by front end when user tells the program to save the game. Will
	 * communicate with Data builder to save all necessary state information.
	 */

	public void save(String fileName);
	
	/**
	 * Load new game from users choice from the UI. Will pass in the String of
	 * the data file for the new game, that will be in XML format and controller
	 * will create the new game.
	 * 
	 * @param filePath
	 *            - string of the file path to the new data file
	 * @return 
	 */
	public IRestrictedGameData loadNewGame(String filePath);

	/**
	 * Resets the current game using the original XML file 
	 * @throws XMLException 
	 *
	 * Resets the current game using the original XML file
	 */
	//public void resetCurrentGame() throws XMLException;
	
	public void makeGame();

	void step(Set<KeyCode> keysPressed);


	public SplashData getSplashData(String filePath);

}
