package actions;

import entity.IEntity;
import entity.IEntityManager;
import gamedata.GameData;
import gamedata.IRestrictedGameData;

public class RemoveAction extends AbstractAction  implements IAction {

	@Override
	public IRestrictedGameData executeAction(IEntity other, IEntity self, IEntityManager myEM,
			IRestrictedGameData currentGameData) {
		// TODO Auto-generated method 
		return new GameData(currentGameData.getPoints().get(), currentGameData.getLives().get() , currentGameData.getRestrictedEntityManager(), currentGameData.getLevel().get(), currentGameData.getMainLocation(), "FirstKill", currentGameData.getMusic());
	}

}
