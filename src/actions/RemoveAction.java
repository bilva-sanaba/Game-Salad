package actions;

import components.entityComponents.SpriteComponent;
import entity.IEntity;
import entity.IEntityManager;
import gamedata.GameData;
import gamedata.IRestrictedGameData;

public class RemoveAction extends AbstractAction  implements IAction {

	@Override
	public IRestrictedGameData executeAction(IEntity other, IEntity self, IEntityManager myEM,
			IRestrictedGameData currentGameData) {
		// TODO Auto-generated method stub
		return new GameData(currentGameData.getPoints(), currentGameData.getLives() , currentGameData.getRestrictedEntityManager(), currentGameData.getLevel(), currentGameData.getMainLocation(), "FirstKill", currentGameData.getMusic());
	}

}
