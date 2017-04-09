package view;

import entity.Entity;

import java.util.HashMap;
import java.util.Observable;

import components.ComponentType;

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
//		entityList.get(entityID).getComponent(ComponentType.Location).setX(row);
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
