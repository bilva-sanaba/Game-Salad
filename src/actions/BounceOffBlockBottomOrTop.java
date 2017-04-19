package actions;

import java.util.ArrayList;
import java.util.List;

import components.entityComponents.ComponentType;
import components.movementcomponents.VelocityComponent;
import entity.Entity;
import entity.IEntity;

public class BounceOffBlockBottomOrTop implements IAction{

	public BounceOffBlockBottomOrTop() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<IEntity> executeAction(IEntity e,IEntity e2) {
		VelocityComponent vc = (VelocityComponent) e.getComponent(ComponentType.Velocity);
		vc.setY(0);
		//Does anything need to be done about acceleration?
		return new ArrayList<IEntity>();

	}

}
