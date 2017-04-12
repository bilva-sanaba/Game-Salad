package view;

import java.io.File;

import components.IComponent;
import javafx.scene.Node;
import view.editor.ComponentEditor;
import view.editor.newComponentFactory;
import voogasalad.util.reflection.Reflection;
import voogasalad.util.reflection.ReflectionException;

public class ComponentFactory implements newComponentFactory {
	private static final String PREFIX = "components.entityComponents.";
	private static final String EDITOR_PREFIX = "view.editor.";
	private static final String SUFFIX = "Component";
	private static final String EDITOR_SUFFIX = "Editor";
	
	public ComponentFactory(){
		System.out.println("bloomfeld is actually bloomfeild");
	}

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
	
	public IComponent getComponent(String componentName, Object...objects) {
		IComponent reflectedComponent;
		try {
			System.out.println(PREFIX + componentName + SUFFIX + "WHAT THE FUCK \n");
			reflectedComponent = (IComponent) Reflection.createInstance(PREFIX + componentName + SUFFIX, objects);
		} catch (Exception e) {
			throw new ReflectionException(ReflectionException.COMPONENT_REFLECTION_ERROR);
		}
		return reflectedComponent;
	}

	public ComponentEditor getComponentEditor(String comp) {
		System.out.println(EDITOR_PREFIX + comp + EDITOR_SUFFIX);
		ComponentEditor reflectedComponent;
		try {
			reflectedComponent = (ComponentEditor) Reflection.createInstance(EDITOR_PREFIX + comp + EDITOR_SUFFIX);
			System.out.println(reflectedComponent.getInputNode());
		} catch (Exception e) {
			System.out.println("i shouldnt be here");
			throw new ReflectionException(ReflectionException.COMPONENT_REFLECTION_ERROR);
		}
		return reflectedComponent;
	}
}
