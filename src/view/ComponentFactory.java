package view;

import components.IComponent;
import javafx.scene.Node;
import voogasalad.util.reflection.Reflection;
import voogasalad.util.reflection.ReflectionException;

public class ComponentFactory {
	private static final String PREFIX = "components.";
	private static final String PREFI = "view.";
	private static final String SUFFIX = "Component";
	private static final String SUFFI = "Editor";


	public IComponent getComponent(String componentName) {
		IComponent reflectedComponent;
		try {
			reflectedComponent = (IComponent) Reflection.createInstance(PREFIX + componentName + SUFFIX);
		} catch (Exception e) {
			throw new ReflectionException(ReflectionException.COMPONENT_REFLECTION_ERROR);
		}
		return reflectedComponent;
	}

	public ComponentEditor getComponentEditor(String comp) {
		System.out.println(PREFI + comp + SUFFI);
		ComponentEditor reflectedComponent;
		try {
			reflectedComponent = (ComponentEditor) Reflection.createInstance(PREFI + comp + SUFFI);
		} catch (Exception e) {
			throw new ReflectionException(ReflectionException.COMPONENT_REFLECTION_ERROR);
		}
		return reflectedComponent;
	}
}
