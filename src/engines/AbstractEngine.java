package engines;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import components.ComponentType;
import components.IComponent;
import entitiy.restricted.IRestrictedEntity;
import entitiy.restricted.RestrictedEntity;
import entity.IEntityManager;

public abstract class AbstractEngine {
	
	protected List<ArrayList<IComponent>> myComponents = new ArrayList<ArrayList<IComponent>>();
	
	public AbstractEngine(IEntityManager myEntityManager) {
		
		/*for (ComponentType ct: neededComponents())
			myComponents.add((ArrayList<IComponent>) myEntityManager.getCertainComponents(neededComponents().get(i)));
	*/
	}
	/**
	 * Returns the componentTypes it needs to operate
	 * @return
	 */
	protected abstract List<ComponentType> neededComponents();
	/**
	 * Updates its lists of components
	 * @return 
	 */
	public abstract Collection<? extends RestrictedEntity> update();
	
}
