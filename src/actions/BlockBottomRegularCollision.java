package actions;

import java.util.ArrayList;
import java.util.List;

import components.entityComponents.ComponentType;
import components.entityComponents.LabelComponent;
import components.movementcomponents.AccelerationComponent;
import components.movementcomponents.VelocityComponent;
import entity.IEntity;

public class BlockBottomRegularCollision implements IAction {
	
	@Override
	public List<IEntity> executeAction(IEntity e, IEntity e2) {
		LabelComponent lc = (LabelComponent) e.getComponent(ComponentType.Label);
		if (lc == null) {
			System.out.println("wtaaaaaaaa");
		}
		if (!lc.getLabel().equals("Block")) {
			VelocityComponent vc = (VelocityComponent) e.getComponent(ComponentType.Velocity);
			AccelerationComponent ac = (AccelerationComponent) e.getComponent(ComponentType.Acceleration);
			
			
			System.out.println(vc.getY());
			if (vc.getY() <0) {
				vc.setY(0);
				ac.setY(0);
			}
		}
		return new ArrayList<IEntity>();

	}
}
