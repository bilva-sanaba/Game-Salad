package view.window;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import components.entityComponents.SpriteComponent;
import entity.Entity;
import entity.presets.PresetEntites;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.AuthoringException;
import view.GUIBuilder;
import view.ImageChooser;
import view.UtilityFactory;
import view.ViewData;
import voogasalad.util.reflection.Reflection;

public class EntityMakerWindow implements Window {

	private final Image defaultImage = new Image(getClass().getClassLoader().getResourceAsStream("empty.jpg"));
	private ImageView myImage;
	private String myImageName = "";
	private Entity myEntity;
	private ImageChooser imageChooser = new ImageChooser();
	private UtilityFactory util;
	private Stage myStage = new Stage();
	private String entityList;
	private EntityChangerWindow ecw = null;

	public EntityMakerWindow(UtilityFactory utilIn) {
		util = utilIn;
	}

	public void showEntityBuilder() {
		myImage = new ImageView(defaultImage);
		myStage.setScene(buildScene());
		openWindow();
	}

	private Scene buildScene() {
		Pane pane = buildPane();
		Scene myScene = new Scene(pane);
		myScene.getStylesheets().add(GUIBuilder.RESOURCE_PACKAGE + GUIBuilder.STYLESHEET);
		return myScene;
	}

	public ImageView getImage() {
		return myImage;
	}

	public Entity getEntity() {
		return myEntity;
	}

	private void addImageButton(Pane root) {
		Node imageButton = util.buildButton("ChooseImageLabel", e -> {
			myImageName = imageChooser.chooseFile();
			Image image = new Image(getClass().getClassLoader().getResourceAsStream(myImageName));
			myImage.setImage(image);
			myImage.setFitWidth(200);
			myImage.setFitHeight(200);
		});
		root.getChildren().add(imageButton);
	}

	private void addRadioButtons(Pane root) {
		Node entityType = new Label("Kind of Entity:");
		Map<String, PresetEntites> stringFromPreset = new HashMap<String, PresetEntites>();
		for (int i = 0; i < PresetEntites.values().length; i++) {
			PresetEntites st = PresetEntites.values()[i];
			System.out.println(util.getText(st.toString()));
			stringFromPreset.put(util.getText(st.toString()), st);
		}
		ListView<String> presetEnt = util.buildListView(new ArrayList(stringFromPreset.keySet()));
		presetEnt.setMaxHeight(250);
		root.getChildren().add(presetEnt);
		presetEnt.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldVal, String newVal) {
				entityList = stringFromPreset.get(newVal).toString();
			}
		});

	}

	private void addOkayButton(Pane root) {
		Node okayButton = util.buildButton("OkayLabel", e -> {
			if (myImageName.equals("")) {
				throw new AuthoringException("NO_IMAGE");
			}
			myEntity = (Entity) Reflection.createInstance("entity.presets." + entityList, 0);
			myEntity.addComponent(new SpriteComponent(myImageName));
			ecw = new EntityChangerWindow(util, myEntity);
			ecw.openWindow();
			myStage.close();
		});
		root.getChildren().add(okayButton);
	}

	private Pane buildPane() {
		VBox pane = new VBox();
		pane.getChildren().add(myImage);
		addImageButton(pane);
		addRadioButtons(pane);
		addOkayButton(pane);
		return pane;
	}

	@Override
	public void openWindow() {
		myStage.showAndWait();
	}

}