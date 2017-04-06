package gameView;

import java.util.HashMap;
import java.util.Iterator;

import entitiy.restricted.IRestrictedEntity;
import entitiy.restricted.IRestrictedEntityManager;
import gameView.observers.EntityManagerObserver;
import gameView.observers.EntityObserver;

public class ImageManager {

	private IRestrictedEntityManager myEntities;
	private EntityObserver myEntityObserver;
	private EntityManagerObserver myEntityManagerObserver;
	private HashMap<IRestrictedEntity, UIImageModel> myMap;
	
	public ImageManager(IRestrictedEntityManager manager) {
		myEntities = manager;
		myEntityObserver = new EntityObserver(this);
		addEntityObserver();
		addAllEntityObservers();
		createMap();
	}
	
	public void updateEntity(IRestrictedEntity entity) {
		UIImageModel toUpdate = myMap.get(entity);
		toUpdate.updateImage(entity.getImagePath());
		toUpdate.updateLocation(entity.getLocation());
	}
	
	public void updateMap(IRestrictedEntity entity) {
		createImageToMap(entity);
	}
	
	public Iterator<UIImageModel> getAllImages() {
		return myMap.values().iterator();
	}
	
	private void addEntityObserver() {
		myEntities.addObserver(myEntityManagerObserver);
	}
	
	private void addAllEntityObservers() {
		for (IRestrictedEntity each: myEntities.getEntities()) {
			each.addObserver(myEntityObserver);
		}
	}
	
	private void createMap() {
		myMap = new HashMap<IRestrictedEntity, UIImageModel>();
		for (IRestrictedEntity each: myEntities.getEntities()) {
			createImageToMap(each);
		}
	}
	
	private void createImageToMap(IRestrictedEntity toAdd) {
		UIImageModel toBeAdded = new UIImageModel(toAdd.getLocation(), toAdd.getImagePath());
		myMap.put(toAdd,  toBeAdded);
	}
	
	
	
	
	
}
