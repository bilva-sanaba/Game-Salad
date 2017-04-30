package gamedata;

import java.util.ArrayList;
import java.util.List;

import components.entityComponents.LocationComponent;
import entity.Entity;
import entity.EntityManager;
import entity.IEntity;
import entity.restricted.IRestrictedEntity;
import entity.restricted.IRestrictedEntityManager;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class GameDataFactory {
	private MediaPlayer player;
	public GameDataFactory(){
	}
	public GameData blankEntityData(IRestrictedGameData currentData){
		double points = currentData.getPoints().doubleValue();
		double lives = currentData.getLives().doubleValue();
		double lvl = currentData.getLevel().doubleValue();
		LocationComponent lc = currentData.getMainLocation();
		List<String> ac = currentData.getAchievement();
		String music = currentData.getMusic().toString();
		
		return new GameData(points,lives,(IRestrictedEntityManager) new EntityManager(), lvl, lc, ac, music);
	}
	public void updateGameData(IGameData gameData, IRestrictedGameData updatedData){
		gameData.setPoints(updatedData.getPoints().doubleValue());
		gameData.setLives(updatedData.getLives().doubleValue());
		gameData.setMainLocation(updatedData.getMainLocation());
		gameData.setLevel(updatedData.getLevel().doubleValue());
		if (gameData.getMusic().toString()!= updatedData.getMusic().toString()){
			gameData.setMusic(updatedData.getMusic().toString());
		}
		if (gameData.getAchievement().size()!=updatedData.getAchievement().size()){
			if (!gameData.getAchievement().get(gameData.getAchievement().size()-1).equals((updatedData.getAchievement().get(updatedData.getAchievement().size()-1)))){
				gameData.setAchievement((String) updatedData.getAchievement().get(updatedData.getAchievement().size()-1));
			}
		}
		//		List<Entity> newEntities = new ArrayList<Entity>();
		//		for (IRestrictedEntity re : updatedData.getRestrictedEntityManager().getRestrictedEntities()){
		//			newEntities.add(re.clone());
		//		}
		//		gameData.setRestrictedEntityManager((IRestrictedEntityManager) new EntityManager(newEntities));
	}
}