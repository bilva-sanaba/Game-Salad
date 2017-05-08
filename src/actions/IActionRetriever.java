// This entire file is part of my masterpiece.
// Hamsa Pillai
package actions;

import java.util.List;

import components.entityComponents.CollisionComponentType;

/**
 * This interface also allows for easy swapping implementations easily. If someone has a better method for dynamic retrieval of IActions at runtime, they can just implement this interface and swap it in wherever it is called. Also makes all implementations of this interface closed.
 * @author Hamsa
 *
 */
public interface IActionRetriever {

	
	
	/**
	 * Returns a list of classes that have the annotation specified by the CollisionComponentType input.
	 * @param typeOfAction : the enum that specifies which side of an entity you want to retrieve IActions for.
	 * @return list of IAction classes that are applicable for the given entity side.
	 */
	public List<Class<?>> getActionsWithAnnotation(CollisionComponentType typeOfAction);
	
	
}
