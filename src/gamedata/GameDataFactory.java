package gamedata;

import java.util.ArrayList;
import java.util.List;

import components.entityComponents.LocationComponent;
import entity.Entity;
import entity.EntityManager;
import entity.IEntity;
import entity.restricted.IRestrictedEntity;
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
	public void updateGameData(IGameData gameData, IRestrictedGameData updatedData){
		gameData.setPoints(updatedData.getPoints().doubleValue());
		gameData.setLives(updatedData.getPoints().doubleValue());
		gameData.setMainLocation(updatedData.getMainLocation());
		gameData.setLevel(updatedData.getLevel().doubleValue());
		gameData.setMusic(updatedData.getMusic().toString());
		List<Entity> newEntities = new ArrayList<Entity>();
		for (IRestrictedEntity re : updatedData.getRestrictedEntityManager().getRestrictedEntities()){
			newEntities.add(re.clone());
		}
		gameData.setRestrictedEntityManager((IRestrictedEntityManager) new EntityManager(newEntities));
	}
}