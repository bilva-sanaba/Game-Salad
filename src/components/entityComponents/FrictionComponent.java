package components.entityComponents;

import components.IComponent;
/**
 * Component which is used by the movement engine to determine if an entity will experience deacceleration
 * @author Bilva
 *
 */

public class FrictionComponent implements IComponent {
	private boolean friction;

	public FrictionComponent(boolean c) {
		friction = c;
	}
	
	public FrictionComponent(){
		super();
	}

	@Override
	public ComponentType getComponentType() {
		return ComponentType.Friction;
	}

	public boolean getFriction() {
		return friction;
	}

	public void setFriction(boolean c) {
		friction = c;
	}

	public IComponent newCopy() {
		return new FrictionComponent(getFriction());
	}

}
