// This entire file is part of my masterpiece.
// Hamsa Pillai

package actions;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * This class's purpose is to create an IAction object from an already existing IAction class. This is it's only responsibility and it does this through reflection. Although the name
 * implies this is a Factory, it actually isn't. Whereas a Factory class does instantiation through hierarchy, this just uses reflection to create an IAction. As you can see, it's a very simple class with one public method (2 with the constructor).
 * However, all exceptions are handled and errors that can naturally arise from reflection are corrected for. Additionally, this implements an interface. Therefore, this class is closed to modification since any instance of this class elsewhere can be 
 * replaced with an extension of this class or another implementation of IActionFactory.
 * @author Hamsa
 *
 */
public class ActionFactory implements IActionFactory {
	
	/**
	 * This is the error message thrown when an exception occurs during class reflection.
	 */
	public static final String ERROR_MESSAGE = "Unable to create the IAction you specified with the input you gave.";

	/**
	 * Empty (and only) constructor for ActionFactory.
	 */
	public ActionFactory() {
		
	}
	
	/**
	 * Tries to create an instance of the given IAction class with the given inputs.
	 * @param classToReflect : the IAction class you wish to create an instance of.
	 * @param actionObjectInputs : the inputs to the constructor for the IAction class. Leave this as null if the class doesn't need inputs to its constructor.
	 * @return the desired IAction object if reflection is successful.
	 * @throws exception if reflection is unsuccessful.
	 */
	public IAction createAction(Class<?> classToReflect, List<String> actionObjectInputs) throws Exception {
		if (classToReflect == null) {
			throw new ClassNotFoundException(ERROR_MESSAGE);
		}
		try {
			
			Constructor<?> actionConstructor = getConstructor(classToReflect, actionObjectInputs);
			return createFromConstructor(actionObjectInputs, actionConstructor);
			
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new Exception(ERROR_MESSAGE);

		}
		
		
	}
	
	
	/**
	 * This method gets the Constructor for a given class. It will return either the general IAction constructor or it will return a declared constructor
	 * if actionObjectInputs isn't null or empty.
	 * @param classToReflect : the IAction class you wish to retrieve the constructor for
	 * @param actionObjectInputs : specifies which constructor to get from the IAction class. Leave as null if there are no inputs.
	 * @return the appropriate constructor for the class.
	 * @throws NoSuchMethodException if constructors can't be found.
	 */
	private Constructor<?> getConstructor(Class<?> classToReflect, List<String> actionObjectInputs)
			throws NoSuchMethodException {
		Constructor<?> actionConstructor = (Constructor<?>) classToReflect.getConstructor();
		Constructor<?> declaredConstructor = classToReflect.getDeclaredConstructor(new Class<?>[]{actionObjectInputs.getClass()});
		if (declaredConstructor!= null) {
			actionConstructor = declaredConstructor;
		}
		return actionConstructor;
	}
	
	
	/**
	 * Given a constructor and inputs, this will create an instance from the constructor using the inputs.
	 * @param actionObjectInputs : inputs to constructor, leave as null if no inputs.
	 * @param actionConstructor : the constructor to create an object from
	 * @return the IAction created from the constructor if successful
	 * @throws exception if construction is unsuccessful
	 */
	private IAction createFromConstructor(List<String> actionObjectInputs, Constructor<?> actionConstructor)
			throws InstantiationException, IllegalAccessException, InvocationTargetException {
		
		IAction createdAction;
		if (actionObjectInputs!=null && actionObjectInputs.size()>0) {
			createdAction = (IAction) actionConstructor.newInstance(actionObjectInputs);
		} else {
			createdAction = (IAction) actionConstructor.newInstance();
		}
		return createdAction;
	}
	

}
