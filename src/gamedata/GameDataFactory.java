package gamedata;

import components.entityComponents.LocationComponent;
import entity.EntityManager;
import entity.restricted.IRestrictedEntityManager;

public class GameDataFactory {
	public GameData blankEntityData(IRestrictedGameData currentData){
		double points = currentData.getPoints().doubleValue();
		double lives = currentData.getLives().doubleValue();
		double lvl = currentData.getLevel().doubleValue();
		LocationComponent lc = currentData.getMainLocation();
		String music = currentData.getMusic().toString();
		
		return new GameData(points,lives,(IRestrictedEntityManager) new EntityManager(), lvl, lc,music);
	}
}
