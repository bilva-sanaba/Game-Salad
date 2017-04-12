package engines.subengines;

import java.util.List;
import components.entityComponents.ComponentType;
import engines.CollisionPair;
import entity.Entity;
import entity.IEntity;
import entity.IEntityManager;
/**
 * The ISubEngine interface is the interface for engines that are put into action when a collision occurs. Each subEngine will need to have a public method handleCollision that the CollisionHandler can call
 * @author Hamsa
 *
 */
public interface ISubEngine {
	
	/**
	 * Entity manager is necessary to get the necessary Components
	 * @param entManager
	 */
	public void addEntityManager(IEntityManager entManager);
	
	/**
	 * Each subengine will handle a collision in a different way by checking different components and acting on them.
	 * The return type is List<Entity> since this method should return any new Entities created from handling the collision.
	 */
	public List<IEntity> handleCollision(CollisionPair collisionObjectComponents);
	
	/**
	 * This method will return the ComponentType that the subEngine needs so that it receives that component, and nothing more.
	 * @return
	 */
	public List<ComponentType> getNecessaryComponents(String sideOfCollision);
	
}