package gameEngine_interface;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.input.KeyCode;
import data_interfaces.Communicator;
import data_interfaces.XMLParser;
import engines.AbstractEngine;
import engines.CollisionEngine;
import engines.MovementEngine;
import entity.Entity;
import entity.EntityManager;
import entity.IEntity;
import entity.restricted.IRestrictedEntity;
import entity.restricted.RestrictedEntity;
import entity.restricted.RestrictedEntityFactory;
import entity.restricted.RestrictedEntityManager;
import engines.AbstractEngine;
import entity.IEntityManager;

/**
 * Basic GameEngine class Note: the engines must be created in someway, likely
 * via reflection
 * 
 * @author Bilva
 *
 */
public class GameEngine implements GameEngineInterface {
	private EntityManager myEntityManager = new EntityManager(new ArrayList<Entity>()); 
	private RestrictedEntityFactory myREF = new RestrictedEntityFactory();
	private RestrictedEntityManager myRestrictedEntityManager;
	private List<AbstractEngine> myEngines = Arrays.asList(new MovementEngine(
			myEntityManager), new CollisionEngine(myEntityManager));
	private XMLParser myParser = new XMLParser();
	private Map<IEntity, IRestrictedEntity> entityToRestricted;
	

	public GameEngine(){
		initializeRestrictedEntities();
	}
	public void loadData(Communicator c){
		myEntityManager = new EntityManager(c.getData());
		initializeRestrictedEntities();
	}
	public Collection<Entity> save(){
		
		return myEntityManager.copy();
	}
	private void initializeRestrictedEntities(){
		myRestrictedEntityManager = myEntityManager.getRestricted();
		entityToRestricted = myEntityManager.getEntityMap();
	}

	/**
	 * Runs each Engine in my Engine
	 */
	@Override

	public Collection <RestrictedEntity> handleUpdates(Collection<KeyCode> keysPressed) {
		Collection <IEntity> changedEntity = new ArrayList<IEntity>();
		Map <Integer, IEntity> changedEntityMap = new HashMap<Integer,IEntity>();
		for (AbstractEngine s : myEngines){
			changedEntity.addAll(s.update());
		}
		Collection<RestrictedEntity> changedRestrictedEntity = new ArrayList<RestrictedEntity>();
		for (IEntity e : changedEntity) {
			changedRestrictedEntity.add(myREF.createRestrictedEntity(e));
		}
		return changedRestrictedEntity;
	}

	@Override
	public RestrictedEntityManager getRestrictedEntityManager() {
		return myRestrictedEntityManager;
	}
}