package gameView.observers;

import entity.restricted.IRestrictedEntity;
import java.util.Observable;
import java.util.Observer;  
 
public class EntityManagerObserver implements Observer { 

	private ObserverManager myObserverManager;

	public EntityManagerObserver(ObserverManager images) { 
		myObserverManager = images;
	} 
 
	@Override  
	public void update(Observable o, Object arg) {
		myObserverManager.updateMap((IRestrictedEntity) arg);
	}

}