package actions;

import java.util.ArrayList;
import java.util.List;

import components.entityComponents.ComponentType;
import components.entityComponents.ObjectCreationComponent;
import entity.IEntity;
import entity.IEntityManager;

public class PowerupCreation implements IAction {
	@Override
	public List<IEntity> executeAction(IEntity e, IEntity e2, IEntityManager myEM) {
		List<IEntity> entities = new ArrayList<IEntity>();
		IEntity powerup = ((ObjectCreationComponent) e2.getComponent(ComponentType.ObjectCreation)).getCreationEffect();
		if (powerup!=null){
			entities.add(powerup);
			myEM.getEntities().add(powerup);
			myEM.changed(powerup);
		}
		return entities;
	}
}
