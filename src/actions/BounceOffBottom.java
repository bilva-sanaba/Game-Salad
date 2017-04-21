package actions;

import java.util.ArrayList;
import java.util.List;

import class_annotations.BottomAction;
import components.entityComponents.ComponentType;
import components.movementcomponents.VelocityComponent;
import entity.IEntity;
import entity.IEntityManager;

@BottomAction()

public class BounceOffBottom implements IAction {
	public static final double VELOCITY_REVERSE = -1;
	public static final double BOUNCE_FACTOR = 0.5;
	

	@Override
	public List<IEntity> executeAction(IEntity player, IEntity npc, IEntityManager myEM) {
		VelocityComponent vc = (VelocityComponent) player.getComponent(ComponentType.Velocity);
		if (vc.getY()<0) {
			vc.setY(vc.getY()*VELOCITY_REVERSE*BOUNCE_FACTOR);
		}
		return new ArrayList<IEntity>();
	}
}
