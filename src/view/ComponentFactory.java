package view;

import components.IComponent;
import voogasalad.util.reflection.*;

public class ComponentFactory {
	private static final String PREFIX = "components.";
	private static final String SUFFIX = "Component";

	public IComponent getEvent(String eventname) {
		IComponent reflectedComponent;
		try {
			reflectedComponent = (IComponent) Reflection.createInstance(PREFIX + eventname + SUFFIX);
		} catch (Exception e) {
			throw new ReflectionException(ReflectionException.COMPONENT_REFLECTION_ERROR);
		}
		return reflectedComponent;
	}
}
