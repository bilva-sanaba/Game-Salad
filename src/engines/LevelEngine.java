package engines;

import java.util.Collection;
import java.util.List;

import components.entityComponents.ComponentType;
import components.entityComponents.GoalComponent;
import entity.IEntity;
import entity.IEntityManager;
import gamedata.GameData;
import gamedata.GameDataFactory;
import gamedata.IRestrictedGameData;
import javafx.scene.input.KeyCode;

public class LevelEngine extends AbstractEngine{

	public LevelEngine(IEntityManager myEntityManager) {
		super(myEntityManager);
	}

	@Override
	public IRestrictedGameData update(Collection<KeyCode> keysPressed, IRestrictedGameData currentGameData) {
		GameDataFactory gdf = new GameDataFactory();
		GameData gd = gdf.blankEntityData(currentGameData);
		for(IEntity e: getEManager().getEntities()){
			if(hasComponent(e, ComponentType.Goal)){
				GoalComponent gc = (GoalComponent) e.getComponent(ComponentType.Goal);
				if(gc.checkIfSatisfied() == true){
//					gd.setLevel(gd.getLevel()+1);
					gc.satisfyGoal(false);
				}
			}
			
		}

		return gd;
	}
}