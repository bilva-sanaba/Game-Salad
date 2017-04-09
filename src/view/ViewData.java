package view;

import entity.Entity;

import java.util.HashMap;
import java.util.Observable;
import components.*;

/**
 * Casting takes place to be able to edit the component because we are using enums to choose the specific component 
 * we feel comfortable to cast the component to its specific component
 * 
 * @author Jonathan
 * @author Justin
 *
 */
public class ViewData extends Observable {
	private HashMap<Integer, Entity> entityList;
	private Entity userSelectedEntity;
	
	public ViewData() {
		entityList = new HashMap<Integer, Entity>();
		userSelectedEntity = null;
	}
	
	public void setUserSelectedEntity(Entity entity) {
		userSelectedEntity = entity;
	}
	
	public Entity getUserSelectedEntity() {
		return userSelectedEntity;
	}
	
	public void setEntityLocation(int entityID, int row, int col) {
		LocationComponent locComp = (LocationComponent) entityList.get(entityID).getComponent(ComponentType.Location);
		locComp.setXY(row, col);
	}
	
	public void setEntityLocation(int row, int col) {
		setEntityLocation(userSelectedEntity.getID(), row, col);
	}
	
	public void addEntity(Entity entity) {
		entityList.put(entity.getID(), entity);
		notifyObservers();
	}
	
	public void removeEntity(Entity entity) {
		entityList.remove(entity.getID());
		notifyObservers();
	}
	
	public HashMap<Integer, Entity> getEntityMap() {
		return entityList;
	}
}
