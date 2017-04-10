package components;

public class VelocityComponent extends XYComponent implements IComponent {
	public VelocityComponent(int x, int y) {
		super(x, y);
	}

	public VelocityComponent() {
		super();
	}

	@Override
	public ComponentType getComponentType() {
		return ComponentType.Velocity;
	}
	public IComponent newCopy() {
		return new VelocityComponent(getX(), getY());
	}
}
