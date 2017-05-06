//This entire file is part of my masterpiece
//JACOB WEISS
package gameView.observers;

import entity.restricted.IRestrictedEntity;

import java.util.Observable;
import java.util.Observer;
/**
 * object for the observer of individual entities
 * @author Jacob, Henry
 *
 */
public class EntityObserver implements Observer {

	private ObserverManager myObserverManager;

	public EntityObserver(ObserverManager images) {
		myObserverManager = images;
	}
	/**
	 * Invokes observer manager to update individual entities
	 * Checks arg to see changes and then updates observable entity
	 */
	@Override
	public void update(Observable o, Object arg) {
		myObserverManager.updateEntity((IRestrictedEntity) o, (IRestrictedEntity) arg);

	}

}
