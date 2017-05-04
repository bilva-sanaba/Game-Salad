package actions;

import java.util.ArrayList;
import java.util.List;

import class_annotations.BottomAction;
import class_annotations.LeftAction;
import class_annotations.RightAction;
import class_annotations.TopAction;
import components.entityComponents.ComponentType;
import components.entityComponents.GoalComponent;
import entity.IEntity;
import entity.IEntityManager;
import gamedata.IGameData;
import gamedata.IRestrictedGameData;
@LeftAction()
@RightAction()
@TopAction()
@BottomAction()
public class GoalAction  extends AbstractAction  implements IAction{
	private int toLevel;
	public GoalAction(int level){
		toLevel=level;
	}
	public GoalAction(List<String> x) {
		toLevel = Integer.parseInt(x.get(0));
	}
	@Override
	public IRestrictedGameData executeAction(IEntity other, IEntity self, IEntityManager myEM, IRestrictedGameData currentGameData) {
		IGameData gameData =getGameDataFactory().blankEntityData(currentGameData);
		gameData.setLevel(toLevel);
//		for(IEntity e : myEM.getEntities()){
//			if(e.getComponent(ComponentType.Goal) != null){
//				GoalComponent gc = (GoalComponent) e.getComponent(ComponentType.Goal);
//				gc.satisfyGoal(true);
//			}
//		}
		return gameData;
	}

}
