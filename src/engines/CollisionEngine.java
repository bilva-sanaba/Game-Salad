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
import components.IComponent;
import components.entityComponents.LabelComponent;
import components.entityComponents.TypeComponent;
import engines.subengines.GeneralPostCollisionHandler;
import engines.subengines.ISubEngine;
import engines.subengines.stopMovementAfterHit;
import entity.Entity;
import entity.IEntity;
import entity.IEntityManager;
import entity.restricted.IRestrictedEntity;
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
	
	/**
	 * This method goes through all entities, takes their locations, and figures out if any are bumping into each other. If any are,
	 * it sends components of those objects to subEngines which can handle effects (such moving the entity or changing the entities image, etc.)
	 */
	private void checkCollisionsOccurred() {
		List<IEntity> entities2 = (List<IEntity>) entManager.getEntities();
		IEntity[] entities = new IEntity[entities2.size()];
		for (int i=0;i<entities.length;i++) {
			entities[i] = entities2.get(i);
		}
		for (int i=0;i<entities.length;i++) {
			IEntity entityOne = entities[i];
			CheckCollisionComponent check = (CheckCollisionComponent) entityOne.getComponent(ComponentType.CheckCollision);
			CollidableComponent collidable = (CollidableComponent) entityOne.getComponent(ComponentType.Collidable);
			if (check!= null && check.getCheckCollision() && collidable != null && collidable.getCollide()) {
				for (int j=i+1;j<entities.length;j++) {
					IEntity entityTwo = entities[j];
					CollidableComponent collidableE2 = (CollidableComponent) entityTwo.getComponent(ComponentType.Collidable);
					if (collidableE2.getCollide() && entityOne!=entityTwo) {
						checkIndividualCollision(entityOne, entityTwo);
					}
				}
			}
		}
	}
	
	
	
	
	private void checkIndividualCollision(IEntity entityOne, IEntity entityTwo) {
		
		String collisionSide = collisionMethod.collides(entityOne, entityTwo);
		sendCollisionToSubEngines(entityOne, entityTwo, collisionSide);
		
	}
	
	private void sendCollisionToSubEngines(IEntity entityOne, IEntity entityTwo, String collisionSide) {
		boolean collisionOccurs = false;
		if (!collisionSide.equals(ITwoObjectCollide.NONE)) {
			collisionOccurs = true;
		}
		
		if (collisionOccurs) {
			
			for (ISubEngine engine : subEngines) {
				newEntitiesCreated.addAll(engine.handleCollision(entityOne, entityTwo, collisionSide, entManager));
			}
			
		}
	}
	@Override
	public List<ComponentType> neededComponents() {
		// TODO Auto-generated method stub
		return new ArrayList<ComponentType>();
	}
	public void update(Collection<KeyCode> keys) {
		
		newEntitiesCreated = new ArrayList<IEntity>();
		if (numSubEnginesAdded<=0) {
			addEngine(new GeneralPostCollisionHandler());
		}
		checkCollisionsOccurred();
		for (IEntity e : newEntitiesCreated){
			entManager.getEntities().add(e);
			entManager.changed(e);
		}
	}
	
}