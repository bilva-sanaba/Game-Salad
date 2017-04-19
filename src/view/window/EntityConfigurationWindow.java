package view.window;

import java.util.ArrayList;
import java.util.HashMap;

import components.IComponent;
import components.entityComponents.ComponentType;
import entity.Entity;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
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
	private HashMap<String, ComponentEditor> myCompEdits;
	private ObservableList<Entity> myList;

	public EntityConfigurationWindow(UtilityFactory utilF, ViewData entityData, String[] entityType,
			ObservableList<Entity> blocksList) {
		myCompF = new ComponentFactory();
		myUtilF = utilF;
		myData = entityData;
		myEntity = myData.getUserSelectedEntity();
		myData.setUserSelectedEntity(myEntity);
		componentList = entityType;
		myCompEdits = new HashMap<String, ComponentEditor>();
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
		myCompEdits = new HashMap<String, ComponentEditor>();
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
			myCompEdits.put(comp, editor);
			root.getChildren().add(editor.getInputNode());
		}
		ObservableList<ComponentType> ObsCopms = FXCollections.observableArrayList(ComponentType.values());
		ListView<ComponentType> components = new ListView<ComponentType>(ObsCopms);
		root.getChildren().add(components);
		components.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ComponentType>() {
			@Override
			public void changed(ObservableValue<? extends ComponentType> observable, ComponentType oldVal,
					ComponentType newVal) {
				try {
					ComponentEditor editor = myCompF.getComponentEditor(newVal.toString(), myUtilF);
					myCompEdits.put(newVal.toString(), editor);
					root.getChildren().add(editor.getInputNode());
				} catch (Exception e) {
					System.out.println("Can not edit this component");
				}
			}
		});
		root.getChildren().add(myUtilF.buildButton("MakeEntity", e -> enterButton()));
	}

	private void enterButton() {
		for (ComponentEditor comp : myCompEdits.values()) {
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
