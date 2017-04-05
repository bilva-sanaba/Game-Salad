package engines;

import java.util.ArrayList;
import java.util.List;

import components.ComponentType;
import components.IComponent;
import entity.IEntityManager;
/**
 * An abstract engine represents all engines
 * It is currently passed in an entityManager which contains all the entities 
 * It sets myComponents equal to the Entity components needed by the engine which are defined in neededComponents
 * (Is this a good way to pass only the needed data???)
 * When update is called, the engine updates all the components it has access to appropriately
 * @author Bilva
 *
 */
public abstract class AbstractEngine {
	private List<ArrayList<IComponent>> myComponents = new ArrayList<ArrayList<IComponent>>();
	public AbstractEngine(IEntityManager myEntityManager){
		myComponents = myEntityManager.getAllComponents(neededComponents());
	}
	/**
	 * Returns the componentTypes it needs to operate
	 * @return
	 */
	protected abstract List<ComponentType> neededComponents();
	/**
	 * Updates its lists of components
	 */
	public abstract void update();
	
}
