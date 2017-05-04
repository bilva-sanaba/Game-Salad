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
	
	
	private Map<Integer, ImageConfig> myEntities;
	private WorldAnimator myWorld;
	private EntityManagerObserver myManagerObserver;
	private EntityObserver myEntityObserver;
	private Set<Integer> updatedEntitySet;
	
	
	public ObserverManager(WorldAnimator world, IRestrictedEntityManager entity) {
		myEntities = new HashMap<Integer, ImageConfig>();
		updatedEntitySet = new HashSet<Integer>();
		myWorld = world;
		setObservers(entity );
		createMap(entity.getRestrictedEntities());
	}
	
	/**
	 * Retruns all Entities
	 * @return
	 */
	public Map<Integer, ImageConfig> getEntityMap() {
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
            System.out.println(test[test.length-1]);
            ImageView iView = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(test[test.length-1])));
            ImageConfig iConfig = new ImageConfig(iView, test[test.length-1]);
            myEntities.put(e.getID(), iConfig);
            updateImageView(e);
        }
    }
	
	private void updateImageView(IRestrictedEntity e) {
		if(myEntities.get(e.getID())==null){
			myWorld.removeEntity(e.getID());
		}
		else{
			
			myEntities.get(e.getID()).getImageView().setFitHeight(e.getRestrictedIPComponent().getHeight());
			myEntities.get(e.getID()).getImageView().setFitWidth(e.getRestrictedIPComponent().getWidth());
			updateImage(e);
			//UNCOMMENT FOR TEST RUNNER
			myEntities.get(e.getID()).getImageView().setTranslateX(e.getRestrictedLocation().getWidth());//-475);
			myEntities.get(e.getID()).getImageView().setTranslateY(e.getRestrictedLocation().getHeight());//-175);

		}
 		
	}
	
	private void updateImage(IRestrictedEntity e) {
		if (!(e.getRestrictedImagePath().equals(""))) {
			myEntities.get(e.getID()).getImageView().setImage(makeImage(e));
			myEntities.get(e.getID()).setPath(e.getRestrictedImagePath());
			updatedEntitySet.add(e.getID());
			
		} else {
			myEntities.put(e.getID(), null);
		}
		
	}
	
	private Image makeImage(IRestrictedEntity e) {
		String[] test = getTest(e);
        Image newImage = new Image(getClass().getClassLoader().getResourceAsStream(test[test.length-1]));
        return newImage;
	}
	
	private String[] getTest(IRestrictedEntity e){
		String[] test = e.getRestrictedImagePath().split("[\\\\/]");
		return test;
	}
	
	public void updateMap(IRestrictedEntity arg) {
		arg.addObserver(myEntityObserver);
		String[] test = getTest(arg);
		ImageConfig iConfig = new ImageConfig(new ImageView(makeImage(arg)), test[test.length-1]);
        myEntities.put(arg.getID(), iConfig);
        updateImageView(arg);

        
        //FOR REVERSE
		myWorld.fillMap();

	}
	
	public void updateEntity(IRestrictedEntity observable, IRestrictedEntity arg) {
	
		if (arg != null) {
			updateImageView(observable);
		} 
		else {
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
