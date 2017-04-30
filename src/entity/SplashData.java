package entity;

import components.entityComponents.GameInstructionsComponent;
import components.entityComponents.GameTitleComponent;
import components.entityComponents.SpriteComponent;

/**
 * @author Justin
 * @author Josh
 *
 */
public class SplashData{

	private int myID;
	private String displayName;
	private String instructions;
	private String backgroundFilePath;
	
	public SplashData(int id, String title, String inst, String imagePath) {
		myID=id;
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
