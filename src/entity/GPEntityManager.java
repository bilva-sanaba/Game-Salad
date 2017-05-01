package entity;

import java.util.Collection;
import java.util.*;

public class GPEntityManager {
	private Collection<LevelEntity> levelEntities;
	private SplashData se;
	public GPEntityManager(Collection<Entity> entities){
		levelEntities = new ArrayList<LevelEntity>();
		for (Entity e: entities) {
			if (e.getClass().toString().equals("class entity.LevelEntity")) {
				levelEntities.add((LevelEntity) e);
			}
			else if (e.getClass().toString().equals("class entity.SplashData")){
				se = (SplashData) e;
			}		
		}
	}
	public SplashData getSplash(){
		return se;
	}
	public Collection<LevelEntity> getLevels(){
		return levelEntities;
	}
}
