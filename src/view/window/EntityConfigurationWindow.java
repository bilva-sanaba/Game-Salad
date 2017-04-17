package view.window;

import java.util.ArrayList;

import entity.Entity;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.ComponentFactory;
import view.GUIBuilder;
import view.UtilityFactory;
import view.ViewData;
import view.editor.ComponentEditor;

/**
 * make a window interface
 * 
 * @author Justin
 * @author Jonathan
 * @author Jack
 */
public class EntityConfigurationWindow implements IWindow {

	private UtilityFactory myUtilF;
	private ViewData myData;
	private String myEntityType;
	private String[] componentList;
	private ComponentFactory myCompF;
	private VBox root;
	private Stage myStage = new Stage();
	private Entity myEntity;
	private ArrayList<ComponentEditor> myCompEdits;
	private ObservableList<Entity> myList;

	public EntityConfigurationWindow(UtilityFactory utilF, ViewData entityData, String[] entityType, ObservableList<Entity> blocksList) {
		myCompF = new ComponentFactory();
		myUtilF = utilF;
		myData = entityData;
		myEntity = myData.getUserSelectedEntity();
		myData.setUserSelectedEntity(myEntity);
		componentList = entityType;
		myCompEdits = new ArrayList<ComponentEditor>();
		myList = blocksList;
		myStage.setScene(buildScene());
	}
	
	public EntityConfigurationWindow(UtilityFactory utilF, ViewData entityData, String[] entityType, ObservableList<Entity> blocksList, Entity e) {
		myCompF = new ComponentFactory();
		myUtilF = utilF;
		myData = entityData;
		myEntity = myData.getUserSelectedEntity();
		myData.setUserSelectedEntity(myEntity);
		componentList = entityType;
		myCompEdits = new ArrayList<ComponentEditor>();
		myList = blocksList;
		myStage.setScene(buildScene());
	}

	public void show() {
		myStage.show();
	}

	private Scene buildScene() {
		root = new VBox();
		buildComponentEditor();
		Scene myScene = new Scene(root, 350, 400);
		myScene.getStylesheets().add(GUIBuilder.RESOURCE_PACKAGE + GUIBuilder.STYLESHEET);
		return myScene;
	}

	private void buildComponentEditor() {
		for (String comp : componentList) {
			ComponentEditor editor = myCompF.getComponentEditor(comp, myUtilF);
			myCompEdits.add(editor);
			root.getChildren().add(editor.getInputNode());
		}
		root.getChildren().add(myUtilF.buildButton("MakeEntity", e -> enterButton()));
	}

	private void enterButton() {
		for (ComponentEditor comp : myCompEdits) {
			myEntity.addComponent(comp.getComponent());
		}
		myList.add(myEntity);
		myStage.close();
	}

	@Override
	public void openWindow() {
		myStage.show();
	}
}


