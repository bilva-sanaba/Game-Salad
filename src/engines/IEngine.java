// This entire file is part of my masterpiece.
// Bilva Sanaba
// This class is a simple example of good design as it is an interface which allows the GameEngine to call the method declared here
// on all engines chosen. 
package engines;

import java.util.Collection;

import exceptions.CopyException;
import gamedata.IRestrictedGameData;
import javafx.scene.input.KeyCode;

public interface IEngine {
	/**
	 * Method called by the game engine in order to update the state of the game
	 * @param keysPressed current Keys being pressed 
	 * @param gameData current GameData
	 * @return updated GameData
	 * @throws CopyException
	 */
	public IRestrictedGameData update(Collection<KeyCode> keysPressed, IRestrictedGameData gameData) throws CopyException;
}
