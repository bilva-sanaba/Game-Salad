package components.entityComponents;

import components.IComponent;
import components.XYComponent;

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
		return new VelocityComponent(getX(), getY()-0.000000000001);
	}
}
