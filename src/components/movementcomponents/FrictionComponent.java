package components.movementcomponents;

import components.IComponent;
import components.entityComponents.ComponentType;

public class FrictionComponent implements IComponent {
	
	private int frictionMultiplier = 0;
	
	public FrictionComponent(int aFrictionMultiplier){
		frictionMultiplier = aFrictionMultiplier;
	}

	@Override
	public ComponentType getComponentType() {
		return ComponentType.Friction;
	}

	@Override
	public IComponent newCopy() {
		// TODO Auto-generated method stub
		return null;
	}

}
