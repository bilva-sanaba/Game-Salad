package components;

public class AccelerationComponent extends XYComponent implements IComponent {

	public AccelerationComponent(double x, double y) {
		super(x, y);
	}

	public AccelerationComponent() {
		super();
	}

	@Override
	public ComponentType getComponentType() {
		return ComponentType.Acceleration;
	}
	@Override
	public IComponent newCopy() {
		return new AccelerationComponent(getX(), getY());
	}

}
