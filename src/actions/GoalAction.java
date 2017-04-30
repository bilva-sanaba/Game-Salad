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
import gamedata.IRestrictedGameData;
@LeftAction()
@RightAction()
@TopAction()
@BottomAction()
public class GoalAction  extends AbstractAction  implements IAction{

	@Override
	public IRestrictedGameData executeAction(IEntity other, IEntity self, IEntityManager myEM, IRestrictedGameData currentGameData) {
		
		for(IEntity e : myEM.getEntities()){
			if(e.getComponent(ComponentType.Goal) != null){
				GoalComponent gc = (GoalComponent) e.getComponent(ComponentType.Goal);
				gc.satisfyGoal(true);
			}
		}
		return getGameDataFactory().blankEntityData(currentGameData);
	}

}
