package actions;

import components.entityComponents.AccelerationComponent;
import components.entityComponents.ComponentType;
import components.entityComponents.VelocityComponent;
import entity.Entity;

public class BounceOffBlockSide implements IAction {

	public BounceOffBlockSide() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void executeAction(Entity e) {
		AccelerationComponent ac = (AccelerationComponent) e.getComponent(ComponentType.Acceleration);
		VelocityComponent vc = (VelocityComponent) e.getComponent(ComponentType.Velocity);
		ac.setX(-1*ac.getX()); //UNSURE IF ACCELERATION SHOULD JUST REVERSED
		ac.setY(1*ac.getY());
	}

}
