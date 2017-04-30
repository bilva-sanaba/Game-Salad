package components.entityComponents;

import actions.AbstractAction;
import actions.IAction;
import components.keyExpressions.RightAction;
import entity.IEntity;
import entity.IEntityManager;
import gamedata.IGameData;
import gamedata.IRestrictedGameData;

public class AcheivementAction extends AbstractAction implements IAction{

	@Override
	public IRestrictedGameData executeAction(IEntity other, IEntity self, IEntityManager myEM,
			IRestrictedGameData currentGameData) {
		IGameData gd = getGameDataFactory().blankEntityData(currentGameData);
		gd.setAchievement("FirstKill");
		return new RightAction().executeAction(other, self, myEM, currentGameData);
	}

}
