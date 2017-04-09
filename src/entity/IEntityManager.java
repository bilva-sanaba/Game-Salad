package entity;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import components.ComponentType;
import components.IComponent;
import entity.restricted.IRestrictedEntity;
import entity.restricted.IRestrictedEntityManager;
/**
 * This interface is for the EntityManager object which should store entity data and provide public methods 
 * for engines to use in order to get appropriate encapsulated/limited information
 * 
 * In order to give it more flexibility, the returns should be some encapsulation+ (concrete/interface)
 * rather than the List<ArrayList<IComponent>>
 * @author Bilva
 *
 */
public interface IEntityManager {
	/**
	 * 
	 * @param certainComponent
	 * @return
	 */
	public Map<Integer, IComponent> getCertainComponents(ComponentType certainComponent);
	/**
	 * Returns an object storing components in a restricted form with pointers
	 * @return
	 */
	public IRestrictedEntityManager getRestricted();
	/**
	 * 
	 * @return
	 */
	public Map<IEntity,IRestrictedEntity> getEntityMap(); 
<<<<<<< HEAD
	/**
	 * Returns entities in entity manager
	 * @return
	 */
	public Collection<Entity> getEntities();
=======
	
	public Collection<Entity> copy();
>>>>>>> 5e6301035bc8d61251ead2fd0f04e528a985e67e
}
