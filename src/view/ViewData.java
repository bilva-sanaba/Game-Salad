package view;

import entity.Entity;

import java.util.HashMap;
import java.util.Observable;
<<<<<<< HEAD
=======
import components.*;

<<<<<<< HEAD
/**
 * Casting takes place to be able to edit the component because we are using enums to choose the specific component
 * we feel comfortable to cast the component to its specific component
 *
=======
>>>>>>> 5c72dfd9ffdd7bbe7f803c0005dfd099a582a7f9
import components.*;

/**
 * Casting takes place to be able to edit the component because we are using enums to choose the specific component 
 * we feel comfortable to cast the component to its specific component
 * 
>>>>>>> master
 * @author Jonathan
 * @author Justin
 *
 */
public class ViewData extends Observable {
	private HashMap<Integer, Entity> definedEntityList;
	private HashMap<Integer, Entity> placedEntityList;
	private Entity userSelectedEntity;

	public ViewData() {
		definedEntityList = new HashMap<Integer, Entity>();
		placedEntityList = new HashMap<Integer, Entity>();
		userSelectedEntity = null;
	}

	public void setUserSelectedEntity(Entity entity) {
		userSelectedEntity = entity;
	}

	public Entity getUserSelectedEntity() {
		return userSelectedEntity;
	}

	public void setEntityLocation(int entityID, int row, int col) {
		LocationComponent locComp = (LocationComponent) definedEntityList.get(entityID).getComponent(ComponentType.Location);
<<<<<<< HEAD
		locComp.setXY(row, col);
	}
	
	public void defineEntity(Entity entity) {
		definedEntityList.put(entity.getID(), entity);
		notifyObservers();
	}
	
=======
		locComp.setX(row);
		locComp.setY(col);
	}

	public void defineEntity(Entity entity) {
		definedEntityList.put(entity.getID(), entity);
		notifyObservers();
	}

>>>>>>> 5c72dfd9ffdd7bbe7f803c0005dfd099a582a7f9
	public void placeEntity(Entity entity) {
		placedEntityList.put(entity.getID(), entity);
		notifyObservers();
	}
<<<<<<< HEAD
	
=======

>>>>>>> 5c72dfd9ffdd7bbe7f803c0005dfd099a582a7f9
	public void undefineEntity(Entity entity) {
		definedEntityList.remove(entity.getID());
		notifyObservers();
	}
<<<<<<< HEAD
	
=======

>>>>>>> 5c72dfd9ffdd7bbe7f803c0005dfd099a582a7f9
	public void unplaceEntity(Entity entity) {
		definedEntityList.remove(entity.getID());
		notifyObservers();
	}

	public HashMap<Integer, Entity> getEntityMap() {
		return definedEntityList;
<<<<<<< HEAD
=======

>>>>>>> 5c72dfd9ffdd7bbe7f803c0005dfd099a582a7f9
	}
}