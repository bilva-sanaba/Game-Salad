package gameView.observers;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import controller.WorldAnimator;
import entity.restricted.IRestrictedEntity;
import entity.restricted.IRestrictedEntityManager;

public class ObserverManager {
	
	
	private Map<Integer, ImageView> myEntities;
	private WorldAnimator myWorld;
	private EntityManagerObserver myManagerObserver;
	private EntityObserver myEntityObserver;
	private Set<Integer> updatedEntitySet;
	
	
	public ObserverManager(WorldAnimator world, IRestrictedEntityManager entity) {
		myEntities = new HashMap<Integer, ImageView>();
		updatedEntitySet = new HashSet<Integer>();
		myWorld = world;
		setObservers(entity );
		createMap(entity.getRestrictedEntities());
	}
	
	public Map<Integer, ImageView> getEntityMap() {
		return myEntities;
	}
	
	public Set<Integer> getUpdatedSet(){
		return updatedEntitySet;
	}
	
	public void clearSet(){
		updatedEntitySet.clear();
	}
	
	private void createMap(Collection<IRestrictedEntity> manager) {
        for (IRestrictedEntity e: manager){
        	e.addObserver(myEntityObserver);
            String[] test = e.getRestrictedImagePath().split("[\\\\/]");
            myEntities.put(e.getID(), new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(test[test.length-1]))));
            updateImageView(e);
        }
    }
	
	private void updateImageView(IRestrictedEntity e) {
		if(myEntities.get(e.getID())==null){
			myWorld.removeEntity(e.getID());
		}
		else{
         myEntities.get(e.getID()).setFitHeight(e.getRestrictedIPComponent().getHeight());
         myEntities.get(e.getID()).setFitWidth(e.getRestrictedIPComponent().getWidth());
         updateImage(e);
         //UNCOMMENT FOR TEST RUNNER
         myEntities.get(e.getID()).setTranslateX(e.getRestrictedLocation().getWidth()-475);
         myEntities.get(e.getID()).setTranslateY(e.getRestrictedLocation().getHeight()-175);
         
         //UNCOMMENT FOR NORMAL
//         myEntities.get(e.getID()).setTranslateX(e.getRestrictedLocation().getWidth()*50-475);
//         myEntities.get(e.getID()).setTranslateY(e.getRestrictedLocation().getHeight()*50-175);
		}
 		
	}
	
	private void updateImage(IRestrictedEntity e) {
		if (!(e.getRestrictedImagePath().equals(""))) {
			myEntities.get(e.getID()).setImage(makeImage(e));
			updatedEntitySet.add(e.getID());
			
		} else {
			myEntities.put(e.getID(), null);
		}
		
	}
	
	private Image makeImage(IRestrictedEntity e) {
		String[] test = e.getRestrictedImagePath().split("[\\\\/]");
        Image newImage = new Image(getClass().getClassLoader().getResourceAsStream(test[test.length-1]));
        return newImage;
	}
	
	public void updateMap(IRestrictedEntity arg) {
		arg.addObserver(myEntityObserver);
        myEntities.put(arg.getID(), new ImageView(makeImage(arg)));
        updateImageView(arg);
	}
	
	public void updateEntity(IRestrictedEntity observable, IRestrictedEntity arg) {
	
		if (arg != null) {
			updateImageView(observable);
		} 
		else {
			System.out.println(observable.getID() + "XXXXXXXX");
			myEntities.put(observable.getID(), null);
			myWorld.removeEntity(observable.getID());
		}
	}
	
	private void setObservers(IRestrictedEntityManager entity) {
		myManagerObserver = new EntityManagerObserver(this);
		myEntityObserver = new EntityObserver(this);
		entity.addObserver(myManagerObserver);
	}
	
	public WorldAnimator getWorldAnimator(){
		return myWorld;
	}
	
	public int size(){
		return myEntities.size();
	}

}
