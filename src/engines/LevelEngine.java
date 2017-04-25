package engines;

import java.util.Collection;
import java.util.List;

import components.entityComponents.ComponentType;
import components.entityComponents.GoalComponent;
import entity.IEntity;
import entity.IEntityManager;
import gamedata.IRestrictedGameData;
import javafx.scene.input.KeyCode;

public class LevelEngine extends AbstractEngine{

	public LevelEngine(IEntityManager myEntityManager) {
		super(myEntityManager);
	}

	@Override
	protected List<ComponentType> neededComponents() {
		return null;
	}

	@Override
	public void update(Collection<KeyCode> keysPressed, IRestrictedGameData gameData) {
		for(IEntity e: getEManager().getEntities()){
			if(hasComponent(e, ComponentType.Goal)){
				GoalComponent gc = (GoalComponent) e.getComponent(ComponentType.Goal);
				if(gc.checkIfSatisfied() == true){
					//System.out.println("hi this works");
				}
			}
			
		}
	}
}
