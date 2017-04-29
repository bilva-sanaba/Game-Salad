package actions;

import entity.IEntity;
import entity.IEntityManager;
import gamedata.GameData;
import gamedata.IRestrictedGameData;

public class PointsAction extends AbstractAction implements IAction {
	private double increment;
	public PointsAction(double d){
		increment = d;
	}
	public PointsAction(){
		increment=1;
	}
	@Override
	public IRestrictedGameData executeAction(IEntity player, IEntity npc, IEntityManager myEM,
			IRestrictedGameData currentGameData) {
		GameData gd = getGameDataFactory().blankEntityData(currentGameData);
		gd.setPoints(gd.getPoints()+increment);
		return (IRestrictedGameData) gd;
	}

}
