package view;

import entity.Entity;
import entity.LevelEntity;
import entity.SplashEntity;
import view.commands.RightClickEvent;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Queue;
import java.util.Stack;

import components.*;
import components.entityComponents.ComponentType;
import components.entityComponents.LocationComponent;
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

	private Stack<RightClickEvent> undoStack;
	private Stack<RightClickEvent> redoStack;
	private HashMap<Integer, Entity> definedEntityMap;
	private HashMap<Integer, HashMap<Integer, Entity>> placedEntityMaps;
	private HashMap<Integer, Entity> levelEntityMap;
	private SplashEntity mySplashEntity;
	private Entity userSelectedEntity;
	private Entity userGridSelectedEntity;
	private Entity copiedEntity;
	private String gameName;
	private int currentLevel;
	private Boolean saved = true;
//TODO: implement the saved boolean to track whether the current state is saved
	private int initialRows;
	private int initialCols;

	public ViewData(int initialRowsIn, int initialColsIn) {
		currentLevel = 1;
		initialRows = initialRowsIn;
		initialCols = initialColsIn;
		undoStack = new Stack<RightClickEvent>();
		redoStack = new Stack<RightClickEvent>();
		definedEntityMap = new HashMap<Integer, Entity>();
		placedEntityMaps = new HashMap<Integer, HashMap<Integer, Entity>>();
		placedEntityMaps.put(currentLevel, new HashMap<Integer, Entity>());
		levelEntityMap = new HashMap<Integer, Entity>();
		levelEntityMap.put(currentLevel, new LevelEntity(-1, initialRows, initialCols, "images/background1.png"));
		mySplashEntity = new SplashEntity(-2, "The game", "Don't lose", "images/background1.png");
		userSelectedEntity = null;
		gameName = "";
	}
	
	public int getCurrentLevel(){
		return currentLevel;
	}
	
	public void setCurrentLevel(int i){
		currentLevel = i;
		setChanged();
		notifyObservers();
	}
	
	public void addLevel(int level){
		currentLevel = level;
		placedEntityMaps.put(currentLevel, new HashMap<Integer, Entity>());
		levelEntityMap.put(currentLevel, new LevelEntity(-1, initialRows, initialCols, "images/background1.png"));
		setChanged();
		notifyObservers();
	}
	
	public void addEvent(RightClickEvent e) {
		undoStack.add(e);
	}

	public void undoLastEvent(){
		if(undoStack.peek() != null) {
			RightClickEvent e = undoStack.pop();
			e.undo();
			redoStack.add(e);
		}
	}

	public void redo(){
		if(redoStack.peek() != null) {
			RightClickEvent e = redoStack.pop();
			e.execute();
			undoStack.add(e);
		}
	}

	public void setUserSelectedEntity(Entity entity) {
		userSelectedEntity = entity;
	}

	public Entity getUserSelectedEntity() {
		return userSelectedEntity;
	}

	public void setUserGridSelectedEntity(Entity entity) {
		userGridSelectedEntity = entity;
	}

	public Entity getUserGridSelectedEntity() {
		return userGridSelectedEntity;
	}

	public void defineEntity(Entity entity) {
		definedEntityMap.put(entity.getID(), entity);
		setChanged();
		notifyObservers();
	}
	
	public void defineEntityNoUpdate(Entity entity) {
		definedEntityMap.put(entity.getID(), entity);
	}

	// fix dependencies
	public void placeEntity(int levelNumber, Entity entity) {
		if (!placedEntityMaps.containsKey(levelNumber)) {
			placedEntityMaps.put(levelNumber, new HashMap<Integer, Entity>());
		}
		placedEntityMaps.get(levelNumber).put(entity.getID(), entity);
		setChanged();
		notifyObservers();
	}

	public void undefineEntity(Entity entity) {
		definedEntityMap.remove(entity.getID());
	}

	// fix dependencies
	public void unplaceEntity(int levelNumber, Entity entity) {
		placedEntityMaps.get(levelNumber).remove(entity);
		userGridSelectedEntity = entity;
		setChanged();
		notifyObservers();
	}

	public void copyEntity(){
		copiedEntity = userGridSelectedEntity;
	}
	
	// fix dependencies
	public Entity pasteEntity(int levelNumber, double x, double y) {
		Entity tempEntity = copiedEntity.clone();
		LocationComponent tempLocation = (LocationComponent) tempEntity.getComponent(ComponentType.Location);
		tempLocation.setXY(x, y);
		placeEntity(levelNumber, tempEntity);
		userGridSelectedEntity = tempEntity;
		return tempEntity;
	}
	
	public Map<Integer, Entity> getDefinedEntityMap() {
		return definedEntityMap;
	}

	// fix dependencies
	public Map<Integer, HashMap<Integer, Entity>> getPlacedEntityMap() {
		return placedEntityMaps;
	}

	public LevelEntity getLevelEntity() {
		return (LevelEntity) levelEntityMap.get(currentLevel);
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

	public void refresh() {
		setChanged();
		notifyObservers();
	}
	
	// fix dependencies
	public void removePlacedEntities(int levelNumber) {
		placedEntityMaps.get(levelNumber).clear();
		setChanged();
		notifyObservers();
	}
	
	//TODO: Reset level tabs method
	public void resetLevelTabs(){
		
	}

	
}
