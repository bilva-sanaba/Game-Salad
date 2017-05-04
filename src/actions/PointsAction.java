package actions;

import java.util.List;

import class_annotations.BottomAction;
import class_annotations.LeftAction;
import class_annotations.RightAction;
import class_annotations.TopAction;
import entity.IEntity;
import entity.IEntityManager;
import exceptions.InputException;
import exceptions.NumericInputException;
import gamedata.GameData;
import gamedata.IRestrictedGameData;

@TopAction()
@LeftAction()
@RightAction()
@BottomAction()
public class PointsAction extends AbstractAction implements IAction {
	private double increment;
	public PointsAction(double d){
		increment = d;
	}
	public PointsAction(){
		increment=10;
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
		gd.setPoints(gd.getPoints().doubleValue()+50);
		return (IRestrictedGameData) gd;
	}

}
