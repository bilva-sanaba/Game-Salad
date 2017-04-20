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
import actions.BounceOffBlockBottomOrTop;
import actions.BounceOffBlockSide;
import actions.IAction;
import components.collisionComponents.CollisionComponentType;
import components.collisionComponents.CollisionComponentsHandler;
import components.collisionComponents.SideCollisionComponent;
import components.entityComponents.CollidableComponent;
import components.entityComponents.ComponentType;
import components.entityComponents.EntityType;
import components.entityComponents.ImagePropertiesComponent;
import components.entityComponents.KeyInputComponent;
import components.entityComponents.LabelComponent;
import components.entityComponents.SpriteComponent;
import components.entityComponents.TypeComponent;
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
import entity.presets.AbstractBlock;
import entity.presets.AbstractMysteryBlock;
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
		Collection<Entity> e = new ArrayList<Entity>();
		Entity x = new Entity(0);
		x.addComponent(new LocationComponent(700,100));
		x.addComponent(new SpriteComponent(("dirt.jpg")));



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
		x.addComponent(new TypeComponent(EntityType.Player));
		((KeyInputComponent) x.getComponent(ComponentType.KeyInput)).addToMap(KeyCode.W, "JUMP");
		((KeyInputComponent) x.getComponent(ComponentType.KeyInput)).addToMap(KeyCode.D, "RIGHT");
		((KeyInputComponent) x.getComponent(ComponentType.KeyInput)).addToMap(KeyCode.A, "LEFT");
		((KeyInputComponent) x.getComponent(ComponentType.KeyInput)).addToMap(KeyCode.R, "if (vc.getY()==0) { vc.setY(-3) ; ac.setY(0.05) }");


		((KeyInputComponent) x.getComponent(ComponentType.KeyInput)).addToMap(KeyCode.T, "REMOVE");
		e.add(x);

		//		for (int i=0;i<20;i++){
		//			Entity x = new Entity(i);
		//			x.addComponent(new LocationComponent(i*50,450));
		//			x.addComponent(new SpriteComponent(("dirt.jpg")));
		//
		//			ImagePropertiesComponent xc = new ImagePropertiesComponent();
		//			xc.setHeight(50);
		//			xc.setWidth(50);
		//			x.addComponent(xc);
		//
		//			SideCollisionComponent scc = new SideCollisionComponent(CollisionComponentType.Top, new BlockTopRegularCollision());
		//			x.addComponent(scc);
		//
		//			x.addComponent(new LabelComponent("Block"));
		//			e.add(x);
		//		}
		//		e.add(g);e.add(t);

		for (int i=1;i<20;i++){
			if (i!=2 && i!=4){
				Entity p = new AbstractBlock(i);
				p.addComponent(new LocationComponent(i*100,i*50));
				p.addComponent(new SpriteComponent(("dirt.jpg")));

				ImagePropertiesComponent xpc = new ImagePropertiesComponent();
				xpc.setHeight(50);
				xpc.setWidth(50);
				p.addComponent(xc);


				p.addComponent(new LabelComponent("Blok"));
				e.add(p);
			}
			Entity y = new Entity(22);
			y.addComponent(new LocationComponent(500,200));
			y.addComponent(new SpriteComponent(("stone.gif")));
			ImagePropertiesComponent yc = new ImagePropertiesComponent();
			yc.setHeight(50);
			yc.setWidth(50);
			y.addComponent(yc);
			Entity p = new AbstractMysteryBlock(21,y); 
			p.addComponent(new LocationComponent(400,200));
			p.addComponent(new SpriteComponent(("sand.jpg")));
			ImagePropertiesComponent xpc = new ImagePropertiesComponent();
			xpc.setHeight(50);
			xpc.setWidth(50);
			p.addComponent(xc);
			p.addComponent(new LabelComponent("Blok"));
			e.add(p);

		}


		myEntityManager = new EntityManager(e);

		//		myEngines = Arrays.asList(new NewMovementEngine(myEntityManager),new CollisionEngine(myEntityManager),new InputEngine(myEntityManager));
		myEngines = Arrays.asList(new InputEngine(myEntityManager), new NewMovementEngine(myEntityManager), new CollisionEngine(myEntityManager));
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