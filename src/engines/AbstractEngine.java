package engines;

import java.util.Collection;

import components.entityComponents.ComponentType;
import entity.IEntity;
import entity.IEntityManager;
import gamedata.IRestrictedGameData;
import javafx.scene.input.KeyCode;
/**
 * Abstract class which all engines can extend in order to get the protected EntityManager
 * 
 * @author Bilva
 *
 */
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
	 * Returns the entity manager passed in the constructor
	 */
	protected IEntityManager getEManager () {
		return myEManager;
	}
	//Safer method exists which e.hasComponent(c)
	protected boolean hasComponent(IEntity e, ComponentType c) {
		return (e.getComponent(c)!=null);
	}

}