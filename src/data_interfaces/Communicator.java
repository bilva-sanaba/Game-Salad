package data_interfaces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.w3c.dom.Element;

import actions.PointsAction;
import actions.Reload;
import actions.RemoveAction;
import actions.ShootAction;
import actions.SmartJump;
import actions.SmartShoot;
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
import components.entityComponents.MonsterType;
import components.entityComponents.MonsterTypeComponent;
import components.entityComponents.ObjectCreationComponent;
import components.entityComponents.OrientationComponent;
import components.entityComponents.SideCollisionComponent;
import components.entityComponents.SpriteComponent;
import components.entityComponents.StepComponent;
import components.entityComponents.StrengthComponent;
import components.entityComponents.TerminalVelocityComponent;
import components.entityComponents.TimeComponent;
import components.entityComponents.TypeComponent;
import components.entityComponents.VelocityComponent;
import components.keyExpressions.JumpAction;
import components.keyExpressions.LeftAction;
import components.keyExpressions.RightAction;
import controller.Camera;
import engines.AIEngine;
import engines.CollisionEngine;
import engines.InfiniteEngine;
import engines.InputEngine;
import engines.MovementEngine;
import engines.TimeEngine;
import engines.infinite.InfiniteEnum;
import entity.*;
import entity.presets.AbstractBlock;
import entity.presets.AbstractBreakableBox;
import entity.presets.AbstractEnemy;
import entity.presets.AbstractGoal;
import entity.presets.AbstractMysteryBlock;
import entity.presets.AbstractPowerup;
import entity.presets.DeathBlock;
import entity.presets.DoodleJumpPlatform;
import entity.restricted.IRestrictedEntityManager;
import gamedata.GameData;
import javafx.scene.input.KeyCode;

public class Communicator extends GameSavingDataTool implements EngineCommunication, PlayerCommunication {

//	private String fileName;
//	List<Map> results;
//
//	public Communicator(String s) {
//		System.out.println("COMMUNICATOR MADE: " + s);
//		fileName = s;
//		XMLPlacedParser xp = new XMLPlacedParser();
//		results = xp.getData(fileName);
//	}
//	
//	public List<IEntityManager> getIEntityManagers() {
//		Map <Integer, Map<Integer, Entity>> m = results.get(0);
//		List <IEntityManager> ret = new ArrayList<IEntityManager>();
//		List <IEntity> toBeAdded;
//		
//		System.out.println("m size: " + m.size());
//		
//		for (int i = 1; i <= m.size(); i++) {
//			System.out.println("THISSSSS happens");
//			toBeAdded = new ArrayList<IEntity>();
//			for (Integer j: m.get(i).keySet()) {
//				toBeAdded.add(m.get(i).get(j));
//				System.out.println("this happens");
//			}
//			ret.add(new EntityManager(toBeAdded));
//			
//		}
//		return ret;
//		
//	}
//
//
//
//	@Override
//	public List<LevelEntity> getLevelEntities() {
//		Map <Integer, LevelEntity> m = results.get(1);
//		List<LevelEntity> ret = new ArrayList<LevelEntity>();
//		
//		for (int i = 1; i <= m.size(); i++) {
//			ret.add(m.get(i));
//		}
//		System.out.println("HOW MANY LEVEL ENTITIES" + ret.size());
//		
//		return ret;
//	}
//
//
//
//	@Override
//	public SplashData getSplashEntity() {
//		return (SplashData) results.get(2).get(getSplashConstant());
//	}
//
//
//
//	@Override
//	public InfiniteEnum getInfinite() {
//		Map<Integer, LevelEntity> m = results.get(1);
//		System.out.println(m.get(1).getInfiniteEnum());
//		return m.get(1).getInfiniteEnum();
//	}
//	
//	public String getMusic() {
//		Map<Integer, LevelEntity> m = results.get(1);
//		return m.get(1).getMusic();
//	}
//	
//	public int getLives () {
//		Map <Integer, LevelEntity> m = results.get(1);
//		return m.get(1).getLives();
//	}
	
	

	public String getMusic() {
		Map<Integer, LevelEntity> m = results.get(1);
		return m.get(1).getMusic();
	}
	
	public int getLives () {
		Map <Integer, LevelEntity> m = results.get(1);
		return m.get(1).getLives();
	}
	
	
	
	private String fileName;
	List<Map> results;

	public Communicator(String s) {
		System.out.println("COMMUNICATOR MADE: " + s);
		fileName = s;
		XMLPlacedParser xp = new XMLPlacedParser();
		results = xp.getData(fileName);
	}
	
	public List<IEntityManager> getIEntityManagers() {
//		Map <Integer, Map<Integer, Entity>> m = results.get(0);
		List <IEntityManager> ret = new ArrayList<IEntityManager>();
		List <IEntity> toBeAdded;
		
//		System.out.println("m size: " + m.size());
//		
//		for (int i = 1; i <= m.size(); i++) {
//			System.out.println("THISSSSS happens");
//			toBeAdded = new ArrayList<IEntity>();
//			for (Integer j: m.get(i).keySet()) {
//				toBeAdded.add(m.get(i).get(j));
//				System.out.println("this happens");
//			}
//			ret.add(new EntityManager(toBeAdded));
//			
//		}
		return dummyLoad();
//		return ret;
		
	}



	@Override
	public List<LevelEntity> getLevelEntities() {
//		Map <Integer, LevelEntity> m = results.get(1);
		List<LevelEntity> ret = new ArrayList<LevelEntity>();
		LevelEntity l = new LevelEntity(0,500,500,"background1.png", "badboujee.wav",3);
		
		ret.add(l);
		
//		for (int i = 1; i <= m.size(); i++) {
//			ret.add(m.get(i));
//		}
//		System.out.println("HOW MANY LEVEL ENTITIES" + ret.size());
		
		return ret;
	}



	@Override
	public SplashData getSplashEntity() {
		SplashData s = new SplashData(1427, "sgsdg", "sghs", "background1.png");
//		return (SplashData) results.get(2).get(getSplashConstant());
		return s;
	}



	@Override
	public InfiniteEnum getInfinite() {
//		Map<Integer, LevelEntity> m = results.get(1);
//		System.out.println(m.get(1).getInfiniteEnum());
//		return m.get(1).getInfiniteEnum();
		return InfiniteEnum.Vertical;
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
		x.addComponent(new AccelerationComponent(0,.9));
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
		time.addAction(new PointsAction(100), 100);
		x.addComponent(time);
		((KeyInputComponent) x.getComponent(ComponentType.KeyInput)).addToMap(KeyCode.V, new ShootAction());
		((KeyInputComponent) x.getComponent(ComponentType.KeyInput)).addToMap(KeyCode.W, new JumpAction());

//		((KeyInputComponent) x.getComponent(ComponentType.KeyInput)).addToMap(KeyCode.W, ica3);
		((KeyInputComponent) x.getComponent(ComponentType.KeyInput)).addToMap(KeyCode.D, new RightAction());
//		((KeyInputComponent) x.getComponent(ComponentType.KeyInput)).addToMap(KeyCode.D, ica);
//		((KeyInputComponent) x.getComponent(ComponentType.KeyInput)).addToMap(KeyCode.D, new PointsAction(100));
		((KeyInputComponent) x.getComponent(ComponentType.KeyInput)).addToMap(KeyCode.A, new LeftAction());
//		((KeyInputComponent) x.getComponent(ComponentType.KeyInput)).addToMap(KeyCode.A, ica2);

		((KeyInputComponent) x.getComponent(ComponentType.KeyInput)).addToMap(KeyCode.R, "if (vc.getY()==0) { vc.setY(-3) ; ac.setY(0.05) }");
		//
		//
		//		((KeyInputComponent) x.getComponent(ComponentType.KeyInput)).addToMap(KeyCode.T, "REMOVE");
		e.add(x);
		e7.add(x.newCopy(32410));
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
		for (int i=1;i<35;i++){
			Entity p = new DoodleJumpPlatform(i);
			//if (i!=12){
				p.addComponent(new LocationComponent(500*Math.random(),i*50));
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
//		for (int i= 0; i<35; i++){
//			Entity pr = new DeathBlock(2356*(i+3));
//			pr.addComponent(new LocationComponent(i*50,400));
//			pr.addComponent(new SpriteComponent(("transparent.png")));
//			ImagePropertiesComponent xpcr = new ImagePropertiesComponent();
//			xpcr.setHeight(50);
//			xpcr.setWidth(50);
//			pr.addComponent(xpcr);
//			pr.addComponent(new LabelComponent("Blok"));
//			pr.addComponent(new TypeComponent(EntityType.Block));
//			e.add(pr);
//		}
		Entity pr = new DeathBlock(2356);
		pr.addComponent(new LocationComponent(700,150));
		pr.addComponent(new SpriteComponent(("platform_tile_063.png")));
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
//		Entity goal = new AbstractGoal(243);
//		goal.addComponent(new LocationComponent(700,100));
//		goal.addComponent(new SpriteComponent(("platform_tile_053.png")));
//		goal.addComponent(new ImagePropertiesComponent(50,50));
//		goal.addComponent(new LabelComponent("Goal"));
//		e.add(goal);
		Entity y3 = new AbstractEnemy(23401);
		y3.addComponent(new LocationComponent(800,150));
		y3.addComponent(new SpriteComponent(("sand.jpg")));
		ImagePropertiesComponent yc3 = new ImagePropertiesComponent();
		yc3.setHeight(20);
		yc3.setWidth(20);
		y3.addComponent(yc3);
		y3.addComponent(new VelocityComponent(3,0));
		y3.addComponent(new LabelComponent("grrraa"));
		y3.addComponent(new CollidableComponent(true));
		y3.addComponent(new TimeComponent(new RemoveAction(), 3000));
		
		for (int i= 0; i<2; i++){
			
			Entity enemy = new AbstractEnemy(106+i);
			if (i==0){
				enemy.addComponent(new LocationComponent(800, 20));
				enemy.addComponent(new ObjectCreationComponent(y3));
//				enemy.addComponent(new TimeComponent(new SmartShoot(), 2000));
			}else{
				enemy.addComponent(new LocationComponent(1200,150));

				
			}
			
			enemy.addComponent(new MonsterTypeComponent(MonsterType.LeftAndRight));
			enemy.addComponent(new SpriteComponent(("sand.jpg")));
			ImagePropertiesComponent goalc = new ImagePropertiesComponent();
			goalc.setHeight(50);
			enemy.addComponent(new StepComponent(50));
			enemy.addComponent(new VelocityComponent(-1,0));
			enemy.addComponent(new TypeComponent(EntityType.Player));
			goalc.setWidth(50);
			enemy.addComponent(goalc);
			enemy.addComponent(new StrengthComponent(5.0));
			e.add(enemy);
			enemy.addComponent(new LabelComponent("grrraah"));
			enemy.addComponent(new CheckCollisionComponent(true));
		}

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
		IEntityManager myEntityManager = new EntityManager(e1);
		List<IEntityManager> myEntityManagers= new ArrayList<IEntityManager>();
		myEntityManagers.add(myEntityManager);
		myEntityManagers.add(new EntityManager(e8));
		return myEntityManagers;
//		return myGameData;
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