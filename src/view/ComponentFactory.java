package view;

import components.IComponent;
import view.editor.ComponentEditor;
import view.editor.newComponentFactory;
import voogasalad.util.reflection.Reflection;
import voogasalad.util.reflection.ReflectionException;

public class ComponentFactory implements newComponentFactory {
	private static final String PREFIX = "components.entityComponents.";
	private static final String EDITOR_PREFIX = "view.editor.";
	private static final String SUFFIX = "Component";
	private static final String EDITOR_SUFFIX = "Editor";
	
	public IComponent getComponent(String componentName) {
		IComponent reflectedComponent;
		try {
			reflectedComponent = (IComponent) Reflection.createInstance(PREFIX + componentName + SUFFIX);
		} catch (Exception e) {
			throw new ReflectionException(ReflectionException.COMPONENT_REFLECTION_ERROR);
		}
		return reflectedComponent;
	}
	
	public IComponent getComponent(String componentName, Object...objects) {
		IComponent reflectedComponent;
		try {
			reflectedComponent = (IComponent) Reflection.createInstance(PREFIX + componentName + SUFFIX, objects);
		} catch (Exception e) {
			throw new ReflectionException(ReflectionException.COMPONENT_REFLECTION_ERROR);
		}
		return reflectedComponent;
	}


	public ComponentEditor getComponentEditor(String comp, UtilityFactory myUtilF) {
		ComponentEditor reflectedComponent;
		try {
			System.out.println(EDITOR_PREFIX + comp + EDITOR_SUFFIX);
			reflectedComponent = (ComponentEditor) Reflection.createInstance(EDITOR_PREFIX + comp + EDITOR_SUFFIX, myUtilF);
		} catch (Exception e) {
			throw new ReflectionException(ReflectionException.COMPONENT_REFLECTION_ERROR);
		}
		return reflectedComponent;
	}
}
