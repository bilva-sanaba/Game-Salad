package gameView.gameScreen;

import javafx.scene.image.ImageView;
import entity.restricted.IRestrictedEntityManager;
import gamedata.GameData;

public interface IGameScreenEntity {

	/**
	 * Add an entity 
	 * @param entity - entity to add
	 */
	public void addData(GameData entity);
	
	public void addEntity(ImageView add);
	
	public void removeEntity(ImageView remove);
	
}
