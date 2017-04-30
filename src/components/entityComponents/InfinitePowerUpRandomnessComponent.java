package components.entityComponents;

import components.AComponent;
import components.IComponent;

public class InfinitePowerUpRandomnessComponent extends AComponent implements IComponent {
	private double powerUpRandomness;

	@Override
	public ComponentType getComponentType() {
		return ComponentType.InfinitePowerUpRandomness;
	}


	public InfinitePowerUpRandomnessComponent(double pu) {
		powerUpRandomness = pu;
	}
	
	public InfinitePowerUpRandomnessComponent(){
		super();
	}

	public double getPowerUpRandomness() {
		return powerUpRandomness;
	}

	public void setPowerUpRandomness(double pu) {
		powerUpRandomness = pu;
	}

	public IComponent newCopy() {
		return new InfinitePowerUpRandomnessComponent(getPowerUpRandomness());
	}

}
