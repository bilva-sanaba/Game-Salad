package actions;

import java.util.ArrayList;
import java.util.List;

import components.entityComponents.ComponentType;
import components.entityComponents.GoalComponent;
import entity.IEntity;
import entity.IEntityManager;

public class GoalAction implements IAction{

	@Override
	public List<IEntity> executeAction(IEntity player, IEntity npc, IEntityManager myEM) {
		for(IEntity e: myEM.getEntities()){
			if(e.getComponent(ComponentType.Goal) != null){
				GoalComponent gc = (GoalComponent) e.getComponent(ComponentType.Goal);
				gc.satisfyGoal();
			}
		}
		return new ArrayList<IEntity>();
	}

}
