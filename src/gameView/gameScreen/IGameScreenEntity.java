package gameView.gameScreen;

import javafx.scene.image.ImageView;
import entity.restricted.IRestrictedEntityManager;
import gameView.gameDataManagement.GameDataManager;
import gamedata.GameData;
import gamedata.IRestrictedGameData;

public interface IGameScreenEntity {

	/**
	 * Add an entity 
	 * @param entity - entity to add
	 */
	public void addData(GameDataManager entity);
	
	public void addEntity(ImageView add);
	
	public void removeEntity(ImageView remove);
	
}
