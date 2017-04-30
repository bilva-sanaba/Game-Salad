package actions;

import java.util.List;

import entity.IEntity;
import entity.IEntityManager;
import exceptions.InputException;
import exceptions.NumericInputException;
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
	
	public PointsAction(String points) throws NumericInputException {
		increment = super.parseDouble(points);
	}
	
	public PointsAction(List<String> inputs) throws InputException {
		inputs = super.validateList(inputs, 1);
		increment = super.parseDouble(inputs.get(0));
	}
	
	@Override
	public IRestrictedGameData executeAction(IEntity player, IEntity npc, IEntityManager myEM,
			IRestrictedGameData currentGameData) {
		GameData gd = getGameDataFactory().blankEntityData(currentGameData);
		gd.setPoints(gd.getPoints()+increment);
		return (IRestrictedGameData) gd;
	}

}
