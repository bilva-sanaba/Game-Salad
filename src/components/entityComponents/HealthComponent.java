package components.entityComponents;

import components.AComponent;
import components.IComponent;

/**
 * 
 * @author Jack
 *
 */
public class HealthComponent extends AComponent implements IComponent {
	private int health;

	public HealthComponent(int h) {
		health = h;
	}
	
	public HealthComponent(){
		super();
	}

	@Override
	public ComponentType getComponentType() {
		return ComponentType.Health;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int h) {
		health = h;
	}

	public IComponent newCopy() {
		return new HealthComponent(getHealth());
	}
}
