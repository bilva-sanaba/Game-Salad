package components;

public class AccelerationComponent extends XYComponent implements IComponent {

	public AccelerationComponent(int x, int y) {
		super(x, y);
	}

	public AccelerationComponent() {
		super();
	}

	@Override
	public ComponentType getComponentType() {
		return ComponentType.Acceleration;
	}

}
