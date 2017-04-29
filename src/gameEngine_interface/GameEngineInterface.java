package gameEngine_interface;


import entity.Entity;
import entity.IEntity;
import gamedata.IRestrictedGameData;
import javafx.scene.input.KeyCode;

import java.util.Collection;

import data_interfaces.Communicator;

/**
 * A game engine is created in the gameplayer which uses this game engine to
 * handle collisions between objects as well as Keyboard inputs
 * 
 * @author Bilva
 *
 */
public interface GameEngineInterface {
	/**
	 * When called by a higher class the GameEngine should utilize its stored
	 * data and update that data
	 * 
	 * @return
	 */


	
	public Collection<IEntity> save();
	
	public IRestrictedGameData loadData(Communicator c);

	void handleUpdates(Collection<KeyCode> keysPressed);

}
