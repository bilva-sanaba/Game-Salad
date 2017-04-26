package components.entityComponents;

import components.AComponent;
import components.IComponent;

public class CheckCollisionComponent extends AComponent implements IComponent {
	private boolean checkCollision;
	
	public CheckCollisionComponent (boolean checkCollisionsInEngine) {
		checkCollision = checkCollisionsInEngine;
	}
	
	public CheckCollisionComponent () {
		checkCollision = true;
	}

	public boolean getCheckCollision() {
		return checkCollision;
	}
	
	public void setCheckCollision(boolean checkCollisionsInEngine) {
		checkCollision = checkCollisionsInEngine;
	}
	
	@Override
	public ComponentType getComponentType() {
		
		return ComponentType.CheckCollision;
	}

	@Override
	public IComponent newCopy() {
		
		return new CheckCollisionComponent(checkCollision);
	}

	

}
