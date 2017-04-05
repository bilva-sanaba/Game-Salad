package entity;

import java.util.ArrayList;
import java.util.List;

import components.ComponentType;
import components.IComponent;
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

	public List<ArrayList<IComponent>> getAllComponents(List<ComponentType> neededComponents);

}
