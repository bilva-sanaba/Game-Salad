package gameEngine_interface;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.PriorityQueue;

import gameView.UIImageProperty;
import author_interfaces.GameData;
import author_interfaces.IGameData;
import data_interfaces.XMLParser;
import engines.AbstractEngine;
import engines.CollisionEngine;
import engines.MovementEngine;
import entitiy.restricted.IRestrictedEntityManager;
import entitiy.restricted.RestrictedEntityManager;
import entity.EntityManager;
import entity.IEntityManager;


import engines.AbstractEngine;
import entity.IEntityManager;
/**
 * Basic GameEngine class
 * Note: the engines must be created in someway, likely via reflection
 * @author Bilva
 *
 */
public class GameEngine implements GameEngineInterface {
	private GameData myLevelManager;
	private EntityManager myEntityManager; 

	private RestrictedEntityManager myRestrictedEntityManager;
	private List<AbstractEngine> myEngines = Arrays.asList(new MovementEngine(myEntityManager), new CollisionEngine(myEntityManager));
	private XMLParser myParser = new XMLParser();
	
	GameEngine(String xmlDataFile){
		myLevelManager = myParser.loadFile(xmlDataFile);
		myEntityManager = myLevelManager.getLevels()[0];
		myRestrictedEntityManager = myEntityManager.getRestricted();
	}
	/**
	 * Runs each Engine in my Engine
	 */
	@Override
	public void handleUpdates() {
		for (AbstractEngine s : myEngines){
			s.update();
		}	
	}
	@Override
	public RestrictedEntityManager getRestrictedEntityManager() {
		return myRestrictedEntityManager;
	}
}