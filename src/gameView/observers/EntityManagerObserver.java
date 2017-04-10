package gameView.observers;

import entity.restricted.IRestrictedEntity;
import gameView.ImageManager;
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