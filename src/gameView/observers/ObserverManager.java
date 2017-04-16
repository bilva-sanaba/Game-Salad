package gameView.observers;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import controller.WorldAnimator;
import entity.restricted.IRestrictedEntity;
import entity.restricted.IRestrictedEntityManager;

public class ObserverManager {
	
	
	private Map<Integer, ImageView> myEntities;
	private EntityManagerObserver myManagerObserver;
	private EntityObserver myEntityObserver;
	
	
	public ObserverManager(WorldAnimator world, IRestrictedEntityManager entity) {
		myEntities = new HashMap<Integer, ImageView>();
		setObservers(entity );
		createMap(entity.getRestrictedEntities());
	}
	
	public Map<Integer, ImageView> getEntityMap() {
		return myEntities;
	}
	
	private void createMap(Collection<IRestrictedEntity> manager) {
        for (IRestrictedEntity e: manager){
        	e.addObserver(myEntityObserver);
            String[] test = e.getRestrictedImagePath().split("[\\\\/]");
            myEntities.put(e.getID(), new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(test[test.length-1]))));
            updateImageView(e);
            //imageMap.get(e.getID()).setTranslateX(e.getLocation().getX()*50-475);
			//imageMap.get(e.getID()).setTranslateY(e.getLocation().getY()*50-175);
        }
    }
	
	private void updateImageView(IRestrictedEntity e) {
		 myEntities.get(e.getID()).setX(e.getRestrictedLocation().getWidth());
         myEntities.get(e.getID()).setY(e.getRestrictedLocation().getHeight());
         myEntities.get(e.getID()).setFitHeight(e.getRestrictedIPComponent().getHeight());
         myEntities.get(e.getID()).setFitWidth(e.getRestrictedIPComponent().getWidth());
		//imageMap.get(e.getID()).setTranslateX(e.getLocation().getX()*50-475);
		//imageMap.get(e.getID()).setTranslateY(e.getLocation().getY()*50-175);
	}
	
	public void updateMap(IRestrictedEntity arg) {
		String[] test = arg.getRestrictedImagePath().split("[\\\\/]");
        myEntities.put(arg.getID(), new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(test[test.length-1]))));
        updateImageView(arg);
	}
	
	public void updateEntity(IRestrictedEntity arg) {
		updateImageView(arg);
	}
	
	private void setObservers(IRestrictedEntityManager entity) {
		myManagerObserver = new EntityManagerObserver(this);
		myEntityObserver = new EntityObserver(this);
		entity.addObserver(myManagerObserver);
	}

}
