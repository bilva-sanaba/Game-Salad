package components.entityComponents;

import components.AbstractOneParameterComponent;
import components.AbstractStringComponent;
import components.IComponent;

/**
 * @author Justin
 *
 */
public class GameTitleComponent extends AbstractStringComponent implements IComponent {
	private String myGameTitle;
	
	public GameTitleComponent(String gameTitle) {
		myGameTitle = gameTitle;
	}
	
	public GameTitleComponent() {
		super();
	}
	
	@Override
	public ComponentType getComponentType() {
		return ComponentType.GameTitle;
	}
}
