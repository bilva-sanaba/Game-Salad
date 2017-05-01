package gameView.gameScreen;

import javafx.scene.image.ImageView;
import gameView.gameDataManagement.GameDataManager;

public interface IGameScreenEntity {

	/**
	 * Add an entity 
	 * @param entity - entity to add
	 */
	public void addData(GameDataManager entity);
	
	public void addEntity(ImageView add);
	
	public void removeEntity(ImageView remove);
	
}
