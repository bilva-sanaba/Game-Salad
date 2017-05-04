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
import gamedata.IRestrictedGameData;
import javafx.scene.input.KeyCode;

public abstract class AbstractEngine implements IEngine {
	private IEntityManager myEManager;
	
	public AbstractEngine(IEntityManager myEntityManager){
		myEManager = myEntityManager;
	}

	/**
	 * Updates its lists of components
	 * @param keysPressed 
	 * @return TODO
	 * 
	 * @return
	 */
	public abstract IRestrictedGameData update(Collection<KeyCode> keysPressed, IRestrictedGameData gameData);
	
	/**
	 * Returns the entity manager
	 */
	protected IEntityManager getEManager () {
		return myEManager;
	}
	
	protected boolean hasComponent(IEntity e, ComponentType c) {
		return (e.getComponent(c)!=null);
	}

}