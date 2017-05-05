package entity;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import components.IComponent;
import components.entityComponents.ComponentType;
import entity.restricted.IRestrictedEntity;
import entity.restricted.IRestrictedEntityManager;

/**
 * This interface is for the EntityManager object which should store entity data
 * and provide public methods for engines to use in order to get appropriate
 * encapsulated/limited information
 * 
 * In order to give it more flexibility, the returns should be some
 * encapsulation+ (concrete/interface) rather than the
 * List<ArrayList<IComponent>>
 * 
 * @author Bilva
 *
 */
public interface IEntityManager {
	/**
	 * 
	 * @param certainComponent
	 * @return
	 */
	public Map<Integer, IComponent> getCertainComponents(
			ComponentType certainComponent);

	/**
	 * Returns a copy of the current entity manager with no pointers
	 * @return
	 */

	public IEntityManager copy();
	
	/**
	 * Returns a collection of stored entities
	 * @return
	 */
	public Collection<IEntity> getEntities();

	/**
	 * For notifying observer of change
	 * @param o
	 */
	void changed(Object o);
}
