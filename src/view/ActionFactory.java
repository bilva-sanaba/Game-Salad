package view;

import actions.IAction;
import voogasalad.util.reflection.Reflection;
import voogasalad.util.reflection.ReflectionException;

public class ActionFactory  {
	private static final String PREFIX = "actions.maker.";
	private static final String SUFFIX = "ActionMaker";

	public IAction getActionMaker(String actionName, Object...objects) {
		IAction reflectedAction;
		try {
			reflectedAction = (IAction) Reflection.createInstance(PREFIX + actionName + SUFFIX, objects);
		} catch (Exception e) {
			throw new ReflectionException(ReflectionException.COMPONENT_REFLECTION_ERROR);
		}
		return reflectedAction;
	}
}
