package view.window;

import java.util.HashMap;

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
import view.editor.EditableComponents;

public class EntityChangerWindow implements Window {
	private UtilityFactory myUtilF;
	private ComponentFactory myCompF;
	private VBox root;
	private Stage myStage = new Stage();
	private Entity myEntity;
	private HashMap<String, ComponentEditor> myCompEdits = new HashMap<String, ComponentEditor>();

	public EntityChangerWindow(UtilityFactory utilF, Entity entityIn) {
		myCompF = new ComponentFactory();
		myUtilF = utilF;
		myEntity = entityIn;
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
		ObservableList<EditableComponents> ObsCopms = FXCollections.observableArrayList(EditableComponents.values());
		ListView<EditableComponents> components = new ListView<EditableComponents>(ObsCopms);
		root.getChildren().add(components);
		components.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<EditableComponents>() {
			@Override
			public void changed(ObservableValue<? extends EditableComponents> observable, EditableComponents oldVal,
					EditableComponents newVal) {
				makeComponent(newVal.toString());
			}
		});
		root.getChildren().add(myUtilF.buildButton("AddEntity", e -> enterButton()));
	}

	private void makeComponent(String comp) {
		try {
			ComponentEditor editor = myCompF.getComponentEditor(comp, myUtilF);
			System.out.println(comp + " line 102 " + this.getClass());
			myCompEdits.put(comp, editor);
			root.getChildren().add(editor.getInputNode());
		} catch (Exception e) {
			System.out.println("Can not edit this component");
		}
	}

	private void compileEntity() {
		for (ComponentEditor comp : myCompEdits.values()) {
			myEntity.addComponent(comp.getComponent());
		}
	}

	private void enterButton() {
		compileEntity();
		myStage.close();
	}

	@Override
	public void openWindow() {
		myStage.showAndWait();
	}
}
