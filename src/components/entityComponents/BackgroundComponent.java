package components.entityComponents;

import components.AComponent;
import components.AbstractOneParameterComponent;
import components.IComponent;

public class BackgroundComponent extends AbstractOneParameterComponent<String> implements IComponent {
	public BackgroundComponent(String fileP) {
		super(fileP);
	}

	@Override
	public ComponentType getComponentType() {
		return ComponentType.Background;
	}	
}
