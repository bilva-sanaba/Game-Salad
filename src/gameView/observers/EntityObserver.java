package gameView.observers;

import entity.restricted.IRestrictedEntity;
import gameView.ImageManager;

import java.util.Observable;
import java.util.Observer;

public class EntityObserver implements Observer {

	private ObserverManager myObserverManager;

	public EntityObserver(ObserverManager images) {
		myObserverManager = images;
	}

	@Override
	public void update(Observable o, Object arg) {
		myObserverManager.updateEntity((IRestrictedEntity) o, (IRestrictedEntity) arg);

	}

}
