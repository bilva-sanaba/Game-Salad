package components.entityComponents;

import components.AComponent;
import components.IComponent;

public class StationaryComponent extends AComponent implements IComponent {
	private boolean station = false;

	public StationaryComponent(boolean s) {
		station = s;
	}
	
	public StationaryComponent(){
		super();
	}

	@Override
	public ComponentType getComponentType() {
		return ComponentType.Collidable;
	}

	public boolean getStationary() {
		return station;
	}

	public void setStationary(boolean s) {
		station = s;
	}

	public IComponent newCopy() {
		return new CollidableComponent(getStationary());
	}
}