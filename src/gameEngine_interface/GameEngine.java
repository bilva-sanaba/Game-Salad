package gameEngine_interface;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import actions.BlockBottomRegularCollision;
import actions.BlockTopRegularCollision;
import actions.Explosion;
import actions.IAction;
import components.collisionComponents.CollisionComponentType;
import components.collisionComponents.CollisionComponentsHandler;
import components.collisionComponents.SideCollisionComponent;
import components.entityComponents.CollidableComponent;
import components.entityComponents.ComponentType;
import components.entityComponents.ImagePropertiesComponent;
import components.entityComponents.KeyInputComponent;
import components.entityComponents.LabelComponent;
import components.entityComponents.SpriteComponent;
import components.keyExpressions.ConcreteKeyExpressions;
import components.movementcomponents.AccelerationComponent;
import components.movementcomponents.LocationComponent;
import components.movementcomponents.VelocityComponent;
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
import entity.GPEntityManager;
import entity.IEntity;
import entity.restricted.IRestrictedEntity;
import entity.restricted.IRestrictedEntityManager;
import gamedata.GameData;
import engines.AbstractEngine;
import entity.IEntityManager;
import entity.SplashEntity;
import gamedata.GameData;

/**
 * Basic GameEngine class Note: the engines must be created in someway, likely
 * via reflection
 * 
 * @author Bilva
 *
 */
public class GameEngine implements GameEngineInterface {
	private EntityManager myEntityManager;// = new EntityManager(new ArrayList<Entity>()); 
	private List<AbstractEngine> myEngines;// = Arrays.asList(new NewMovementEngine(myEntityManager), new CollisionEngine(myEntityManager), new InputEngine(myEntityManager));
	private XMLParser myParser = new XMLParser();
	private Map<IEntity, IRestrictedEntity> entityToRestricted;
	private Entity mainCharacter;
	private GPEntityManager GPEM;
	private double points=0;
	private double lives=0;
	private double level=0; 

	public GameEngine(){
	}
	public GameData loadData(Communicator c){
		myEntityManager = new EntityManager(c.getData());
		GPEM = new GPEntityManager(c.getData());
		myEngines = Arrays.asList(new NewMovementEngine(myEntityManager), new CollisionEngine(myEntityManager), new InputEngine(myEntityManager));
		LocationComponent lc = (LocationComponent) getMainCharacter().getComponent(ComponentType.Location);
		GameData dg = new GameData(points,lives,(IRestrictedEntityManager) myEntityManager, level, lc);
		return dg;
	}
	public Collection<IEntity> save(){

		return myEntityManager.copy();
	}
	public SplashEntity getSplashEntity(){
		return GPEM.getSplash();
	}


	/**
	 * Runs each Engine in my Engine
	 */
	@Override

	public void handleUpdates(Collection<KeyCode> keysPressed) {
		Collection <IEntity> changedEntity = new ArrayList<IEntity>();
		Map <Integer, IEntity> changedEntityMap = new HashMap<Integer,IEntity>();
		for (AbstractEngine s : myEngines){
			s.update(keysPressed);
		}
	}

	//TODO: Delete once testing is over
	public GameData dummyLoad(){
//		Entity t = new Entity(40);
//		t.addComponent(new LocationComponent(0,200));
//		t.addComponent(new VelocityComponent(0,0));
//		t.addComponent(new SpriteComponent(("platform_tile_053.png")));
//		t.addComponent(new AccelerationComponent(0,0));
//		t.addComponent(new LabelComponent("notb"));
//		t.addComponent(new KeyInputComponent());
//
//		ImagePropertiesComponent ic = new ImagePropertiesComponent();
//		ic.setHeight(100);
//		ic.setWidth(100);
//		t.addComponent(ic);
//		((KeyInputComponent) t.getComponent(ComponentType.KeyInput)).addToMap(KeyCode.W, "JUMP");
//		((KeyInputComponent) t.getComponent(ComponentType.KeyInput)).addToMap(KeyCode.D, "RIGHT");
//		((KeyInputComponent) t.getComponent(ComponentType.KeyInput)).addToMap(KeyCode.A, "LEFT");
//		Entity g = new Entity(75);
//		g.addComponent(new LocationComponent(200,200));
//		g.addComponent(new SpriteComponent(("dirt.jpg")));
//		g.addComponent(new LabelComponent("Block"));
//		ImagePropertiesComponent lc = new ImagePropertiesComponent();
//		lc.setHeight(100);
//		lc.setWidth(100);
//		g.addComponent(lc);
//		g.addComponent(new SideCollisionComponent(CollisionComponentType.Top, new BlockTopRegularCollision()));
//		g.addComponent(new SideCollisionComponent(CollisionComponentType.Bottom, new BlockTopRegularCollision()));
//		g.addComponent(new SideCollisionComponent(CollisionComponentType.Left, new BlockTopRegularCollision()));
//		g.addComponent(new SideCollisionComponent(CollisionComponentType.Right, new BlockTopRegularCollision()));
		Collection<Entity> e = new ArrayList<Entity>();
		Entity x = new Entity(0);
		x.addComponent(new LocationComponent(500,400));
		x.addComponent(new SpriteComponent(("Feuer46.GIF")));

		ImagePropertiesComponent xc = new ImagePropertiesComponent();
		xc.setHeight(50);
		xc.setWidth(50);
		x.addComponent(xc);

		x.addComponent(new VelocityComponent(0,0));
		x.addComponent(new AccelerationComponent(0,0));
		x.addComponent(new CollidableComponent(true));
//		x.addComponent(new CollisionComponentsHandler());
//		SideCollisionComponent sc = new SideCollisionComponent(CollisionComponentType.Top);
//		sc.addActionForLabel(new LabelComponent("Blok"), new BlockTopRegularCollision());
//		SideCollisionComponent sct = new SideCollisionComponent(CollisionComponentType.Bottom);
//		sct.addActionForLabel(new LabelComponent("Blok"), new BlockBottomRegularCollision());
//		((CollisionComponentsHandler) x.getComponent(ComponentType.CollisionHandler)).addCollisionComponent(sc);
//		((CollisionComponentsHandler) x.getComponent(ComponentType.CollisionHandler)).addCollisionComponent(sct);
		x.addComponent(new LabelComponent("grrraah"));
		x.addComponent(new KeyInputComponent());
		((KeyInputComponent) x.getComponent(ComponentType.KeyInput)).addToMap(KeyCode.W, "JUMP");
		((KeyInputComponent) x.getComponent(ComponentType.KeyInput)).addToMap(KeyCode.D, "RIGHT");
		((KeyInputComponent) x.getComponent(ComponentType.KeyInput)).addToMap(KeyCode.A, "LEFT");
		((KeyInputComponent) x.getComponent(ComponentType.KeyInput)).addToMap(KeyCode.T, "REMOVE");
		e.add(x);
		for (int i=1;i<20;i++){
			Entity p = new Entity(i);
			p.addComponent(new LocationComponent(i*50,450));
			p.addComponent(new SpriteComponent(("dirt.jpg")));

			ImagePropertiesComponent xpc = new ImagePropertiesComponent();
			xpc.setHeight(50);
			xpc.setWidth(50);
			p.addComponent(xc);
			p.addComponent(new CollidableComponent(true));
		
			//p.addComponent(scc);
			p.addComponent(new CollisionComponentsHandler());
			SideCollisionComponent scc = new SideCollisionComponent(CollisionComponentType.Top);
			scc.addActionForLabel(new LabelComponent("grrraah"), new Explosion());
			SideCollisionComponent scq = new SideCollisionComponent(CollisionComponentType.Bottom);
			scq.addActionForLabel(new LabelComponent("grrraah"), new BlockBottomRegularCollision());
			((CollisionComponentsHandler) p.getComponent(ComponentType.CollisionHandler)).addCollisionComponent(scc);
			((CollisionComponentsHandler) p.getComponent(ComponentType.CollisionHandler)).addCollisionComponent(scq);
			
			p.addComponent(new LabelComponent("Blok"));
			e.add(p);
		}
		
		myEntityManager = new EntityManager(e);
		
//		myEngines = Arrays.asList(new NewMovementEngine(myEntityManager),new CollisionEngine(myEntityManager),new InputEngine(myEntityManager));
		myEngines = Arrays.asList(new NewMovementEngine(myEntityManager), new InputEngine(myEntityManager), new CollisionEngine(myEntityManager));
		return new GameData(0,0, (IRestrictedEntityManager) myEntityManager, 0, (LocationComponent) getMainCharacter().getComponent(ComponentType.Location) );
	}

	public IEntity getMainCharacter(){
		for(IEntity e : myEntityManager.getEntities()){
			if(e.getComponent(ComponentType.KeyInput) != null){
				return e;
			}
		}
		return null;
	}
}