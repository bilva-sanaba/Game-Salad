package view;

import entity.Entity;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
/**
 * make a window interface
 * 
 * @author Justin
 * @author Jonathan
 */
public class EntityConfigurationWindow {
	
	private UtilityFactory myUtilF;
	private ViewData myData;
	private Stage myStage;
	private String myEntityType;
	private String[] componentList;
	private ComponentFactory myCompF;
	private VBox root;
	private Entity myEntity;
	
	
	public EntityConfigurationWindow(UtilityFactory utilF, ViewData entityData, String[] entityType) {
		myCompF = new ComponentFactory();
		myUtilF = utilF;
		myData = entityData;
		myEntity = new Entity(123);//myData.getUserSelectedEntity();
		myStage = new Stage();
		componentList = entityType;
		myStage.setScene(buildScene());
	}
	
	public void show() {
		myStage.show();
	}
	
	private Scene buildScene() {
		root = new VBox();
		buildComponentEditor();
		return new Scene(root);
	}

	private void buildComponentEditor() {
		for(String comp: componentList){
			ComponentEditor editor = myCompF.getComponentEditor(comp);
			System.out.println(editor + " got editor");
			root.getChildren().add(editor.getInputNode());
			myEntity.addComponent(myCompF.getComponent(comp));
		}
	}
}
