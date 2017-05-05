package components.entityComponents;

import components.IComponent;
import components.XYComponent;
/**
 * Component used by movement engine to update location
 * @author Bilva
 *
 */
public class AccelerationComponent extends XYComponent implements IComponent {
	/**
	 * Sets the acceleration in 2D space of an entity
	 * @param x x acceleration
	 * @param y y acceleration
	 */
	public AccelerationComponent(double x, double y) {
		super(x, y);
	}
	/**
	 * Sets the acceleration in 2D space of an entity
	 * @param x x acceleration
	 * @param y y acceleration
	 */
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
