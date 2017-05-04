package actions;

import entity.IEntity;
import entity.IEntityManager;
import gamedata.IRestrictedGameData;

public interface IAction {
	/**
	 * This method should take in an entity, get the necessary components for it, and modify those components in a way that is relevant to the entity it 
	 * @param currentGameData TODO
	 * @param e
	 */
	public IRestrictedGameData executeAction(IEntity other, IEntity self, IEntityManager myEM, IRestrictedGameData currentGameData);

}