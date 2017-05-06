package components.entityComponents;

import components.IComponent;
import components.XYComponent;
/**
 * Component used by movement engine to update location
 * @author Bilva
 *
 */

public class AccelerationComponent extends XYComponent implements IComponent {

	public AccelerationComponent(double x, double y) {
		super(x, y);
	}
	
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
	@Override
	public IComponent newCopy() {
		return new AccelerationComponent(getX(), getY());
	}

}
