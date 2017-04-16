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
		
		 myEntities.get(e.getID()).setTranslateX(e.getRestrictedLocation().getWidth());
         myEntities.get(e.getID()).setTranslateY(e.getRestrictedLocation().getHeight());
         myEntities.get(e.getID()).setFitHeight(e.getRestrictedIPComponent().getHeight());
         myEntities.get(e.getID()).setFitWidth(e.getRestrictedIPComponent().getWidth());
         updateImage(e);
//         myEntities.get(e.getID()).setTranslateX(e.getRestrictedLocation().getWidth()*50-475);
//         myEntities.get(e.getID()).setTranslateY(e.getRestrictedLocation().getHeight()*50-175);
 		
	}
	
	private void updateImage(IRestrictedEntity e) {
		if (!(e.getRestrictedImagePath().equals(""))) {
			myEntities.get(e.getID()).setImage(makeImage(e));
		}
		
	}
	
	private Image makeImage(IRestrictedEntity e) {
		String[] test = e.getRestrictedImagePath().split("[\\\\/]");
        Image newImage = new Image(getClass().getClassLoader().getResourceAsStream(test[test.length-1]));
        return newImage;
	}
	
	public void updateMap(IRestrictedEntity arg) {
        myEntities.put(arg.getID(), new ImageView(makeImage(arg)));
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
