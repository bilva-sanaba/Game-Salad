package engines;

import java.util.Collection;
import java.util.List;

import components.entityComponents.ComponentType;
import components.entityComponents.GoalComponent;
import entity.IEntity;
import entity.IEntityManager;
import javafx.scene.input.KeyCode;

public class CameraEngine extends AbstractEngine {

	public CameraEngine(IEntityManager myEntityManager) {
		super(myEntityManager);
	}

	@Override
	protected List<ComponentType> neededComponents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Collection<KeyCode> keysPressed) {
		for(IEntity e: getEManager().getEntities()){
			if(hasComponent(e, ComponentType.Camera)){
				GoalComponent gc = (GoalComponent) e.getComponent(ComponentType.Camera);
				if(gc.checkIfSatisfied() == true){
				//	System.out.println("hi this works");
				}
			}
			
		}
	}

}
