package gameEngine_interface;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import components.entityComponents.AccelerationComponent;
import components.entityComponents.ComponentType;
import components.entityComponents.KeyExpression;
import components.entityComponents.KeyInputComponent;
import components.entityComponents.LocationComponent;
import components.entityComponents.SpriteComponent;
import components.entityComponents.VelocityComponent;
import components.keyExpressions.ConcreteKeyExpressions;
import javafx.scene.input.KeyCode;
import data_interfaces.Communicator;
import data_interfaces.XMLParser;
import engines.AbstractEngine;
import engines.CollisionEngine;
import engines.InputEngine;
import engines.MovementEngine;
import engines.NewMovementEngine;
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
	private List<AbstractEngine> myEngines = Arrays.asList(new NewMovementEngine(myEntityManager), new CollisionEngine(myEntityManager));
	private XMLParser myParser = new XMLParser();
	private Map<IEntity, IRestrictedEntity> entityToRestricted;
	private Entity mainCharacter;

	public GameEngine(){
		initializeRestrictedEntities();
	}
	public void loadData(Communicator c){
		myEntityManager = new EntityManager(c.getData());
		myEngines = Arrays.asList(new NewMovementEngine(myEntityManager), new CollisionEngine(myEntityManager));
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
			changedEntity.addAll(s.update(keysPressed));
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
	
	//TODO: Delete once testing is over
	public void dummyLoad(){
		Collection<Entity> e = new ArrayList<Entity>();
		for (int i=0;i<20;i++){
			Entity t = new Entity(i);
			t.addComponent(new LocationComponent(i*50,450));
			t.addComponent(new SpriteComponent(("dirt.jpg")));
			if (i<1){
			Entity t2 = new Entity(i+21);
			t2.addComponent(new LocationComponent(300,400-i*50));
			t2.addComponent(new SpriteComponent(("stone.gif")));
			e.add(t2);
			}
			e.add(t);
		}
		Entity t = new Entity(40);
		t.addComponent(new LocationComponent(0,200));
		t.addComponent(new VelocityComponent(0,0));
		t.addComponent(new SpriteComponent(("platform_tile_053.png")));
		t.addComponent(new KeyInputComponent());
		t.addComponent(new AccelerationComponent(0,0));
		//t.addComponent(new TerminalVelComponent(5, 5));
		((KeyInputComponent) t.getComponent(ComponentType.KeyInput)).addToMap(KeyCode.W,ConcreteKeyExpressions.JUMP.getKeyExpression());
		e.add(t);
		((KeyInputComponent) t.getComponent(ComponentType.KeyInput)).addToMap(KeyCode.A,ConcreteKeyExpressions.LEFT.getKeyExpression());
		e.add(t);
		((KeyInputComponent) t.getComponent(ComponentType.KeyInput)).addToMap(KeyCode.D,ConcreteKeyExpressions.RIGHT.getKeyExpression());
		e.add(t);
		mainCharacter = t;
		myEntityManager=new EntityManager(e);    
		myEngines = Arrays.asList(new NewMovementEngine(myEntityManager), new InputEngine(myEntityManager));
		initializeRestrictedEntities();
	}
	
	public IEntity getMainCharacter(){
		for(IEntity e : myEntityManager.getEntityMap().keySet()){
			if(e.getComponent(ComponentType.KeyInput) != null){
				return e;
			}
		}
		return null;
	}
}