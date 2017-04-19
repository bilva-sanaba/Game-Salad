package actions;

import java.util.ArrayList;
import java.util.List;

import components.entityComponents.ComponentType;
import components.entityComponents.ImagePropertiesComponent;
import entity.Entity;
import entity.IEntity;

public class Explosion implements IAction {

	@Override
	public List<IEntity> executeAction(IEntity e, IEntity e2) {
		ImagePropertiesComponent ic = (ImagePropertiesComponent) e.getComponent(ComponentType.ImageProperties);
		return new ArrayList<IEntity>();
	}

}
