package actions;

import components.entityComponents.ComponentType;
import components.entityComponents.ImagePropertiesComponent;
import entity.Entity;

public class Explosion implements IAction {

	@Override
	public void executeAction(Entity e) {
		ImagePropertiesComponent ic = e.getComponent(ComponentType.ImageProperties);
		
	}

}
