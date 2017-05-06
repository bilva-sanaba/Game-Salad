//This entire file is part of my masterpiece
//JACOB WEISS
package gameView.observers;

import entity.restricted.IRestrictedEntity;
import java.util.Observable;
import java.util.Observer;  
 /**
  * Object for the obsever of the collection of entities (the entity manager)
  * @author Jacob, Henry
  *
  */
public class EntityManagerObserver implements Observer { 

	private ObserverManager myObserverManager;

	public EntityManagerObserver(ObserverManager images) { 
		myObserverManager = images;
	} 
	/**
	 * Invokes observer manager to update entities held within the entity manager
	 */
	@Override  
	public void update(Observable o, Object arg) {
		myObserverManager.updateMap((IRestrictedEntity) arg);
	}

}