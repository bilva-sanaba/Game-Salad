package components.entityComponents;

import components.AbstractOneParameterComponent;
import components.IComponent;

/**
 * @author Justin
 * 
 */
public class GameInstructionsComponent extends AbstractOneParameterComponent<String> implements IComponent {

	
	public GameInstructionsComponent(String instructions) {
		super(instructions);
	}
	
	public GameInstructionsComponent() {
		super();
	}
	
	@Override
	public ComponentType getComponentType() {
		return ComponentType.GameInstructions;
	}
}
