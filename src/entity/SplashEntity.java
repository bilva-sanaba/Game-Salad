package entity;

import components.entityComponents.GameInstructionsComponent;
import components.entityComponents.GameTitleComponent;
import components.entityComponents.SpriteComponent;

/**
 * @author Justin
 * @author Josh
 *
 */
public class SplashEntity extends Entity {

	private GameTitleComponent gameTitle;
	private GameInstructionsComponent gameInstructions;
	private SpriteComponent sprite;
	
	public SplashEntity(int id, String title, String instructions, String imagePath) {
		super(id);
		gameTitle = new GameTitleComponent(title);
		gameInstructions = new GameInstructionsComponent(instructions);
		sprite = new SpriteComponent(imagePath);
		this.addComponent(gameTitle);
		this.addComponent(gameInstructions);
		this.addComponent(sprite);
	}
	
	public String getGameTitle() {
		return gameTitle.getGameTitle();
	}
	
	public String getInstructions() {
		return gameInstructions.getGameInstructions();
	}
	
	public String getSpriteImagePath() {
		return sprite.getClassPath();
	}
	
	public void setGameTitleComponent(String title) {
		gameTitle.setGameTitle(title);
	}
	
	public void setGameInstructionsComponent(String instructions) {
		gameInstructions.setGameInstructions(instructions);
	}
	
	public void setSpriteComponent(String imagePath) {
		sprite.setClassPath(imagePath);
	}
}
