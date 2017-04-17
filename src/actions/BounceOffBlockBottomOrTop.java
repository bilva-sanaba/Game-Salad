package actions;

import components.entityComponents.ComponentType;
import components.entityComponents.VelocityComponent;
import entity.Entity;

public class BounceOffBlockBottomOrTop implements IAction{

	public BounceOffBlockBottomOrTop() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void executeAction(Entity e) {
		VelocityComponent vc = (VelocityComponent) e.getComponent(ComponentType.Velocity);
		vc.setY(vc.getY()*-1);
		//Does anything need to be done about acceleration?
		
	}

}
