package gameView.gameScreen;

import entity.restricted.IRestrictedEntityManager;
import gamedata.GameData;

public interface IGameScreenEntity {

	/**
	 * Add an entity 
	 * @param entity - entity to add
	 */
	public void addData(GameData entity);
	
}
