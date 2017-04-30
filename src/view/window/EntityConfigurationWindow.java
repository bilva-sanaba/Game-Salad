package view.window;

import java.util.HashMap;
import java.util.Iterator;
import components.IComponent;
import components.entityComponents.ComponentType;
import entity.Entity;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
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
public class EntityConfigurationWindow implements Window {
	private UtilityFactory myUtilF;
	private ViewData myData;
	private String[] componentList;
	private ComponentFactory myCompF;
	private VBox root;
	private Stage myStage = new Stage();
	private Entity myEntity;
	private HashMap<String, ComponentEditor> myCompEdits = new HashMap<String, ComponentEditor>();

	public EntityConfigurationWindow(UtilityFactory utilF, ViewData entityData, String[] entityType, Entity entityIn) {
		initalizeVars(utilF, entityData, entityType, entityIn);
	}

	public EntityConfigurationWindow(UtilityFactory utilF, ViewData entityData, Entity e) {
		String[] empty = {};
		initalizeVars(utilF, entityData, empty, e);
		openWindow();
	}
	
	private void initalizeVars(UtilityFactory utilF, ViewData entityData, String[] entityType, Entity entityIn){
		myCompF = new ComponentFactory();
		myUtilF = utilF;
		myData = entityData;
		myEntity = entityIn;
		myData.setUserSelectedEntity(myEntity);
		componentList = entityType;
		myStage.setScene(buildScene());
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
			makeComponent(comp);
		}
		ObservableList<ComponentType> ObsCopms = FXCollections.observableArrayList(ComponentType.values());
		ListView<ComponentType> components = new ListView<ComponentType>(ObsCopms);
		root.getChildren().add(components);
		components.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ComponentType>() {
			@Override
			public void changed(ObservableValue<? extends ComponentType> observable, ComponentType oldVal,
					ComponentType newVal) {
				makeComponent(newVal.toString());
			}
		});
		root.getChildren().add(myUtilF.buildHBox(
				myUtilF.buildButton("AddActions", e -> addActions()),
				myUtilF.buildButton("BackToEntity", e -> enterButton())));
	}
	
	private void makeComponent(String comp) {
		try {
			ComponentEditor editor = myCompF.getComponentEditor(comp, myUtilF);
			System.out.println(comp + " line 102 "  + this.getClass());
			myCompEdits.put(comp, editor);
			root.getChildren().add(editor.getInputNode());
		} catch (Exception e) {
			System.out.println("Can not edit this component");
		}
	}

	private void enterButton() {
		for (ComponentEditor comp : myCompEdits.values()) {
			myEntity.addComponent(comp.getComponent());
		}
		myData.defineEntity(myEntity);
		myData.setUserSelectedEntity(myEntity);
		myStage.close();
	}
	
	private void addActions(){
		new EntityActionWindow(myUtilF, myData, myEntity);
	}

	@Override
	public void openWindow() {
		myStage.show();
	}
}
