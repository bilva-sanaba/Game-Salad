package components.entityComponents;

import components.IComponent;
import components.XYComponent;
/**
 * Component used by movement engine to update the position of an entity
 * @author Bilva
 *
 */

public class VelocityComponent extends XYComponent implements IComponent {
	public VelocityComponent(double x, double y) {
		super(x, y);
	}

	public VelocityComponent(int x, int y) {
		super(x, y);
	}

	@Override
	public ComponentType getComponentType() {
		return ComponentType.Velocity;
	}
	/**
	 * Change in velocity due to error movement engine saving
	 */

	public IComponent newCopy() {
		return new VelocityComponent(getX(), getY()-0.000000000001);
	}
}
