package entity;

import java.util.*;

public class GamePlayerEntityManager {
	private Collection<Entity> myLevelEntities;
	private SplashEntity se;

	public GamePlayerEntityManager(Collection<Entity> entities){
		myLevelEntities = new ArrayList<Entity>();
		for (Entity e: entities) {
			if (e.getClass().toString().equals("class entity.SplashEntity")){
				se = (SplashEntity)e;
			}
			else if (e.getClass().toString().equals("class entity.LevelEntity")) {
				myLevelEntities.add((LevelEntity) e);
			}
		}
	}
}


