package entity;

import java.util.ArrayList;

public class GameObject {
	private ArrayList<Level> levelList = new ArrayList<Level>();

	public ArrayList<Level> getLevels(){
		return levelList;
	}
}
