package actions;

import entity.IEntity;
import entity.IEntityManager;
import gamedata.GameData;
import gamedata.IRestrictedGameData;

public class IPointsAction extends AbstractAction implements IAction {
	public IPointsAction(double d){
		
	}
	@Override
	public IRestrictedGameData executeAction(IEntity player, IEntity npc, IEntityManager myEM,
			IRestrictedGameData currentGameData) {
		GameData gd = getGameDataFactory().blankEntityData(currentGameData);
		gd.setPoints(gd.getPoints().get()+1);
		return (IRestrictedGameData) gd;
	}

}
