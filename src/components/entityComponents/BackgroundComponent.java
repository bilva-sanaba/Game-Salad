package components.entityComponents;

import components.AComponent;
import components.AbstractOneParameterComponent;
import components.AbstractStringComponent;
import components.IComponent;

public class BackgroundComponent extends AbstractStringComponent implements IComponent {
	public BackgroundComponent(String fileP) {
		super(fileP);
	}

	@Override
	public ComponentType getComponentType() {
		return ComponentType.Background;
	}	
}
