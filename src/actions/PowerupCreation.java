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
		ObjectCreationComponent occ = ((ObjectCreationComponent) e2.getComponent(ComponentType.ObjectCreation));
		if (occ.checkIfCreation()){
		
		IEntity powerup = ((ObjectCreationComponent) e2.getComponent(ComponentType.ObjectCreation)).getCreationEffect();
		if (powerup!=null){
			entities.add(powerup);
		}
		}
		return entities;
	}
}
