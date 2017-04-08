package entity;

import java.util.ArrayList;
import java.util.Observable;

public class Level extends Observable{
	private ArrayList<Entity> entityList = new ArrayList<Entity>();
	
	public void add(Entity e){
		entityList.add(e);
		setChanged();
		notifyObservers();
	}

	public ArrayList<Entity> getList(){
		return entityList;
	}
}
