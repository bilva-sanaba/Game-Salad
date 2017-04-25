package engines;

import java.util.Collection;
import java.util.List;

import components.entityComponents.BackCameraComponent;
import components.entityComponents.ComponentType;
import components.entityComponents.FrontCameraComponent;
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
			//Front camera
			if(hasComponent(e, ComponentType.FrontCamera)){
				FrontCameraComponent fc = (FrontCameraComponent) e.getComponent(ComponentType.FrontCamera);
				if(fc.getScrolling()){
					System.out.println("front scrolling is working");
				}

			}
			//Back camera
			if(hasComponent(e, ComponentType.FrontCamera)){
				BackCameraComponent bc = (BackCameraComponent) e.getComponent(ComponentType.BackCamera);
				if(bc.getScrolling()){
					System.out.println("back scrolling is working");
				}

			}
		}
			
	}
}


