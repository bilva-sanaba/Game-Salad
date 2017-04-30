package components.entityComponents;

import components.IComponent;

public class NullComponent implements IComponent {

	@Override
	public ComponentType getComponentType() {
		return ComponentType.Null;
	}

	@Override
	public IComponent newCopy() {
		return new NullComponent();
	}

}
