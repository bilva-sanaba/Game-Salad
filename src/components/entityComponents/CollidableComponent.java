package components.entityComponents;

import components.AComponent;
import components.IComponent;

public class CollidableComponent extends AComponent implements IComponent {
	private boolean collide;

	public CollidableComponent(boolean c) {
		collide = c;
	}
	
	public CollidableComponent(){
		super();
	}

	@Override
	public ComponentType getComponentType() {
		return ComponentType.Collidable;
	}

	public boolean getCollide() {
		return collide;
	}

	public void setCollide(boolean c) {
		collide = c;
	}

	public IComponent newCopy() {
		return new CollidableComponent(getCollide());
	}
}