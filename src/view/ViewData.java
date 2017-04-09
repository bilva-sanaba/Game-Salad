package view;

import entity.Entity;

import java.util.HashMap;
import java.util.Observable;
import components.*;

/**
 * Casting takes place to be able to edit the component because we are using enums to choose the specific component
 * we feel comfortable to cast the component to its specific component
 *
=======
import components.*;
/**
 * Casting takes place to beable to edit the component becuase we are using Enums to choose the specific component 
 * we feel compfortable to cast the component to its specific component
 * 
 * @author Jonathan
 * @author Justin
 *
 */
public class ViewData extends Observable {
	private HashMap<Integer, Entity> definedEntityList;
	private HashMap<Integer, Entity> placedEntityList;
	private Entity userSelectedEntity;
	private String gameName;
	
	public ViewData() {
		definedEntityList = new HashMap<Integer, Entity>();
		placedEntityList = new HashMap<Integer, Entity>();
		userSelectedEntity = null;
		gameName = "";
	}

	public void setUserSelectedEntity(Entity entity) {
		userSelectedEntity = entity;
	}

	public Entity getUserSelectedEntity() {
		return userSelectedEntity;
	}

	public void setEntityLocation(int entityID, int row, int col) {
		LocationComponent locComp = (LocationComponent) definedEntityList.get(entityID).getComponent(ComponentType.Location);
		locComp.setX(row);
		locComp.setY(col);
	}

	public void defineEntity(Entity entity) {
		definedEntityList.put(entity.getID(), entity);
		notifyObservers();
	}

	public void placeEntity(Entity entity) {
		placedEntityList.put(entity.getID(), entity);
		notifyObservers();
	}

	public void undefineEntity(Entity entity) {
		definedEntityList.remove(entity.getID());
		notifyObservers();
	}

	public void unplaceEntity(Entity entity) {
		definedEntityList.remove(entity.getID());
		notifyObservers();
	}

	public HashMap<Integer, Entity> getEntityMap() {
		return definedEntityList;

	}
	
	public void setGameName(String s) {
		gameName = s;
	}
	
	public String getGameName() {
		return gameName;
	}
}
}

