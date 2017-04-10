package components;

/**
 * 
 * @author Jack
 *
 */
public class HealthComponent implements IComponent {
	private int health;

	public HealthComponent(int h) {
		health = h;
	}

	@Override
	public ComponentType getComponentType() {
		// TODO Auto-generated method stub
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
