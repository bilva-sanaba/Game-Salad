package gameView.observers;

import entitiy.restricted.IRestrictedEntity;
import entity.IEntityManager;
import gameView.ImageManager;
import gameView.UIView;
import gameView.gameScreen.GameScreen;

import java.util.Observable;
import java.util.Observer;

public class EntityManagerObserver implements Observer {

	private ImageManager myImageManager;
	
	public EntityManagerObserver(ImageManager images) {
		myImageManager = images;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		myImageManager.updateMap((IRestrictedEntity) arg);
	}

}
