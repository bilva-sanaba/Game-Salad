package components.entityComponents;

import components.IComponent;

/**
 * 
 * @author Jack
 *
 */
public class HealthComponent implements IComponent {
	
	private double health;

	public HealthComponent(double h) {
		health = h;
	}
	
	public HealthComponent(){
		super();
	}

	@Override
	public ComponentType getComponentType() {
		return ComponentType.Health;
	}

	public double getHealth() {
		return health;
	}

	public void setHealth(double h) {
		health = h;
	}

	public IComponent newCopy() {
		return new HealthComponent(getHealth());
	}
}
