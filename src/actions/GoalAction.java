package actions;

import java.util.ArrayList;
import java.util.List;

import components.entityComponents.ComponentType;
import components.entityComponents.GoalComponent;
import entity.IEntity;
import entity.IEntityManager;
import gamedata.GameDataFactory;
import gamedata.IRestrictedGameData;

public class GoalAction implements IAction{

	@Override
	public IRestrictedGameData executeAction(IEntity player, IEntity npc, IEntityManager myEM, IRestrictedGameData currentGameData) {
		
		for(IEntity e: myEM.getEntities()){
			if(e.getComponent(ComponentType.Goal) != null){
				GoalComponent gc = (GoalComponent) e.getComponent(ComponentType.Goal);
				gc.satisfyGoal();
			}
		}
		GameDataFactory gdf = new GameDataFactory();

		return gdf.blankEntityData(currentGameData);
	}

}
