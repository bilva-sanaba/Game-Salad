package view.editor;

import components.IComponent;

public interface newComponentFactory {
	public IComponent getComponent(String componentName, Object...objects);

}
