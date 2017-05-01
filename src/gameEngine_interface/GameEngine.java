package gameEngine_interface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import actions.PointsAction;
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
import components.entityComponents.ControllableComponent;
import components.entityComponents.DamagedComponent;
import components.entityComponents.EntityType;
import components.entityComponents.GoalComponent;
import components.entityComponents.HealthComponent;
import components.entityComponents.ImagePropertiesComponent;
import components.entityComponents.KeyInputComponent;
import components.entityComponents.LabelComponent;
import components.entityComponents.LocationComponent;
import components.entityComponents.ObjectCreationComponent;
import components.entityComponents.OrientationComponent;
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
import controller.WorldAnimator;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.input.KeyCode;
import data_interfaces.Communicator;

import data_interfaces.EngineCommunication;
import data_interfaces.InfiniteEnum;
import engines.AIEngine;
import engines.AbstractEngine;
import engines.CollisionEngine;
import engines.IEngine;
import engines.InfiniteEngine;
import engines.InputEngine;
import engines.MovementEngine;
import engines.TimeEngine;
import entity.Entity;
import entity.EntityManager;
import entity.GPEntityManager;
import entity.IEntity;
import entity.IEntityManager;
import entity.SplashData;
import entity.presets.AbstractBlock;
import entity.presets.AbstractBreakableBox;
import entity.presets.AbstractGoal;
import entity.presets.AbstractPowerup;
import entity.restricted.IRestrictedEntityManager;
import gameEngine.chooser.GameEngineChooser;
import gameEngine.chooser.IGameEngineChooser;
import gameView.userInput.IRestrictedUserInputData;
import gamedata.GameData;
import gamedata.GameDataFactory;
import gamedata.IGameData;
import gamedata.IRestrictedGameData;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.input.KeyCode;
/**
 * Basic GameEngine class Note: the engines must be created in someway, likely
 * via reflection
 * 
 * @author Bilva
 *
 */
public class GameEngine implements GameEngineInterface {
	private IEntityManager myEntityManager;
	private List<IEngine> myEngines;
	private IGameData myGameData;
	private IGameEngineChooser myGameEngineChooser;
	private int numUpdates;
	private boolean sliderPause;
	private List<IEntityManager> myEntityManagers;
	private List<IEntityManager> previousEntityManagers;
	private EntityLoader myEntityLoader;
	public static final int SAVE_FREQUENCY = WorldAnimator.FRAMES_PER_SECOND;
	
	public GameEngine(IRestrictedUserInputData data){
		initializeUserData(data);
		previousEntityManagers = new ArrayList<IEntityManager>();
		sliderPause = false;
	}
	private void initializeUserData(IRestrictedUserInputData data){
		ReadOnlyDoubleProperty p = data.getRewind();
		p.addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) {
            	if (old_val.doubleValue()!=new_val.doubleValue()){
                    rewindState(old_val.doubleValue(),new_val.doubleValue());   
            	}

            }
        });
	}
	
	public IRestrictedGameData loadData(EngineCommunication c){	


		
		//REAL USE THIS
		myEntityManagers = c.getIEntityManagers();
		myEntityManager = myEntityManagers.get(0);
		myGameEngineChooser = new GameEngineChooser(myEntityManager, c.getInfinite());
		myEngines = myGameEngineChooser.getEngines();
		myEntityLoader = new EntityLoader(myEntityManager);
		
		LocationComponent lc = (LocationComponent) getMainCharacter().getComponent(ComponentType.Location);

		myGameData = new GameData(0,0,(IRestrictedEntityManager) myEntityManager, 0, lc, new ArrayList<String>(),"");
		return (IRestrictedGameData) myGameData;
	}
	
	public List<IEntityManager> save(){
		List<IEntityManager> mySaveState = new ArrayList<IEntityManager>();
		for (IEntityManager em : myEntityManagers){
			mySaveState.add(em.copy());
		}
		return mySaveState;
	}

	/**
	 * Runs each Engine in my Engine
	 */
	@Override
	public void handleUpdates(Collection<KeyCode> keysPressed) {
		resetStoredStates();
		saveNewEntityManager();
		for (IEngine s : myEngines){
			IRestrictedGameData rgd = s.update(keysPressed, (IRestrictedGameData) myGameData);
			updateLevel(rgd);
			GameDataFactory gameDataFactory = new GameDataFactory();
			gameDataFactory.updateGameData(myGameData,rgd);		
		}
	}
	private void updateLevel(IRestrictedGameData restrictedGameData){
		if (myGameData.getLevel().intValue()!=restrictedGameData.getLevel().intValue()){
			myEntityLoader.loadNew(myEntityManagers.get(restrictedGameData.getLevel().intValue()));
			previousEntityManagers.clear();
		}
	}
	private void resetStoredStates(){
		if (sliderPause==true){
			sliderPause=false;
			previousEntityManagers.clear();
		}
	}
	
	private IEntity getMainCharacter(){
		for(IEntity e : myEntityManager.getEntities()){
			if(e.hasComponent(ComponentType.KeyInput)){
				return e;
			}
		}
		return null;
	}
	
//	public void addCamera(Camera c) {
//		myEntityManager.changed(c);
//	}
	
	private void rewindState(Double old, Double next){
		sliderPause=true;
		Integer previousIndex = (Double.valueOf(previousEntityManagers.size()*old)).intValue();
		Integer index = (Double.valueOf(previousEntityManagers.size()*next)).intValue();
		if (next==1){index--;};
		if (previousIndex!=index){
			myEntityLoader.loadNew(previousEntityManagers.get(index));
		}
	}
	private void saveNewEntityManager() {
		numUpdates++;
		if (numUpdates % SAVE_FREQUENCY*10 == 0) {
			previousEntityManagers.add((myEntityManager.copy()));
			
		}
		while (previousEntityManagers.size()>25) {
			previousEntityManagers.remove(0);
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
	
	private EntityManager loadFakeManager() {
		Collection<IEntity> e = new ArrayList<IEntity>();
		Collection<IEntity> e7 = new ArrayList<IEntity>();
		IEntity x = new Entity(0);
		x.addComponent(new LocationComponent(100,150));
		x.addComponent(new SpriteComponent(("mario_step2.gif")));
		ImagePropertiesComponent xc = new ImagePropertiesComponent();
		x.addComponent(new CheckCollisionComponent(true));
		xc.setHeight(50);
		xc.setWidth(50);
		x.addComponent(xc);
		x.addComponent(new VelocityComponent(0,0));
		x.addComponent(new AccelerationComponent(0,0));
		x.addComponent(new CollidableComponent(true));
		x.addComponent(new LabelComponent("grrraah"));
		x.addComponent(new KeyInputComponent());
		x.addComponent(new TypeComponent(EntityType.Player));


//		Map<Integer, String> collection = new HashMap<Integer, String>();
//		collection.put(0, "mario_step1.gif");
//		collection.put(1, "mario_step2.gif");
//		collection.put(2, "mario_step3.gif");
//		//ImageChangeAction ica = new ImageChangeAction(collection);
//		Map<Integer, String> collection2 = new HashMap<Integer,String>();
//		collection2.put(0, "mario_leftstep1.gif");
//		collection2.put(1, "mario_leftstep2.gif");
//		collection2.put(2, "mario_leftstep3.gif");
//		ImageChangeAction ica2 = new ImageChangeAction(collection2);
//		Map<Integer, String> collection3 = new HashMap<Integer, String>();
//		collection3.put(0, "mario_jump.gif");
//		ImageChangeAction ica3 = new ImageChangeAction(collection3);


		x.addComponent(new GoalComponent());
		x.addComponent(new TerminalVelocityComponent(5,5));
		Entity y2 = new Entity(201);
		y2.addComponent(new LocationComponent(800,150));
		y2.addComponent(new SpriteComponent(("Feuer46.GIF")));
		ImagePropertiesComponent yc2 = new ImagePropertiesComponent();
		yc2.setHeight(50);
		yc2.setWidth(50);
		y2.addComponent(yc2);
		y2.addComponent(new VelocityComponent(3,0));
		y2.addComponent(new LabelComponent("grrraa"));
		y2.addComponent(new CollidableComponent(true));
		y2.addComponent(new TimeComponent(new RemoveAction(), 3000));
		y2.addComponent(new TypeComponent(EntityType.Projectile));
		y2.addComponent(new CheckCollisionComponent(true));
		x.addComponent(new ObjectCreationComponent(y2));
		TimeComponent time = new TimeComponent(new Reload(), 1000);
		x.addComponent(time);
		((KeyInputComponent) x.getComponent(ComponentType.KeyInput)).addToMap(KeyCode.V, new ShootAction());
		((KeyInputComponent) x.getComponent(ComponentType.KeyInput)).addToMap(KeyCode.W, new JumpAction());
//		((KeyInputComponent) x.getComponent(ComponentType.KeyInput)).addToMap(KeyCode.W, ica3);
		((KeyInputComponent) x.getComponent(ComponentType.KeyInput)).addToMap(KeyCode.D, new RightAction());

//		((KeyInputComponent) x.getComponent(ComponentType.KeyInput)).addToMap(KeyCode.D, ica);

		((KeyInputComponent) x.getComponent(ComponentType.KeyInput)).addToMap(KeyCode.D, new PointsAction(100));
		((KeyInputComponent) x.getComponent(ComponentType.KeyInput)).addToMap(KeyCode.A, new LeftAction());
//		((KeyInputComponent) x.getComponent(ComponentType.KeyInput)).addToMap(KeyCode.A, ica2);
		((KeyInputComponent) x.getComponent(ComponentType.KeyInput)).addToMap(KeyCode.R, "if (vc.getY()==0) { vc.setY(-3) ; ac.setY(0.05) }");
		//
		//
		//		((KeyInputComponent) x.getComponent(ComponentType.KeyInput)).addToMap(KeyCode.T, "REMOVE");
		e.add(x);
		return new EntityManager(e);
	}
	public List<IEntityManager> dummyLoad(){
		Collection<Entity> e = new ArrayList<Entity>();
		Collection<Entity> e7 = new ArrayList<Entity>();
		SplashData sd = new SplashData(1000, "Title", "Instructions", "background1.png");
		e.add(sd);
		
		Entity x = new Entity(0);
		x.addComponent(new LocationComponent(100,150));
		x.addComponent(new SpriteComponent(("mario_step2.gif")));
		ImagePropertiesComponent xc = new ImagePropertiesComponent();
		x.addComponent(new CheckCollisionComponent(true));
		xc.setHeight(50);
		xc.setWidth(50);
		x.addComponent(xc);
		x.addComponent(new VelocityComponent(0,0));
		x.addComponent(new AccelerationComponent(0,0));
		x.addComponent(new CollidableComponent(true));
		x.addComponent(new LabelComponent("grrraah"));
		x.addComponent(new KeyInputComponent());
		x.addComponent(new TypeComponent(EntityType.Player));
		x.addComponent(new HealthComponent(100));
		x.addComponent(new DamagedComponent());
		x.addComponent(new TerminalVelocityComponent(10,10));
		x.addComponent(new ControllableComponent(true));
		x.addComponent(new OrientationComponent());

		List<String> collection = new ArrayList<String>();
		collection.add("mario_step1.gif");
		collection.add("mario_step2.gif");
		collection.add("mario_step3.gif");
//		ImageChangeAction ica = new ImageChangeAction(collection);
//		List<String> collection2 = new ArrayList<String>();
//		collection2.add("mario_leftstep1.gif");
//		collection2.add("mario_leftstep2.gif");
//		collection2.add("mario_leftstep3.gif");
//		ImageChangeAction ica2 = new ImageChangeAction(collection2);
//		List<String> collection3 = new ArrayList<String>();
//		collection3.add("mario_jump.gif");
//		ImageChangeAction ica3 = new ImageChangeAction(collection3);

		x.addComponent(new GoalComponent());

		Entity y2 = new Entity(201);
		y2.addComponent(new LocationComponent(800,150));
		y2.addComponent(new SpriteComponent(("Feuer46.GIF")));
		ImagePropertiesComponent yc2 = new ImagePropertiesComponent();
		yc2.setHeight(50);
		yc2.setWidth(50);
		y2.addComponent(yc2);
		y2.addComponent(new VelocityComponent(3,0));
		y2.addComponent(new LabelComponent("grrraa"));
		y2.addComponent(new CollidableComponent(true));
		y2.addComponent(new TimeComponent(new RemoveAction(), 3000));
		y2.addComponent(new TypeComponent(EntityType.Projectile));
		y2.addComponent(new CheckCollisionComponent(true));
		x.addComponent(new ObjectCreationComponent(y2));
		TimeComponent time = new TimeComponent(new Reload(), 1000);
		x.addComponent(time);
		((KeyInputComponent) x.getComponent(ComponentType.KeyInput)).addToMap(KeyCode.V, new ShootAction());
		((KeyInputComponent) x.getComponent(ComponentType.KeyInput)).addToMap(KeyCode.W, new JumpAction());

//		((KeyInputComponent) x.getComponent(ComponentType.KeyInput)).addToMap(KeyCode.W, ica3);
		((KeyInputComponent) x.getComponent(ComponentType.KeyInput)).addToMap(KeyCode.D, new RightAction());
//		((KeyInputComponent) x.getComponent(ComponentType.KeyInput)).addToMap(KeyCode.D, ica);
		((KeyInputComponent) x.getComponent(ComponentType.KeyInput)).addToMap(KeyCode.D, new PointsAction(100));
		((KeyInputComponent) x.getComponent(ComponentType.KeyInput)).addToMap(KeyCode.A, new LeftAction());
//		((KeyInputComponent) x.getComponent(ComponentType.KeyInput)).addToMap(KeyCode.A, ica2);

		((KeyInputComponent) x.getComponent(ComponentType.KeyInput)).addToMap(KeyCode.R, "if (vc.getY()==0) { vc.setY(-3) ; ac.setY(0.05) }");
		//
		//
		//		((KeyInputComponent) x.getComponent(ComponentType.KeyInput)).addToMap(KeyCode.T, "REMOVE");
		e.add(x);
		e7.add(x.newCopy(100));
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
//		e.add(x);
		for (int i=1;i<10;i++){
			Entity p = new AbstractBlock(i);
			//if (i!=12){
				p.addComponent(new LocationComponent(i*50,200));
			//}else{
			//	p.addComponent(new LocationComponent(i*50,50));
			//}
			p.addComponent(new SpriteComponent(("dirt.jpg")));
			ImagePropertiesComponent xpc = new ImagePropertiesComponent();
			xpc.setHeight(50);
			xpc.setWidth(50);
			p.addComponent(xpc);
			p.addComponent(new LabelComponent("Blok"));
			p.addComponent(new TypeComponent(EntityType.Block));
			p.addComponent(new CollidableComponent(true));
			e.add(p);
			e7.add(p);
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


//		y.addComponent(new TypeComponent(EntityType.Block));
//		y.addComponent(new TypeComponent(EntityType.Block));
//>>>>>>> rewind
//		Entity p = new AbstractMysteryBlock(102,y); 
//		p.addComponent(new LocationComponent(900,50));
//		p.addComponent(new SpriteComponent(("platform_tile_023.png")));
//		ImagePropertiesComponent xpc = new ImagePropertiesComponent();
//		xpc.setHeight(50);
//		xpc.setWidth(50);
//		p.addComponent(xpc);
//		p.addComponent(new LabelComponent("Blok"));
//		e.add(p);
		Entity goal = new AbstractGoal(243);
		goal.addComponent(new LocationComponent(700,100));
		goal.addComponent(new SpriteComponent(("platform_tile_053.png")));
		goal.addComponent(new ImagePropertiesComponent(50,50));
		goal.addComponent(new LabelComponent("Goal"));
		e.add(goal);
//		for (int i= 0; i<2; i++){
//			Entity enemy = new AbstractEnemy(106+i);
//			if (i==0){
//				enemy.addComponent(new LocationComponent(800, 20));
//			}else{
//				enemy.addComponent(new LocationComponent(1200,150));
//			}
//			enemy.addComponent(new SpriteComponent(("sand.jpg")));
//			ImagePropertiesComponent goalc = new ImagePropertiesComponent();
//			goalc.setHeight(50);
//			enemy.addComponent(new StepComponent(50));
//			enemy.addComponent(new VelocityComponent(-1,0));
//			enemy.addComponent(new LabelComponent("wecamefromnothingtosomething"));
//			goalc.setWidth(50);
//			enemy.addComponent(goalc);
//			enemy.addComponent(new StrengthComponent(5.0));
//			e.add(enemy);
//			enemy.addComponent(new CheckCollisionComponent(true));
//		}

		Collection<IEntity> e1 = new ArrayList<IEntity>();
		for (Entity exp : e) {
			e1.add(exp);
		}
//		Entity u = new Entity(0);
//		u.addComponent(new LocationComponent(100,150));
//		u.addComponent(new SpriteComponent(("mario_step2.gif")));
//		ImagePropertiesComponent uc = new ImagePropertiesComponent();
//		u.addComponent(new CheckCollisionComponent(true));
//		uc.setHeight(50);
//		uc.setWidth(50);
//		u.addComponent(uc);
//		u.addComponent(new VelocityComponent(0,0));
//		u.addComponent(new AccelerationComponent(0,0));
//		u.addComponent(new CollidableComponent(true));
//		u.addComponent(new LabelComponent("grrraah"));
//		u.addComponent(new KeyInputComponent());
//		u.addComponent(new TypeComponent(EntityType.Player));
//
//		List<String> collectionu = new ArrayList<String>();
//		collectionu.add("mario_step1.gif");
//		collectionu.add("mario_step2.gif");
//		collectionu.add("mario_step3.gif");
//		ImageChangeAction icau = new ImageChangeAction(collectionu);
//		List<String> collection2u = new ArrayList<String>();
//		collection2u.add("mario_leftstep1.gif");
//		collection2u.add("mario_leftstep2.gif");
//		collection2u.add("mario_leftstep3.gif");
//		ImageChangeAction ica2u = new ImageChangeAction(collection2u);
//		List<String> collection3u = new ArrayList<String>();
//		collection3.add("mario_jump.gif");
//		ImageChangeAction ica3u = new ImageChangeAction(collection3u);
//
//		u.addComponent(new GoalComponent());
//		u.addComponent(new TerminalVelocityComponent(5,5));
//		Entity y2u = new Entity(201);
//		y2u.addComponent(new LocationComponent(800,150));
//		y2u.addComponent(new SpriteComponent(("Feuer46.GIF")));
//		ImagePropertiesComponent yc2u = new ImagePropertiesComponent();
//		yc2u.setHeight(50);
//		yc2u.setWidth(50);
//		y2u.addComponent(yc2u);
//		y2u.addComponent(new VelocityComponent(3,0));
//		y2u.addComponent(new LabelComponent("grrraa"));
//		y2u.addComponent(new CollidableComponent(true));
//		y2u.addComponent(new TimeComponent(new RemoveAction(), 3000));
//		y2u.addComponent(new TypeComponent(EntityType.Projectile));
//		y2u.addComponent(new CheckCollisionComponent(true));
//		u.addComponent(new ObjectCreationComponent(y2));
//		TimeComponent timeu = new TimeComponent(new Reload(), 1000);
//		u.addComponent(timeu);
//		((KeyInputComponent) u.getComponent(ComponentType.KeyInput)).addToMap(KeyCode.V, new ShootAction());
//		((KeyInputComponent) u.getComponent(ComponentType.KeyInput)).addToMap(KeyCode.W, new JumpAction());
//		((KeyInputComponent) u.getComponent(ComponentType.KeyInput)).addToMap(KeyCode.W, ica3);
//		((KeyInputComponent) u.getComponent(ComponentType.KeyInput)).addToMap(KeyCode.D, new RightAction());
//		((KeyInputComponent) u.getComponent(ComponentType.KeyInput)).addToMap(KeyCode.D, ica);
//		((KeyInputComponent) u.getComponent(ComponentType.KeyInput)).addToMap(KeyCode.D, new PointsAction(100));
//		((KeyInputComponent) u.getComponent(ComponentType.KeyInput)).addToMap(KeyCode.A, new LeftAction());
//		((KeyInputComponent) u.getComponent(ComponentType.KeyInput)).addToMap(KeyCode.A, ica2);
//		((KeyInputComponent) u.getComponent(ComponentType.KeyInput)).addToMap(KeyCode.R, "if (vc.getY()==0) { vc.setY(-3) ; ac.setY(0.05) }");
//		e7.add(u);
//		//
//		//
//		//		((KeyInputComponent) x.getComponent(ComponentType.KeyInput)).addToMap(KeyCode.T, "REMOVE");
		Collection<IEntity> e8 = new ArrayList<IEntity>();
		for (Entity exp : e7) {
			e8.add(exp);
		}
		myEntityManager = new EntityManager(e1);
		myEntityManagers= new ArrayList<IEntityManager>();
		myEntityManagers.add(myEntityManager);
		myEntityManagers.add(new EntityManager(e8));
		List<String> listl = new ArrayList<String>();
		listl.add("");
		myGameData= new GameData(0,0, (IRestrictedEntityManager) myEntityManager, 0, (LocationComponent) getMainCharacter().getComponent(ComponentType.Location), listl, "" );

		myEngines = Arrays.asList(new InputEngine(myEntityManager), new MovementEngine(myEntityManager), new CollisionEngine(myEntityManager), new TimeEngine(myEntityManager),new AIEngine(myEntityManager), new InfiniteEngine(myEntityManager,InfiniteEnum.None));
		return myEntityManagers;
//		return myGameData;
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
}