package actions;

import entity.IEntity;
import entity.IEntityManager;
import gamedata.IRestrictedGameData;

public class RemoveAction extends AbstractAction  implements IAction {

	@Override
	public IRestrictedGameData executeAction(IEntity player, IEntity npc, IEntityManager myEM,
			IRestrictedGameData currentGameData) {
		// TODO Auto-generated method stub
		return new PowerupUsage().executeAction(npc,player,myEM,currentGameData);
	}

}
