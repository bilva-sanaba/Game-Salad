package view;

import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
/**
 * make a window interface
 * 
 * @author Justin
 * @author Jonathan
 */
public class EntityConfigurationWindow {
//	private static final String[] BLOCK_ENTITY = {"Label","Location","Sprite", "ImageProperties"};
//	private static final String[] CHARACTER_ENTITY = {"Label","Location","Sprite", "ImageProperties", "Velocity", "Health", "Accelaration"};
//	private static final String[] POWERUP_ENTITY = {"Label","Location","Sprite", "ImageProperties"};
	
	private UtilityFactory myUtilF;
	private ViewData myData;
	private Stage myStage;
	private String myEntityType;
	private String[] componentList;
	

	public EntityConfigurationWindow(UtilityFactory utilF, ViewData entityData, String[] entityType) {
		myUtilF = utilF;
		myData = entityData;
		myStage = new Stage();
		componentList = entityType;
	}
	
	public void show() {
		buildComponentEditor();
		myStage.show();
	}

	private void buildComponentEditor() {
		StackPane root = new StackPane(); 
		
	}
	
	
}
