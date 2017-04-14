package components.entityComponents;

import components.IComponent;

/**
 * @author Justin
 *
 */
public class GameInstructionsComponent implements IComponent {
	private String myGameInstructions;
	
	public GameInstructionsComponent(String instructions) {
		myGameInstructions = instructions;
	}
	
	public GameInstructionsComponent() {
		super();
	}
	
	@Override
	public ComponentType getComponentType() {
		return ComponentType.GameInstructions;
	}
	
	public String getGameInstructions() {
		return myGameInstructions;
	}
	
	public void setGameInstructions(String instructions) {
		myGameInstructions = instructions;
	}
	
	public IComponent newCopy() {
		return new GameInstructionsComponent(getGameInstructions());
	}
}
