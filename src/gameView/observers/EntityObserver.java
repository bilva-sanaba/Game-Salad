package gameView.observers;

import entitiy.restricted.IRestrictedEntity;
import gameView.ImageManager;

import java.util.Observable;
import java.util.Observer;

public class EntityObserver implements Observer {
	
	private ImageManager myImageManager;

	public EntityObserver(ImageManager images) {
		myImageManager = images;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		myImageManager.updateEntity((IRestrictedEntity) arg);
		
	}

}
