//package engines;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//
//import components.entityComponents.CheckCollisionComponent;
//import components.entityComponents.CollidableComponent;
//import components.entityComponents.ComponentType;
//import components.entityComponents.EntityType;
//import components.IComponent;
//import components.entityComponents.LabelComponent;
//import components.entityComponents.TypeComponent;
//import controller.Camera;
//import engines.subengines.GeneralPostCollisionHandler;
//import engines.subengines.ISubEngine;
//import entity.Entity;
//import entity.IEntity;
//import entity.IEntityManager;
//import entity.restricted.IRestrictedEntity;
//import gamedata.IRestrictedGameData;
//import javafx.scene.input.KeyCode;
//import voogasalad.util.paint.ImageRefiner;
///**
// * This engine handles all collisions
// * When update is called it should use all needed CollisionSubEngines
// * (This provides an important area for design choices as the neededComponents method would need to be changed if
// * more collision sub engines are added which use different components)
// * @author Bilva, Hamsa
// *
// */
//public class CollisionEngine extends AbstractEngine {
//	
//	private List<ISubEngine> subEngines;
//	private IEntityManager entManager;
//	private List<IEntity> newEntitiesCreated;
//	private ITwoObjectCollide collisionMethod = new ObjectCollisionAlgorithm();
//	private int numSubEnginesAdded;
//		public CollisionEngine(IEntityManager myEntityManager) {
//		super(myEntityManager);
//		subEngines = new ArrayList<ISubEngine>();
//		entManager = myEntityManager;
//		numSubEnginesAdded = 0;
//		
//	}
//	
//	public void addEngine(ISubEngine newSubEngine) {
//		subEngines.add(newSubEngine);
//		numSubEnginesAdded++;
//		
//	}
//	
//	/**
//	 * This method goes through all entities, takes their locations, and figures out if any are bumping into each other. If any are,
//	 * it sends components of those objects to subEngines which can handle effects (such moving the entity or changing the entities image, etc.)
//	 * @return 
//	 */
//
//	private IRestrictedGameData checkCollisionsOccurred(IRestrictedGameData gd) {
//		List<IEntity> entities2 = (List<IEntity>) entManager.getEntities();
//		IEntity[] entities = new IEntity[entities2.size()];
//		Camera cam = null;
//		for (int i=0;i<entities.length;i++) {
//			entities[i] = entities2.get(i);
//			TypeComponent type = (TypeComponent) entities[i].getComponent(ComponentType.Type);
//			if (type!=null && type.getType().equals(EntityType.Camera)) {
//				cam = (Camera) entities[i];
//			}
//				
//		}
//		for (int i=0;i<entities.length;i++) {
//			IEntity entityOne = entities[i];
//			CheckCollisionComponent check = (CheckCollisionComponent) entityOne.getComponent(ComponentType.CheckCollision);
//			CollidableComponent collidable = (CollidableComponent) entityOne.getComponent(ComponentType.Collidable);
//			if (check!= null && check.getCheckCollision() && collidable != null && collidable.getCollide() /*&& cam.withinCameraBounds(entityOne)*/) {
//				for (int j=i+1;j<entities.length;j++) {
//					IEntity entityTwo = entities[j];
//					CollidableComponent collidableE2 = (CollidableComponent) entityTwo.getComponent(ComponentType.Collidable);
//					if (collidableE2.getCollide() && entityOne!=entityTwo /*&& cam.withinCameraBounds(entityTwo)*/) {
//						checkIndividualCollision(entityOne, entityTwo, gd);
//					}
//				}
//			}
//		}
//		return gd;
//	}
//	
//	
//	
//	
//	private void checkIndividualCollision(IEntity entityOne, IEntity entityTwo, IRestrictedGameData gd) {
//		
//		String collisionSide = collisionMethod.collides(entityOne, entityTwo);
//		sendCollisionToSubEngines(entityOne, entityTwo, collisionSide, gd);
//		
//	}
//	
//	private void sendCollisionToSubEngines(IEntity entityOne, IEntity entityTwo, String collisionSide, IRestrictedGameData gd) {
//		boolean collisionOccurs = false;
//		if (!collisionSide.equals(ITwoObjectCollide.NONE)) {
//			collisionOccurs = true;
//		}
//		
//		if (collisionOccurs) {
//			
//			for (ISubEngine engine : subEngines) {
//				gd = engine.handleCollision(entityOne, entityTwo, collisionSide, entManager,gd);
////				newEntitiesCreated.addAll(engine.handleCollision(entityOne, entityTwo, collisionSide, entManager,gd));
//			}
//			
//		}
//	}
//	@Override
//	public List<ComponentType> neededComponents() {
//		// TODO Auto-generated method stub
//		return new ArrayList<ComponentType>();
//	}
//	public IRestrictedGameData update(Collection<KeyCode> keys,IRestrictedGameData gd) {
//		IRestrictedGameData rgd = gd;
//		newEntitiesCreated = new ArrayList<IEntity>();
//		if (numSubEnginesAdded<=0) {
//			addEngine(new GeneralPostCollisionHandler());
//		}
//		rgd=checkCollisionsOccurred(rgd);
//		for (IRestrictedEntity e :rgd.getRestrictedEntityManager().getRestrictedEntities()){
////			entManager.getEntities().add(e);
////			entManager.changed(e);
//		}
//		return rgd;
//	}
//	
//}
package engines;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import components.entityComponents.CheckCollisionComponent;
import components.entityComponents.CollidableComponent;
import components.entityComponents.ComponentType;
import components.entityComponents.EntityType;
import components.entityComponents.KeyInputComponent;
import components.IComponent;
import components.entityComponents.LabelComponent;
import components.entityComponents.LocationComponent;
import components.entityComponents.TypeComponent;
import controller.Camera;
import engines.subengines.GeneralPostCollisionHandler;
import engines.subengines.ISubEngine;
import entity.Entity;
import entity.IEntity;
import entity.IEntityManager;
import entity.restricted.IRestrictedEntity;
import gamedata.IRestrictedGameData;
import javafx.scene.input.KeyCode;
import voogasalad.util.paint.ImageRefiner;
/**
 * This engine handles all collisions
 * When update is called it should use all needed CollisionSubEngines
 * (This provides an important area for design choices as the neededComponents method would need to be changed if
 * more collision sub engines are added which use different components)
 * @author Bilva, Hamsa
 *
 */
public class CollisionEngine extends AbstractEngine {
	
	private List<ISubEngine> subEngines;
	private IEntityManager entManager;
	private List<IEntity> newEntitiesCreated;
	private ITwoObjectCollide collisionMethod = new ObjectCollisionAlgorithm();
	private int numSubEnginesAdded;
	
	public CollisionEngine(IEntityManager myEntityManager) {
		super(myEntityManager);
		subEngines = new ArrayList<ISubEngine>();
		entManager = myEntityManager;
		numSubEnginesAdded = 0;
		
	}
	
	public void addEngine(ISubEngine newSubEngine) {
		subEngines.add(newSubEngine);
		numSubEnginesAdded++;
		
	}
	
	private void withinBoundsOfMainPlayer(IEntity mainPlayer, IEntity otherEntity) {
		
	}
	
	/**
	 * This method goes through all entities, takes their locations, and figures out if any are bumping into each other. If any are,
	 * it sends components of those objects to subEngines which can handle effects (such moving the entity or changing the entities image, etc.)
	 * @return 
	 */


	private IRestrictedGameData checkCollisionsOccurred(IRestrictedGameData gd) {
		List<IEntity> entities2 = (List<IEntity>) entManager.getEntities();
		IEntity[] entities = new IEntity[entities2.size()];
		IEntity mainPlayer = null;
		for (int i=0;i<entities.length;i++) {
			entities[i] = entities2.get(i);
			KeyInputComponent mainPlayerCheck = (KeyInputComponent) entities[i].getComponent(ComponentType.KeyInput);
			if (mainPlayerCheck!=null) {
				mainPlayer = entities[i];
			}
				
		}

		
		for (int i=0;i<entities.length;i++) {
			IEntity entityOne = entities[i];
			CheckCollisionComponent check = (CheckCollisionComponent) entityOne.getComponent(ComponentType.CheckCollision);
			CollidableComponent collidable = (CollidableComponent) entityOne.getComponent(ComponentType.Collidable);

			
			if (check!= null && check.getCheckCollision() && collidable != null && collidable.getCollide() /*&& cam.withinCameraBounds(entityOne)*/) {
				for (int j=0;j<entities.length;j++) {
					IEntity entityTwo = entities[j];
					CollidableComponent collidableE2 = (CollidableComponent) entityTwo.getComponent(ComponentType.Collidable);
					if (collidableE2 != null && collidableE2.getCollide() && entityOne!=entityTwo /*&& cam.withinCameraBounds(entityTwo)*/) {
						gd = checkIndividualCollision(entityOne, entityTwo, gd);
					}
					
				}
			}
		}
		return gd;
	}
	
	
	
	
	private IRestrictedGameData checkIndividualCollision(IEntity entityOne, IEntity entityTwo, IRestrictedGameData gd) {
		String collisionSide = collisionMethod.collides(entityOne, entityTwo);
		return sendCollisionToSubEngines(entityOne, entityTwo, collisionSide, gd);
		
	}
	
	private IRestrictedGameData sendCollisionToSubEngines(IEntity entityOne, IEntity entityTwo, String collisionSide, IRestrictedGameData gd) {
		boolean collisionOccurs = false;
		if (!collisionSide.equals(ITwoObjectCollide.NONE)) {
			collisionOccurs = true;
		}
		
		if (collisionOccurs) {
			for (ISubEngine engine : subEngines) {
				gd = engine.handleCollision(entityOne, entityTwo, collisionSide, entManager,gd);
//				newEntitiesCreated.addAll(engine.handleCollision(entityOne, entityTwo, collisionSide, entManager,gd));
			}
			
		}
		return gd;
	}

	public IRestrictedGameData update(Collection<KeyCode> keys,IRestrictedGameData gd) {
		IRestrictedGameData rgd = gd;
		newEntitiesCreated = new ArrayList<IEntity>();
		if (numSubEnginesAdded<=0) {
			addEngine(new GeneralPostCollisionHandler());
		}
		rgd=checkCollisionsOccurred(rgd);
		for (IRestrictedEntity e :rgd.getRestrictedEntityManager().getRestrictedEntities()){
//			entManager.getEntities().add(e);
//			entManager.changed(e);
		}
		
		return rgd;
	}
	
}