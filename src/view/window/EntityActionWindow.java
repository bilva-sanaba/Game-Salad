package view.window;

import java.util.HashMap;

import entity.Entity;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import view.ComponentFactory;
import view.GUIBuilder;
import view.UtilityFactory;
import view.ViewData;
import view.editor.ComponentEditor;

public class EntityActionWindow extends Window{
	private UtilityFactory myUtilF;
	private ViewData myData;
	private ComponentFactory myCompF;
	private BorderPane root;
	private Stage myStage = new Stage();
	private Entity myEntity;
	private HashMap<String, ComponentEditor> myCompEdits = new HashMap<String, ComponentEditor>();

	public EntityActionWindow(UtilityFactory utilF, ViewData entityData, Entity myE){
		myCompF = new ComponentFactory();
		myUtilF = utilF;
		myData = entityData;
		myEntity = myE;
		myData.setUserSelectedEntity(myEntity);
		myStage.setScene(buildScene());
	}

	private Scene buildScene() {
		root = new BorderPane();
		buildInfiniteGame();
		Scene myScene = new Scene(root, 350, 400);
		myScene.getStylesheets().add(GUIBuilder.RESOURCE_PACKAGE + GUIBuilder.STYLESHEET);
		return myScene;
	}

	private void buildInfiniteGame() {
		
	}

	@Override
	public void openWindow() {
		myStage.show();
	}

}
