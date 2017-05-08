// This entire file is part of my masterpiece.
// Hamsa Pillai
package actions;

/**
 * This class exists to allow for different implementations of creating a new IAction class during runtime. This allows for someone to step in and implement a completely different design
 * which may be better for specific situations, and still have the program run with only one or two changes. This interface makes all implementations closed to modification.
 * @author Hamsa
 *
 */
public interface IUserActionFactory {

	
	/**
	 * Creates and saves a new user-defined IAction class given the contents for the execute method and the name for the new IAction.
	 * @param executeMethodContents : the String representing the user's implementation of the execute method in IAction
	 * @param actionClassName : the String representing the name the user wants to assign to the IAction. Also used for retrieval of that class.
	 * @return the Class created if successful
	 * @throws InstantiationException if the creation was unsuccessful.
	 */
	public Class<?> createNewIAction(String executeMethodContents, String actionClassName) throws InstantiationException;
	
	/**
	 * Creates a new instance of a defined user-created IAction class.
	 * @param actionClassName : the String representing the name of the class that has been created.
	 * @return an IAction object of that class if it exists
	 * @throws exception if that class has not been defined or if an object could not be made.
	 */
	public IAction createInstance(String actionClassName) throws InstantiationException, IllegalAccessException, ClassNotFoundException;
}

