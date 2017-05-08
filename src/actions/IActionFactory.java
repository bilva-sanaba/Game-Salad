// This entire file is part of my masterpiece.
// Hamsa Pillai
package actions;

import java.util.List;

/**
 * This interface allows for different ways of creating an object from a given class. Also, it allows for changing the IAction framework and not having to modify any implementation of this interface.
 * @author Hamsa
 *
 */
public interface IActionFactory {

	/**
	 * Tries to create an instance of the given IAction class with the given inputs.
	 * @param classToReflect : the IAction class you wish to create an instance of.
	 * @param actionObjectInputs : the inputs to the constructor for the IAction class. Leave this as null if the class doesn't need inputs to its constructor.
	 * @return the desired IAction object if reflection is successful.
	 * @throws exception if reflection is unsuccessful.
	 */
	public IAction createAction(Class<?> classToReflect, List<String> actionObjectInputs) throws Exception;

}
