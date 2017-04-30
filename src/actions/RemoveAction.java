package actions;

import entity.IEntity;
import entity.IEntityManager;
import gamedata.GameData;
import gamedata.IGameData;
import gamedata.IRestrictedGameData;

public class RemoveAction extends AbstractAction  implements IAction {

	@Override
	public IRestrictedGameData executeAction(IEntity other, IEntity self, IEntityManager myEM,
			IRestrictedGameData currentGameData) {
		return new PowerupUsage().executeAction(self,other,myEM,currentGameData);
	}

}
