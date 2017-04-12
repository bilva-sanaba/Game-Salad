package components;

public class StrengthComponent implements IComponent {
	private int strength;

	public StrengthComponent(int s) {
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

	public int getStrength() {
		return strength;
	}

	public void setHealth(int s) {
		strength = s;
	}

	public IComponent newCopy() {
		return new HealthComponent(getStrength());
	}
}
