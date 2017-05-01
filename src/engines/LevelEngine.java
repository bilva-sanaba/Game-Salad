package engines;

import java.util.Collection;
import java.util.List;

import components.entityComponents.ComponentType;
import components.entityComponents.GoalComponent;
import components.entityComponents.LocationComponent;
import entity.IEntity;
import entity.IEntityManager;
import gamedata.GameData;
import gamedata.GameDataFactory;
import gamedata.IRestrictedGameData;
import javafx.scene.input.KeyCode;

public class LevelEngine extends AbstractEngine{
	private List<IEntityManager> myEntityManagers;
	public LevelEngine(IEntityManager myEntityManager, List<IEntityManager> myEMs) {
		super(myEntityManager);
		myEntityManagers = myEMs;
	}

	@Override
	public IRestrictedGameData update(Collection<KeyCode> keysPressed, IRestrictedGameData currentGameData) {
		GameDataFactory gdf = new GameDataFactory();
		GameData gd = gdf.blankEntityData(currentGameData);
		for(IEntity e: getEManager().getEntities()){
			if(hasComponent(e, ComponentType.Goal)){
				GoalComponent gc = (GoalComponent) e.getComponent(ComponentType.Goal);
				if(gc.checkIfSatisfied() == true){
					gd.setLevel(gd.getLevel().intValue()+1);
					((LocationComponent) e.getComponent(ComponentType.Location)).setX(0);
					gc.satisfyGoal(false);
				}
			}
			
		}

		return gd;
	}
}