package components.entityComponents;

import components.IComponent;

/**
 * @author Justin
 *
 */
public class GameTitleComponent implements IComponent {
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
	
	public String getGameTitle() {
		return myGameTitle;
	}
	
	public void setGameTitle(String title) {
		myGameTitle = title;
	}
	
	public IComponent newCopy() {
		return new GameTitleComponent(getGameTitle());
	}
}
