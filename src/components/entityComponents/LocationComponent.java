package components.entityComponents;

import components.IComponent;
import components.XYComponent;

/**
 * Component used for determining the location of an object
 * 
 * @author Bilva
 *
 */
public class LocationComponent extends XYComponent implements IComponent {
	public LocationComponent(double x, double y){
		super(x, y);
	}
	
	public LocationComponent(int x, int y) {
		super(x,y);
	}

	public LocationComponent() {
		super();
	}

	@Override
	public ComponentType getComponentType() {
		return ComponentType.Location;
	}
	public IComponent newCopy() {
		return new LocationComponent(getX(), getY());
	}
}
