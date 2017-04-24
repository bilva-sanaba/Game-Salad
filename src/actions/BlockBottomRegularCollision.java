package actions;

import java.util.ArrayList;
import java.util.List;

import components.entityComponents.AccelerationComponent;
import components.entityComponents.ComponentType;
import components.entityComponents.LabelComponent;
import components.entityComponents.VelocityComponent;
import entity.IEntity;
import entity.IEntityManager;

public class BlockBottomRegularCollision implements IAction {
	
	@Override
	public List<IEntity> executeAction(IEntity e, IEntity e2, IEntityManager myEM) {
		LabelComponent lc = (LabelComponent) e.getComponent(ComponentType.Label);
		if (lc == null) {
		}
		if (!lc.getLabel().equals("Block")) {
			VelocityComponent vc = (VelocityComponent) e.getComponent(ComponentType.Velocity);
			AccelerationComponent ac = (AccelerationComponent) e.getComponent(ComponentType.Acceleration);
			if (vc.getY() <0) {
				vc.setY(0);
				ac.setY(0);
			}
		}
		return new ArrayList<IEntity>();

	}
}
