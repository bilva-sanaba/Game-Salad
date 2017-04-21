package actions;

import java.util.ArrayList;
import java.util.List;

import components.entityComponents.AccelerationComponent;
import components.entityComponents.ComponentType;
import components.entityComponents.VelocityComponent;
import entity.IEntity;
import entity.IEntityManager;

public class BounceOffBlockSide implements IAction {

	public BounceOffBlockSide() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<IEntity> executeAction(IEntity e,IEntity e2, IEntityManager myEM) {
		AccelerationComponent ac = (AccelerationComponent) e.getComponent(ComponentType.Acceleration);
		VelocityComponent vc = (VelocityComponent) e.getComponent(ComponentType.Velocity);
		ac.setX(-1*ac.getX()); //UNSURE IF ACCELERATION SHOULD JUST REVERSED
		ac.setY(1*ac.getY());
		return new ArrayList<IEntity>();

	}

}
