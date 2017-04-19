package components.movementcomponents;

import components.IComponent;
import components.entityComponents.ComponentType;
import components.entityComponents.XYComponent;

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
	public IComponent newCopy() {
		return new VelocityComponent(getX(), getY());
	}
}
