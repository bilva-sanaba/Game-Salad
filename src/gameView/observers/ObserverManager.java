//This entire file is part of my masterpiece
//JACOB WEISS
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
/**
 * Handles the changes from the engine and reflects them in the observable data (EntityObserver and EntityManagerObserver)
 * Creates structures which the animation may then read and translate to game player so user may view
 * Only sends necessary information to the animation
 * Use of the observer design pattern highlights the good design for this portion of the project
 * By utilizing observers we are able to keep the front end and the back end of the project (game player and game engine separate)
 * without this feature, which we had implemented before animation had access to the game engine and so
 * theoretically the user could alter the back end. Such a feature may be excusable for this project
 * in which we wanted to boast as many features as possible, but in actual game design, the user should not be able
 * to change the general structure of the game he/she is playing. Also, prevents accidental errors from being done
 * and makes it so front end does not have to change when backend features are added/deleted since it is merely
 * observing said changes.
 * @author Jacob, Henry
 *
 */
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
	 * Returns all Entities
	 * @return
	 */
	public Map<Integer, ImageConfig> getEntityMap() {
		return myEntities;
	}
	/**
	 * Returns set of only changed entities
	 * @return
	 */
	public Set<Integer> getUpdatedSet(){
		return updatedEntitySet;
	}
	/**
	 * clears the set of updated entities (used after each step so the same entities do not keep getting updated)
	 */
	public void clearSet(){
		updatedEntitySet.clear();
	}
	/**
	 * 
	 * @param manager
	 */
	private void createMap(Collection<IRestrictedEntity> manager) {
        for (IRestrictedEntity e: manager){
        	e.addObserver(myEntityObserver);
            String[] test = e.getRestrictedImagePath().split("[\\\\/]");
            ImageView iView = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(test[test.length-1])));
            ImageConfig iConfig = new ImageConfig(iView, test[test.length-1]);
            myEntities.put(e.getID(), iConfig);
            updateImageView(e);
        }
    }
	/**
	 * Removes entity from animation if it has been deleted in the backend
	 * Updates entity's image's position, size and image
	 * @param e
	 */
	private void updateImageView(IRestrictedEntity e) {
		if(myEntities.get(e.getID())==null){
			myWorld.removeEntity(e.getID());
		}
		else{
			myEntities.get(e.getID()).getImageView().setFitHeight(e.getRestrictedIPComponent().getHeight());
			myEntities.get(e.getID()).getImageView().setFitWidth(e.getRestrictedIPComponent().getWidth());
			updateImage(e);
			myEntities.get(e.getID()).getImageView().setTranslateX(e.getRestrictedLocation().getWidth());
			myEntities.get(e.getID()).getImageView().setTranslateY(e.getRestrictedLocation().getHeight());
		}
 		
	}
	/**
	 * Checks to see if the entity's image path is null.
	 * If not, sets image and image path (used in proxy design later)
	 * Otherwise, adds null ImageConfig
	 * @param e
	 */
	private void updateImage(IRestrictedEntity e) {
		if (!(e.getRestrictedImagePath().equals(""))) {
			myEntities.get(e.getID()).getImageView().setImage(makeImage(e));
			myEntities.get(e.getID()).setPath(e.getRestrictedImagePath());
			updatedEntitySet.add(e.getID());
			
		} else {
			myEntities.put(e.getID(), null);
		}
		
	}
	/**
	 * Converts the image file path so it is readable on either mac or pc before accessing resource to create image
	 */
	private Image makeImage(IRestrictedEntity e) {
		String[] test = getTest(e);
        Image newImage = new Image(getClass().getClassLoader().getResourceAsStream(test[test.length-1]));
        return newImage;
	}
	
	/**
	 * helper for above method
	 * @param e
	 * @return
	 */
	private String[] getTest(IRestrictedEntity e){
		String[] test = e.getRestrictedImagePath().split("[\\\\/]");
		return test;
	}
	/**
	 * Updates entity manager observer 
	 * adds new item's desired elements (ID, ImageView and String path) to the observable entity map with ImageConfigs
	 * Called when change occurs
	 * Then makes call to update ImageView to change game player relevant information
	 * For reverse, it calls public method on animation to fill the map with previously stored entities while simultaneously 
	 * having the camera follow the rewinding user
	 * @param arg
	 */
	public void updateMap(IRestrictedEntity arg) {
		arg.addObserver(myEntityObserver);
		String[] test = getTest(arg);
		ImageConfig iConfig = new ImageConfig(new ImageView(makeImage(arg)), test[test.length-1]);
        myEntities.put(arg.getID(), iConfig);
        updateImageView(arg);

        //FOR REVERSE
		myWorld.fillMap();
		

	}
	/**
	 * updates entity observer
	 * If not null, invoke updateImageView to update location, image, etc. seen in game player. Otherwise, remove from world
	 * @param observable
	 * @param arg
	 */
	public void updateEntity(IRestrictedEntity observable, IRestrictedEntity arg) {
	
		if (arg != null) {
			updateImageView(observable);
		} 
		else {
			myEntities.put(observable.getID(), null);
			myWorld.removeEntity(observable.getID());
		}
	}
	/**
	 * Initializes the entity manager observer and the entity observer
	 * @param entity
	 */
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
