// This entire file is part of my masterpiece.
// Justin Yang
//
// ViewData is an observable class that is modified whenever the user
// performs an action such as placing an entity on the grid or selecting
// an entity in the entity selection window. Every method that modifies
// the data such as setCurrentLevel or defineEntity notifies ViewController,
// which is the observer. ViewData was easily extended to have multiple levels
// through the use of maps. placedEntityMaps takes the level number as a key
// and returns the map of placed entities for that level. By performing exchange
// operations on level maps and modifying a little code in each method, we were
// able to easily implement multiple levels, which shows that this data class
// is very flexible.

package view;

import entity.Entity;
import entity.LevelEntity;
import entity.SplashData;
import view.commands.RightClickEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Stack;
import components.entityComponents.ComponentType;
import components.entityComponents.LocationComponent;

/**
 * Casting takes place to be able to edit the component because we are using
 * enums to choose the specific component we feel comfortable to cast the
 * component to its specific component
 *
 * @author Jonathan
 * @author Justin
 * @author Jack
 * @author Josh
 */
public class ViewData extends Observable {

	private Stack<RightClickEvent> undoStack;
	private Stack<RightClickEvent> redoStack;
	private Map<Integer, Entity> definedEntityMap;
	private Map<Integer, Map<Integer, Entity>> placedEntityMaps;
	private Map<Integer, LevelEntity> levelEntityMap;
	private SplashData mySplashEntity;
	private Entity userSelectedEntity;
	private Entity userGridSelectedEntity;
	private Entity copiedEntity;
	private String gameName;
	private int currentLevel;
	private int maxLevel;
	private int initialRows;
	private int initialCols;

	public ViewData(int initialRowsIn, int initialColsIn) {
		currentLevel = 1;
		maxLevel = 1;
		initialRows = initialRowsIn;
		initialCols = initialColsIn;
		undoStack = new Stack<RightClickEvent>();
		redoStack = new Stack<RightClickEvent>();
		definedEntityMap = new HashMap<Integer, Entity>();
		placedEntityMaps = new HashMap<Integer, Map<Integer, Entity>>();
		placedEntityMaps.put(currentLevel, new HashMap<Integer, Entity>());
		levelEntityMap = new HashMap<Integer, LevelEntity>();
		levelEntityMap.put(currentLevel,
				new LevelEntity(-1, initialRows, initialCols, "images/background1.png", "", 3));
		mySplashEntity = new SplashData(-2, "The game", "Don't lose", "background1.png");
		userSelectedEntity = null;
		gameName = "";
	}

	public void addLevel() {
		maxLevel++;
		currentLevel = maxLevel;
		placedEntityMaps.put(currentLevel, new HashMap<Integer, Entity>());
		levelEntityMap.put(currentLevel,
				new LevelEntity(-1, initialRows, initialCols, "images/background1.png", "", 3));
	}

	public void removeLevel(int level) {
		for (int i = level; i < maxLevel; i++) {
			placedEntityMaps.put(i, placedEntityMaps.get(i + 1));
			levelEntityMap.put(i, levelEntityMap.get(i + 1));
		}
		placedEntityMaps.remove(placedEntityMaps.size());
		levelEntityMap.remove(levelEntityMap.size());
		maxLevel--;
	}

	public void moveLevel(int level, int destination) {
		Map<Integer, Entity> swapper = placedEntityMaps.get(destination);
		placedEntityMaps.put(destination, placedEntityMaps.get(level));
		placedEntityMaps.put(level, swapper);
	}

	public int getMaxLevel() {
		return maxLevel;
	}

	public int getPlacedEntityID() {
		return findMapMax(placedEntityMaps.get(currentLevel)) + 1;
	}

	public int getDefinedEntityID() {
		return findMapMax(definedEntityMap) + 1;
	}

	private int findMapMax(Map<Integer, Entity> m) {
		int max = 0;
		for (Integer i : m.keySet()) {
			if (i > max)
				max = i;
		}
		return max;
	}

	public int getCurrentLevel() {
		return currentLevel;
	}

	public void setCurrentLevel(int i) {
		currentLevel = i;
		setChanged();
		notifyObservers();
	}

	public void addEvent(RightClickEvent e) {
		undoStack.add(e);
	}

	public void undoLastEvent() {
		if (undoStack.peek() != null) {
			RightClickEvent e = undoStack.pop();
			e.undo();
			redoStack.add(e);
		}
	}

	public void redo() {
		if (redoStack.peek() != null) {
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

	public void unplaceEntity(int level, Entity entity) {
		placedEntityMaps.get(level).remove(entity.getID());
		setChanged();
		notifyObservers();
	}

	public void copyEntity(Entity entity) {
		copiedEntity = entity;
	}

	public Entity getCopiedEntity() {
		return copiedEntity;
	}

	public Entity pasteEntity(int level, double x, double y) {
		Entity tempEntity = copiedEntity.clone();
		LocationComponent tempLocation = (LocationComponent) tempEntity.getComponent(ComponentType.Location);
		tempLocation.setXY(x, y);
		placeEntity(level, tempEntity);
		return tempEntity;
	}

	public Map<Integer, Entity> getDefinedEntityMap() {
		return definedEntityMap;
	}

	public Map<Integer, Map<Integer, Entity>> getPlacedEntityMap() {
		return placedEntityMaps;
	}

	public Map<Integer, LevelEntity> getLevelEntityMap() {
		return levelEntityMap;
	}

	public LevelEntity getLevelEntity(int i) {
		return levelEntityMap.get(currentLevel);
	}

	public void setLevelEntity(int level, LevelEntity e) {
		levelEntityMap.put(level, e);
	}

	public SplashData getSplashEntity() {
		return mySplashEntity;
	}

	public void setSplashEntity(SplashData s) {
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

	public void removePlacedEntities(int level) {
		placedEntityMaps.get(level).clear();
		setChanged();
		notifyObservers();
	}

	public void resetLevelTabs() {
		maxLevel = placedEntityMaps.size();
		currentLevel = 1;
		setChanged();
		notifyObservers("reset");
	}

}
