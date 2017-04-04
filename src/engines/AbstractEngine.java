package engines;

import java.util.ArrayList;
import java.util.List;

import components.ComponentType;
import components.IComponent;
import entity.IEntityManager;

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
