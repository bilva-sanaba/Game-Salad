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
import entity.Entity;
import entity.EntityManager;
import entity.IEntity;
import entity.IEntityManager;
import entity.restricted.IRestrictedEntity;
import entity.restricted.IRestrictedEntityManager;
import entity.restricted.RestrictedEntity;
import entity.restricted.RestrictedEntityFactory;
import entity.restricted.RestrictedEntityManager;
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
	private RestrictedEntityFactory myREF = new RestrictedEntityFactory();
	private RestrictedEntityManager myRestrictedEntityManager;
	private List<AbstractEngine> myEngines = Arrays.asList(new MovementEngine(myEntityManager), new CollisionEngine(myEntityManager));
	private XMLParser myParser = new XMLParser();
	private Map<IEntity, IRestrictedEntity> entityToRestricted;
	
	GameEngine(String xmlDataFile){
		myLevelManager = (GameData) myParser.loadFile(xmlDataFile);
		myEntityManager = ((GameData) myLevelManager).getLevels()[0];
		myRestrictedEntityManager = myEntityManager.getRestricted();
		entityToRestricted = myEntityManager.getEntityMap();
	}
	/**
	 * Runs each Engine in my Engine
	 */
	@Override

	public Collection <RestrictedEntity> handleUpdates(Collection<KeyCode> keysPressed) {
		Collection <Entity> changedEntity = new ArrayList<Entity>();
		
		for (AbstractEngine s : myEngines){
			changedEntity.addAll(s.update());
		}	
		Collection <RestrictedEntity> changedRestrictedEntity = new ArrayList<RestrictedEntity>();
		for (Entity e : changedEntity){
			changedRestrictedEntity.add(myREF.createRestrictedEntity(e));
		}
		return changedRestrictedEntity;
	}
	@Override
	public RestrictedEntityManager getRestrictedEntityManager() {
		return myRestrictedEntityManager;
	}
}