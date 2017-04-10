package view;

import java.io.File;

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
			System.out.println(PREFIX + componentName + SUFFIX + "WHAT THE FUCK \n");
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
			System.out.println(reflectedComponent.getInputNode());
		} catch (Exception e) {
			System.out.println("i shouldnt be here");
			throw new ReflectionException(ReflectionException.COMPONENT_REFLECTION_ERROR);
		}
		return reflectedComponent;
	}
}
