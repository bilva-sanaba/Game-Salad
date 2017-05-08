// This entire file is part of my masterpiece.
// Hamsa Pillai
package actions;

import java.util.List;

import components.entityComponents.CollisionComponentType;

/**
 * This class follows the Facade design pattern. The purpose of this class is to simplify the process of getting and creating IActions, which are an integral part of the program. It 
 * does this by making it so that you only depend on this class for all features that are IAction-related. This class itself depends on three different classes, but the way it is set up 
 * is easy to read and understand, especially with the clear variable names. Implementing IActions was previously tough for the FrontEnd since they had to receive a list of IAction classes
 * from the frontend and then reflect it to objects themselves. This class takes care of all that for them. The relatively complex system of getting annotated classes and reflecting them to objects
 * is all done through this simple interface. At the same time, the Facade itself does not to anything special. It only provides relevant methods and public static finals so that any caller can choose
 * the right method easily instead of having to search through the package explorer to find the relevant classes and call methods from those. Additionally, all exceptions are either handled or thrown in this class.
 * Lastly, the Facade follows the Single Responsibility Principle by doing nothing more than redirecting requests to the appropriate classes. It also allows for different implementations since it only makes calls to interfaces. Overall, the Facade design makes
 * dealing with IAction retrieval and creation MUCH easier while not becoming too involved itself.
 * @author Hamsa
 *
 */
public class ActionFacade {
	
	/**
	 * These public static finals are here to help users give inputs to methods below, especially since one asks for an enum which would require searching through the package explorer.
	 */
	
	/**
	 * Specifies the TOP side of an entity.
	 */
	public static final CollisionComponentType TOP = CollisionComponentType.Top;
	/**
	 * Specifies the BOTTOM side of an entity.
	 */
	public static final CollisionComponentType BOTTOM = CollisionComponentType.Bottom;
	/**
	 * Specifies the LEFT side of an entity.
	 */
	public static final CollisionComponentType LEFT = CollisionComponentType.Left;
	/**
	 * Specifies the RIGHT side of an entity.
	 */
	public static final CollisionComponentType RIGHT = CollisionComponentType.Right;


	
	private IActionRetriever existingActionRetriever;
	private IActionFactory actionCreator;
	private IUserActionFactory userActionDefiner;

	/**
	 * Constructor for an ActionFacade. Sets up dependencies so that it can execute given requests.
	 */
	public ActionFacade() {
		existingActionRetriever = new ActionRetriever();
		actionCreator = new ActionFactory();
		userActionDefiner = new UserActionFactory();
	}
	
	/**
	 * Returns a list of IActions that can be applied to a specific side of an entity. Ex. passing in CollisionComponentType.Left will return
	 * all IActions that can be stored in the left side of an entity.
	 * @param sideForAction : the CollisionComponentType enum that specifies which side you want IActions for.
	 * @return A list of classes that correspond to valid IActions.
	 */
	public List<Class<?>> getExistingActions(CollisionComponentType sideForAction) {
		return existingActionRetriever.getActionsWithAnnotation(sideForAction);
	}

	/**
	 * Creates an instance of an IAction given a Class to reflect from and a list of inputs to that IAction. This can also
	 * instantiate an instance of a runtime-created IAction (one that the user defines while creating a game).
	 * @param IActionToCreate : the class specifying which IAction to reflect into an object.
	 * @param inputsToAction : the inputs, if any, to the IAction. If the IAction does not need any inputs, leave this as null.
	 * @return IAction : the IAction desired, if the reflection was successful.
	 * @throws Exception : an exception specifying what went wrong, if reflection was unsuccessful.
	 */
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
	
	/**
	 * Creates a new user-defined IAction. This allows for users to define a new IAction's behavior while they are running their program: they don't need to touch actual code.
	 * @param executeMethodContents : the String which will constitute the body of the execute method in the new IAction.
	 * @param actionName : the String which represents the name they want to assign to this new IAction.
	 * @return IAction : the user-defined IAction, if creation was successful.
	 * @throws Exception : an exception specifying what went wrong during creation, if it was unsuccessful.
	 */
	public IAction defineNewAction(String executeMethodContents, String actionName) throws Exception {
		Class<?> toCreate = userActionDefiner.createNewIAction(executeMethodContents, actionName);
		return userActionDefiner.createInstance(actionName);
	}
	
	
	
}
