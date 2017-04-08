package gameEngine_interface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import gameView.UIImageProperty;
import javafx.scene.input.KeyCode;
import author_interfaces.GameData;
import author_interfaces.IGameData;
import data_interfaces.XMLParser;
import engines.AbstractEngine;
import engines.CollisionEngine;
import engines.MovementEngine;
import entitiy.restricted.IRestrictedEntity;
import entitiy.restricted.IRestrictedEntityManager;
import entitiy.restricted.RestrictedEntity;
import entitiy.restricted.RestrictedEntityManager;
import entity.Entity;
import entity.EntityManager;
import entity.IEntity;
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
	private Map<IEntity, IRestrictedEntity> entityToRestricted;
	
	GameEngine(String xmlDataFile){
		myLevelManager = myParser.loadFile(xmlDataFile);
		myEntityManager = myLevelManager.getLevels()[0];
		myRestrictedEntityManager = myEntityManager.getRestricted();
		entityToRestricted = myEntityManager.getEntityMap();
	}
	/**
	 * Runs each Engine in my Engine
	 */
	@Override
	public Collection <RestrictedEntity> handleUpdates(Collection<KeyCode> keysPressed) {
		Collection <RestrictedEntity> changesRestrictedEntity = new ArrayList<RestrictedEntity>();
		for (AbstractEngine s : myEngines){
			changesRestrictedEntity.addAll(s.update());
		}	
		return changesRestrictedEntity;
	}
	@Override
	public RestrictedEntityManager getRestrictedEntityManager() {
		return myRestrictedEntityManager;
	}
}