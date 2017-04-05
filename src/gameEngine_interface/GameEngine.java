package gameEngine_interface;

import java.util.List;

import engines.AbstractEngine;
import entity.IEntityManager;
/**
 * Basic GameEngine class
 * Note: the engines must be created in someway, likely via reflection
 * @author Bilva
 *
 */
public class GameEngine implements GameEngineInterface {
	private IEntityManager myEntityManager; 
	List<AbstractEngine> myEngines;
	/**
	 * Runs each Engine in my Engine
	 */
	@Override
	public void handleUpdates() {
		for (AbstractEngine s : myEngines){
			s.update();
		}	
	}
}
