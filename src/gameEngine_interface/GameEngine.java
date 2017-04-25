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
import actions.DoubleJump;
import actions.IAction;
import actions.ImageChangeAction;
import actions.Reload;
import actions.RemoveAction;
import actions.ShootAction;
import actions.Teleport;
import components.entityComponents.AccelerationComponent;
import components.entityComponents.CheckCollisionComponent;
import components.entityComponents.CollidableComponent;
import components.entityComponents.CollisionComponentType;
import components.entityComponents.CollisionComponentsHandler;
import components.entityComponents.ComponentType;
import components.entityComponents.EntityType;
import components.entityComponents.GoalComponent;
import components.entityComponents.ImagePropertiesComponent;
import components.entityComponents.KeyInputComponent;
import components.entityComponents.LabelComponent;
import components.entityComponents.LocationComponent;
import components.entityComponents.ObjectCreationComponent;
import components.entityComponents.SideCollisionComponent;
import components.entityComponents.SpriteComponent;
import components.entityComponents.TerminalVelocityComponent;
import components.entityComponents.TimeComponent;
import components.entityComponents.TypeComponent;
import components.entityComponents.VelocityComponent;
import components.keyExpressions.JumpAction;
import components.keyExpressions.LeftAction;
import components.keyExpressions.RightAction;
import controller.Camera;
import javafx.scene.input.KeyCode;
import data_interfaces.Communicator;
import data_interfaces.XMLParser;
import engines.AbstractEngine;
import engines.CollisionEngine;
import engines.InputEngine;
import engines.LevelEngine;
import engines.MovementEngine;
import engines.TimeEngine;
import entity.Entity;
import entity.EntityManager;
import entity.GPEntityManager;
import entity.IEntity;
import entity.restricted.IRestrictedEntity;
import entity.restricted.IRestrictedEntityManager;
import gamedata.GameData;
import gamedata.IRestrictedGameData;
import engines.AbstractEngine;
import entity.IEntityManager;
import entity.SplashEntity;
import entity.presets.AbstractBlock;
import entity.presets.AbstractBreakableBox;
import entity.presets.AbstractGoal;
import entity.presets.AbstractMysteryBlock;
import entity.presets.AbstractPowerup;
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
	private String music = "";

	private Camera cam;
	
	public GameEngine(){
	}
	public IRestrictedGameData loadData(Communicator c){
		myEntityManager = new EntityManager(c.getData());
		GPEM = new GPEntityManager(c.getData());
		myEngines = Arrays.asList(new MovementEngine(myEntityManager), new CollisionEngine(myEntityManager), new InputEngine(myEntityManager), new LevelEngine(myEntityManager));
		LocationComponent lc = (LocationComponent) getMainCharacter().getComponent(ComponentType.Location);
		IRestrictedGameData dg = (IRestrictedGameData) new GameData(points,lives,(IRestrictedEntityManager) myEntityManager, level, lc,music);
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

	public void handleUpdates(Collection<KeyCode> keysPressed, IRestrictedGameData gd) {
		Collection <IEntity> changedEntity = new ArrayList<IEntity>();
		Map <Integer, IEntity> changedEntityMap = new HashMap<Integer,IEntity>();
		for (AbstractEngine s : myEngines){
			s.update(keysPressed,gd);
		}
	}
	
	//TODO: Dumb flappybird
//	public GameData dummyLoad(){
//		Collection<Entity> e = new ArrayList<Entity>();
//		Entity x = new Entity(0);
//		x.addComponent(new LocationComponent(100,150));
//		x.addComponent(new SpriteComponent(("flappybird_yellow.png")));
//		ImagePropertiesComponent xc = new ImagePropertiesComponent();
//		x.addComponent(new CheckCollisionComponent(true));
//		xc.setHeight(50);
//		xc.setWidth(50);
//		x.addComponent(xc);
//		x.addComponent(new VelocityComponent(3,0));
//		x.addComponent(new AccelerationComponent(0,0.1));
//		x.addComponent(new CollidableComponent(true));
//		x.addComponent(new LabelComponent("grrraah"));
//		x.addComponent(new KeyInputComponent());
//		x.addComponent(new TypeComponent(EntityType.Player));
//		((KeyInputComponent) x.getComponent(ComponentType.KeyInput)).addToMap(KeyCode.W, new DoubleJump());
//		e.add(x);
//		for (int i=1;i<10;i++){
//			Entity p = new AbstractBlock(i);
//			p.addComponent(new LocationComponent(i*200,200));
//			p.addComponent(new SpriteComponent(("pipe_up.png")));
//
//			ImagePropertiesComponent xpc = new ImagePropertiesComponent();
//			double d = Math.random();
//			xpc.setHeight(100*d);
//			xpc.setWidth(50);
//			p.addComponent(xpc);
//			p.addComponent(new LabelComponent("Blok"));
//			p.addComponent(new TypeComponent(EntityType.Block));
//			Entity q = new AbstractBlock(i*100);
//			q.addComponent(new LocationComponent(i*200,00));
//			q.addComponent(new SpriteComponent(("pipe_down.png")));
//
//			ImagePropertiesComponent xpq = new ImagePropertiesComponent();
//			xpq.setHeight(100*(1-d));
//			xpq.setWidth(50);
//			q.addComponent(xpq);
//			q.addComponent(new LabelComponent("Blok"));
//			q.addComponent(new TypeComponent(EntityType.Block));
//			
//			
//			e.add(p);
//			e.add(q);
//		}
//		myEntityManager = new EntityManager(e);
//
//		//		myEngines = Arrays.asList(new NewMovementEngine(myEntityManager),new CollisionEngine(myEntityManager),new InputEngine(myEntityManager));
//		myEngines = Arrays.asList(new InputEngine(myEntityManager), new NewMovementEngine(myEntityManager), new CollisionEngine(myEntityManager), new TimeEngine(myEntityManager));
//		return new GameData(0,0, (IRestrictedEntityManager) myEntityManager, 0, (LocationComponent) getMainCharacter().getComponent(ComponentType.Location),"" );
//	}
	
	public GameData dummyLoad(){
		System.out.println("-------------------------------------- line 118 of GameEngine");
		Collection<Entity> e = new ArrayList<Entity>();
		Entity x = new Entity(0);
		x.addComponent(new LocationComponent(100,150));
		x.addComponent(new SpriteComponent(("platform_tile_053.png")));
		ImagePropertiesComponent xc = new ImagePropertiesComponent();
		x.addComponent(new CheckCollisionComponent(true));
		xc.setHeight(50);
		xc.setWidth(50);
		x.addComponent(xc);
		x.addComponent(new VelocityComponent(0,0));
		x.addComponent(new AccelerationComponent(0,0.1));
		x.addComponent(new CollidableComponent(true));
		x.addComponent(new LabelComponent("grrraah"));
		x.addComponent(new KeyInputComponent());
		x.addComponent(new TypeComponent(EntityType.Player));

		x.addComponent(new GoalComponent());
		x.addComponent(new TerminalVelocityComponent(5,5));

		Entity y2 = new Entity(201);
		y2.addComponent(new LocationComponent(800,150));
		y2.addComponent(new SpriteComponent(("platform_tile_057.png")));
		ImagePropertiesComponent yc2 = new ImagePropertiesComponent();
		yc2.setHeight(50);
		yc2.setWidth(50);
		y2.addComponent(yc2);
		y2.addComponent(new VelocityComponent(3,0));
		y2.addComponent(new LabelComponent("Blok"));
		y2.addComponent(new CollidableComponent(false));
		y2.addComponent(new TimeComponent(new RemoveAction(), 3000));
		y2.addComponent(new TypeComponent(EntityType.Projectile));
		x.addComponent(new ObjectCreationComponent(y2));
		TimeComponent time = new TimeComponent(new Reload(), 1000);
		x.addComponent(time);
		((KeyInputComponent) x.getComponent(ComponentType.KeyInput)).addToMap(KeyCode.V, new ShootAction());
		((KeyInputComponent) x.getComponent(ComponentType.KeyInput)).addToMap(KeyCode.W, new JumpAction());
		((KeyInputComponent) x.getComponent(ComponentType.KeyInput)).addToMap(KeyCode.D, new RightAction());
		((KeyInputComponent) x.getComponent(ComponentType.KeyInput)).addToMap(KeyCode.A, new LeftAction());
		((KeyInputComponent) x.getComponent(ComponentType.KeyInput)).addToMap(KeyCode.R, "if (vc.getY()==0) { vc.setY(-3) ; ac.setY(0.05) }");
		 e.add(x);

		
		for (int i=1;i<35;i++){

			Entity p = new AbstractBlock(i);
			if (i!=12){
			p.addComponent(new LocationComponent(i*50,200));
			}else{
				p.addComponent(new LocationComponent(i*50,50));
			}
			p.addComponent(new SpriteComponent(("dirt.jpg")));

			ImagePropertiesComponent xpc = new ImagePropertiesComponent();
			xpc.setHeight(50);
			xpc.setWidth(50);
			p.addComponent(xpc);
			p.addComponent(new LabelComponent("Blok"));
			p.addComponent(new TypeComponent(EntityType.Block));
			p.addComponent(new CollidableComponent(true));
			e.add(p);
		}
		Entity pr = new AbstractBreakableBox(2356);
		pr.addComponent(new LocationComponent(700,150));
		pr.addComponent(new SpriteComponent(("platform_tile_035.png")));

		ImagePropertiesComponent xpcr = new ImagePropertiesComponent();
		xpcr.setHeight(50);
		xpcr.setWidth(50);
		pr.addComponent(xpcr);
		pr.addComponent(new LabelComponent("Blok"));
		pr.addComponent(new TypeComponent(EntityType.Block));
		e.add(pr);
		Entity y = new AbstractPowerup(101);
		y.addComponent(new LocationComponent(1000,150));
		y.addComponent(new SpriteComponent(("platform_tile_057.png")));
		ImagePropertiesComponent yc = new ImagePropertiesComponent();
		yc.setHeight(50);
		yc.setWidth(50);
		y.addComponent(yc);
		y.addComponent(new VelocityComponent(0,0));
		y.addComponent(new LabelComponent("Blok"));

		
		//BLOCK

		y.addComponent(new TypeComponent(EntityType.Block));

		Entity p = new AbstractMysteryBlock(102,y); 
		p.addComponent(new LocationComponent(900,50));
		p.addComponent(new SpriteComponent(("platform_tile_023.png")));
		ImagePropertiesComponent xpc = new ImagePropertiesComponent();
		xpc.setHeight(50);
		xpc.setWidth(50);
		p.addComponent(xpc);
		p.addComponent(new LabelComponent("Blok"));
		e.add(p);
		
		//GOAL
		Entity goal = new AbstractGoal(106);
		goal.addComponent(new LocationComponent(800, 20));
		goal.addComponent(new SpriteComponent(("sand.jpg")));
		ImagePropertiesComponent goalc = new ImagePropertiesComponent();
		goalc.setHeight(50);
		goalc.setWidth(50);
		goal.addComponent(new LabelComponent("Goal"));
		goal.addComponent(goalc);
		e.add(goal);


		p.addComponent(new TypeComponent(EntityType.Block));


		e.add(p);
		Entity portal2 = createPortal();
		
		e.add(portal2);
		e.add(createPortal2());
		
		

		myEntityManager = new EntityManager(e);


		myEngines = Arrays.asList(new InputEngine(myEntityManager), new MovementEngine(myEntityManager), new CollisionEngine(myEntityManager), new TimeEngine(myEntityManager));
		return new GameData(0,0, (IRestrictedEntityManager) myEntityManager, 0, (LocationComponent) getMainCharacter().getComponent(ComponentType.Location),"" );
	}
	
	//for testing
	public void addCamera(Camera c) {
		myEntityManager.changed(c);
	}
	
	private Entity createPortal() {
		Entity portal2 = new Entity(110);
		portal2.addComponent(new LocationComponent(450, 125));
		portal2.addComponent(new SpriteComponent("platform_tile_063.png"));
		SideCollisionComponent scc = new SideCollisionComponent(CollisionComponentType.Top);
		scc.addActionForLabel(new LabelComponent("grrraah"), new Teleport(100, 100));
		CollisionComponentsHandler cch = new CollisionComponentsHandler();
		cch.addCollisionComponent(scc);
		portal2.addComponent(cch);
		portal2.addComponent(new TypeComponent(EntityType.Block));

		ImagePropertiesComponent ipc = new ImagePropertiesComponent();
		ipc.setHeight(50);
		ipc.setWidth(50);
		portal2.addComponent(ipc);
		portal2.addComponent(new CollidableComponent(true));
		return portal2;
	}
	
	private Entity createPortal2() {
		Entity portal2 = new Entity(112);
		portal2.addComponent(new LocationComponent(1500, 125));
		portal2.addComponent(new SpriteComponent("portal.png"));
		SideCollisionComponent scc = new SideCollisionComponent(CollisionComponentType.Right);
		scc.addActionForLabel(new LabelComponent("grrraah"), new Teleport(100, 100));
		CollisionComponentsHandler cch = new CollisionComponentsHandler();
		cch.addCollisionComponent(scc);
		portal2.addComponent(cch);
		portal2.addComponent(new TypeComponent(EntityType.Block));

		ImagePropertiesComponent ipc = new ImagePropertiesComponent();
		ipc.setHeight(50);
		ipc.setWidth(50);
		portal2.addComponent(ipc);
		portal2.addComponent(new CollidableComponent(true));
		return portal2;
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