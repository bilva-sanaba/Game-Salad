//THIS IS PART OF MY MASTERPIECE
package actions;

import java.util.List;

import components.entityComponents.CollisionComponentType;

public interface IActionRetriever {

	
	
	
	public List<Class<?>> getActionsWithAnnotation(CollisionComponentType typeOfAction);
	
	
}
