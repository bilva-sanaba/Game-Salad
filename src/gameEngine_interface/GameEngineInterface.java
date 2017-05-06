package gameEngine_interface;


import entity.Entity;
import entity.IEntity;
import entity.IEntityManager;
import gamedata.IRestrictedGameData;
import javafx.scene.input.KeyCode;

import java.util.Collection;
import java.util.List;

import data_interfaces.Communicator;
import data_interfaces.EngineCommunication;

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
	public List<IEntityManager> save();
	/**
	 * Given an EngineCommunication, retrieves all information needed to run file stored in Communication class
	 * @param c
	 * @return
	 */
	public IRestrictedGameData loadData(EngineCommunication c);
	/**
	 * Called during each iteration of WorldAnimator and must be used to update the display of all Entities
	 * @param keysPressed
	 */
	void handleUpdates(Collection<KeyCode> keysPressed);

}
