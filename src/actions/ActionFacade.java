//THIS IS PART OF MY MASTERPIECE
package actions;

import java.util.List;

import components.entityComponents.CollisionComponentType;

public class ActionFacade {
	
	
	public static final CollisionComponentType TOP = CollisionComponentType.Top;
	public static final CollisionComponentType BOTTOM = CollisionComponentType.Bottom;
	public static final CollisionComponentType LEFT = CollisionComponentType.Left;
	public static final CollisionComponentType RIGHT = CollisionComponentType.Right;


	
	private IActionRetriever existingActionRetriever;
	private ActionFactory actionCreator;
	private UserActionFactory userActionDefiner;

	public ActionFacade() {
		existingActionRetriever = new ActionRetriever();
		actionCreator = new ActionFactory();
		userActionDefiner = new UserActionFactory();
	}
	
	public List<Class<?>> getExistingActions(CollisionComponentType sideForAction) {
		return existingActionRetriever.getActionsWithAnnotation(sideForAction);
	}

	
	public IAction createAction(Class<?> IActionToCreate, List<String> inputsToAction) throws Exception {
		try { 
			return actionCreator.createAction(IActionToCreate, inputsToAction);
		} catch (Exception e) {
			try {
				return userActionDefiner.createInstance(IActionToCreate.getName());
			} catch (Exception anotherE) {
				throw anotherE;
			}
		}
	}
	
	public IAction defineNewAction(String executeMethodContents, String actionName) throws Exception {
		Class<?> toCreate = userActionDefiner.createNewIAction(executeMethodContents, actionName);
		return userActionDefiner.createInstance(actionName);
	}
	
	
	
}
