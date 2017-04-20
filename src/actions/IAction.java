package actions;

import java.util.List;

import entity.Entity;
import entity.IEntity;

public interface IAction {
	/**
	 * This method should take in an entity, get the necessary components for it, and modify those components in a way that is relevant to the entity it 
	 * @param e
	 */
	public List<IEntity> executeAction(IEntity player, IEntity npc);
	
}