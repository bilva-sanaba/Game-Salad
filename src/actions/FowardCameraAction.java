package actions;

import java.util.List;

import components.entityComponents.ComponentType;
import components.movementcomponents.VelocityComponent;
import entity.IEntity;
import entity.IEntityManager;

public class FowardCameraAction implements IAction{

	@Override
	public List<IEntity> executeAction(IEntity player, IEntity npc, IEntityManager myEM) {
		VelocityComponent playerVC = (VelocityComponent) player.getComponent(ComponentType.Velocity);
		
		for(IEntity e : myEM.getEntities()){
			VelocityComponent currentVC = (VelocityComponent) e.getComponent(ComponentType.Velocity);
			currentVC.setX(playerVC.getX());
		}
	}

}
