package entity;

import components.entityComponents.GameInstructionsComponent;
import components.entityComponents.GameTitleComponent;
import components.entityComponents.SpriteComponent;

/**
 * @author Justin
 * @author Josh
 *
 */
public class SplashData extends Entity{

	private String displayName;
	private String instructions;
	private String backgroundFilePath;
	
	public SplashData(int id, String title, String inst, String imagePath) {
		super(id);
		displayName = title;
		instructions = inst;
		backgroundFilePath = imagePath;
	}
	
	public String getGameTitle() {
		return displayName;
	}
	
	public String getInstructions() {
		return instructions;
	}
	
	public String getBackgroundFilePath() {
		return backgroundFilePath;
	}
}
