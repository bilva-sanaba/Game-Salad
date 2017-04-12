package actions;

import entity.Entity;

public interface IAction {
	/**
	 * This method should take in an entity, get the necessary components for it, and modify those components in a way that is relevant to the entity it 
	 * @param e
	 */
	public void executeAction(Entity e);
	
}