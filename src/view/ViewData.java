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
 * @author Jack
 *
 */
public class ViewData extends Observable {
	private HashMap<Integer, Entity> definedEntityMap;
	private HashMap<Integer, Entity> placedEntityMap;
	private Entity userSelectedEntity;
	private String gameName;

	public ViewData() {
		definedEntityMap = new HashMap<Integer, Entity>();
		placedEntityMap = new HashMap<Integer, Entity>();
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

		LocationComponent locComp = new LocationComponent(row, col);

		placedEntityMap.get(entityID).addComponent(locComp);
		/*	LocationComponent locComp = (LocationComponent) definedEntityList.get(entityID).getComponent(ComponentType.Location);
		locComp.setX(row);
		locComp.setY(col); */
	}

	public void defineEntity(Entity entity) {
		definedEntityMap.put(entity.getID(), entity);
	}

	public void placeEntity(Entity entity) {
		placedEntityMap.put(entity.getID(), entity);
	}

	public void undefineEntity(Entity entity) {
		definedEntityMap.remove(entity.getID());
	}

	public void unplaceEntity(Entity entity) {
		definedEntityMap.remove(entity.getID());
	}

	public HashMap<Integer, Entity> getDefinedEntityMap() {
		return definedEntityMap;
	}
	
	public HashMap<Integer, Entity> getPlacedEntityMap() {
		return placedEntityMap;
	}

	public void setGameName(String s) {
		gameName = s;
	}

	public String getGameName() {
		return gameName;
	}
	
	public void clearData(){
		definedEntityMap.clear();
		placedEntityMap.clear();
		
	}
	public void refresh(){
		setChanged();
		notifyObservers("refresh");
	}
}
