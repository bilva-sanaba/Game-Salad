//THIS IS PART OF MY MASTERPIECE

package actions;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class ActionFactory {
	
	
	public static final String ERROR_MESSAGE = "Unable to create the IAction you specified with the input you gave.";

	public ActionFactory() {
		
	}
	
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
	
	

	private Constructor<?> getConstructor(Class<?> classToReflect, List<String> actionObjectInputs)
			throws NoSuchMethodException {
		Constructor<?> actionConstructor = (Constructor<?>) classToReflect.getConstructor();
		Constructor<?> declaredConstructor = classToReflect.getDeclaredConstructor(new Class<?>[]{actionObjectInputs.getClass()});
		if (declaredConstructor!= null) {
			actionConstructor = declaredConstructor;
		}
		return actionConstructor;
	}
	
	

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
