package engines;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import components.IComponent;
import components.entityComponents.ComponentType;
import entity.Entity;
import entity.IEntity;
import entity.IEntityManager;
import entity.restricted.IRestrictedEntity;
import entity.restricted.RestrictedEntity;
import javafx.scene.input.KeyCode;

public abstract class AbstractEngine {

	protected List<ArrayList<IComponent>> myComponents = new ArrayList<ArrayList<IComponent>>();
	private IEntityManager myEManager;
	
	public AbstractEngine(IEntityManager myEntityManager){
		myEManager = myEntityManager;
//		int i = 0;
//		for (ComponentType ct : neededComponents())
//			myComponents.add((ArrayList<IComponent>) myEntityManager
//					.getCertainComponents(neededComponents().get(i)));
	}

	/**
	 * Returns the componentTypes it needs to operate
	 * 
	 * @return
	 */
	protected abstract List<ComponentType> neededComponents();

	/**
	 * Updates its lists of components
	 * @param keysPressed 
	 * 
	 * @return
	 */
	public abstract Collection<IEntity> update(Collection<KeyCode> keysPressed);
	
	/**
	 * Returns the entity manager
	 */
	protected IEntityManager getEManager () {
		return myEManager;
	}

}