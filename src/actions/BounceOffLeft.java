package actions;

import java.util.ArrayList;
import java.util.List;

import class_annotations.LeftAction;
import components.entityComponents.AccelerationComponent;
import components.entityComponents.ComponentType;
import components.entityComponents.VelocityComponent;
import entity.IEntity;
import entity.IEntityManager;


@LeftAction()
public class BounceOffLeft implements IAction {
	
	public static final double VELOCITY_REVERSE = -1;
	public static final double BOUNCE_FACTOR = 0.5;

	@Override
	public List<IEntity> executeAction(IEntity e,IEntity e2,IEntityManager myEM) {
		AccelerationComponent ac = (AccelerationComponent) e.getComponent(ComponentType.Acceleration);
		VelocityComponent vc = (VelocityComponent) e.getComponent(ComponentType.Velocity);
		
		if(vc.getX() > 0) {
			vc.setX(vc.getX()*VELOCITY_REVERSE*BOUNCE_FACTOR);
		}
		
		return new ArrayList<IEntity>();

	}
}
