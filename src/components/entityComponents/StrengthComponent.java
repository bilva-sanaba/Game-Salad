package components.entityComponents;

import components.IComponent;

public class StrengthComponent implements IComponent {
	private double strength;

	public StrengthComponent(double s) {
		strength = s;
	}
	
	public StrengthComponent(){
		super();
	}

	@Override
	public ComponentType getComponentType() {
		// TODO Auto-generated method stub
		return ComponentType.Strength;
	}

	public double getStrength() {
		return strength;
	}

	public void setHealth(double s) {
		strength = s;
	}

	public IComponent newCopy() {
		return new StrengthComponent(getStrength());
	}
}