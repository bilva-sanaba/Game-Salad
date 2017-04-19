package view;

import entity.Entity;
import entity.LevelEntity;
import entity.SplashEntity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Observable;
import components.*;
import components.movementcomponents.LocationComponent;
import data_interfaces.Communicator;

/**
 * Casting takes place to be able to edit the component because we are using enums to choose the specific component
 * we feel comfortable to cast the component to its specific component
 *
 * @author Jonathan
 * @author Justin
 * @author Jack
 */
public class ViewData extends Observable {
	
	private static final int STARTINGROWS = 50;
	private static final int STARTINGCOLS = 50;
	private static final String PRESETFILE = "PresetEntities";
	
	private HashMap<Integer, Entity> definedEntityMap;
	private HashMap<Integer, Entity> placedEntityMap;
	private LevelEntity myLevelEntity;
	private SplashEntity mySplashEntity;
	private Entity userSelectedEntity;
	private String gameName;
	private Boolean saved = true;
	//TODO: implement the saved boolean to track whether the current state is saved

	public ViewData() {
		definedEntityMap = new HashMap<Integer, Entity>();
		placedEntityMap = new HashMap<Integer, Entity>();
		myLevelEntity = new LevelEntity(-1, STARTINGROWS, STARTINGCOLS, "images/background1.png");
		mySplashEntity = new SplashEntity(-2, "The game", "Don't lose", "images/background1.png");
		userSelectedEntity = null;
		gameName = "";
	}

	public void setUserSelectedEntity(Entity entity) {
		userSelectedEntity = entity;
	}

	public Entity getUserSelectedEntity() {
		return userSelectedEntity;
	}

	public void defineEntity(Entity entity) {
		definedEntityMap.put(entity.getID(), entity);
		setChanged();
		notifyObservers(entity);
	}

	public void placeEntity(Entity entity) {
		placedEntityMap.put(entity.getID(), entity);
		setChanged();
		notifyObservers(entity);
	}

	//TODO: implement CTRL + Z and stuff
	/*	public void undefineEntity(Entity entity) {
		definedEntityMap.remove(entity.getID());
	}

	public void unplaceEntity(Entity entity) {
		definedEntityMap.remove(entity.getID());
	} */

	public HashMap<Integer, Entity> getDefinedEntityMap() {
		return definedEntityMap;
	}

	public HashMap<Integer, Entity> getPlacedEntityMap() {
		return placedEntityMap;
	}

	public LevelEntity getLevelEntity () {
		return myLevelEntity;
	}

	public void setLevelEntity(LevelEntity l) {
		myLevelEntity = l;
	}

	public SplashEntity getSplashEntity() {
		return mySplashEntity;
	}

	public void setSplashEntity(SplashEntity s) {
		mySplashEntity = s;
	}

	public void setGameName(String s) {
		gameName = s;
	}

	public String getGameName() {
		return gameName;
	}

	public void refresh(){
		definedEntityMap.clear();
		placedEntityMap.clear();
		setChanged();
		notifyObservers("refresh");
	}

	public void addPresetEntities(){
		Communicator c = new Communicator(PRESETFILE);
		Collection <Entity> col = c.getData();
		for (Entity e: col) {
			if (!e.getClass().toString().equals("class entity.LevelEntity") && !e.getClass().toString().equals("class entity.SplashEntity")) {
				defineEntity(e);
			}
		}
	}
}
