package components.keyExpressions;

import java.util.ArrayList;
import java.util.List;

import actions.IAction;
import class_annotations.KeyAction;
import components.entityComponents.ComponentType;
import components.movementcomponents.AccelerationComponent;
import components.movementcomponents.VelocityComponent;
import entity.IEntity;
import entity.IEntityManager;

@KeyAction()

public class JumpAction implements IAction {

	@Override
	public List<IEntity> executeAction(IEntity player, IEntity npc, IEntityManager myEM) {
		VelocityComponent vc = (VelocityComponent) player.getComponent(ComponentType.Velocity);
		AccelerationComponent ac = (AccelerationComponent) player.getComponent(ComponentType.Acceleration);
		if (vc.getY()==0){
			vc.setY(-5);
			ac.setY(.1);
		}
		List<IEntity> entities = new ArrayList<IEntity>();
		return entities;
	}

}
