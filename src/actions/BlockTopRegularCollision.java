package actions;
import components.entityComponents.AccelerationComponent;
import components.entityComponents.ComponentType;
import components.entityComponents.LabelComponent;
import components.entityComponents.VelocityComponent;
import entity.Entity;
public class BlockTopRegularCollision implements IAction {
	@Override
	public void executeAction(Entity e) {
		LabelComponent lc = (LabelComponent) e.getComponent(ComponentType.Label);
		if (lc == null) {
			System.out.println("wtaaaaaaaa");
		}
		if (!lc.getLabel().equals("Block")) {
			VelocityComponent vc = (VelocityComponent) e.getComponent(ComponentType.Velocity);
			AccelerationComponent ac = (AccelerationComponent) e.getComponent(ComponentType.Acceleration);
			
			
			System.out.println(vc.getY());
			if (vc.getY() > 0) {
				vc.setY(0);
				ac.setY(0);
			}
		}
	}
}