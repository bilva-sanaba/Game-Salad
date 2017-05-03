package view.window;

import components.entityComponents.SpriteComponent;
import entity.Entity;
import entity.presets.AbstractBlock;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.GUIBuilder;
import view.ImageChooser;
import view.UtilityFactory;
import view.ViewData;
import voogasalad.util.reflection.Reflection;

public class EntityBuilderWindow implements Window {

	private final Image defaultImage = new Image(getClass().getClassLoader().getResourceAsStream("empty.jpg"));
	private ImageView myImage;
	private String myImageName = "";
	private Entity myEntity;
	private ImageChooser imageChooser = new ImageChooser();
	private UtilityFactory util;
	private ViewData myData;
	private Stage myStage = new Stage();
	private String[] entityList = {"Error"};

	public EntityBuilderWindow(UtilityFactory utilIn, ObservableList<Entity> blocksListIn, ViewData dataIn) {
		myData = dataIn;
		util = utilIn;
	}

	public void showEntityBuilder() {
		myImage = new ImageView(defaultImage);
		myStage.setScene(buildScene());
		myStage.show();
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

	private void addImageButton(Pane root){
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
		root.getChildren().add(entityType);

		final ToggleGroup group = util.buildRadioButtonGroup("SelectEntityType", root);
		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
				entityList = (String[]) new_toggle.getUserData();
			}
		});
	}
	
	private void addOkayButton(Pane root) {
		Node okayButton = util.buildButton("OkayLabel", e -> {
			AbstractBlock newBlock = new AbstractBlock(1);
			System.out.println(newBlock.getClass() + " -> " + entityList[0]);
			Entity tempEntity = (Entity) Reflection.createInstance(entityList[0], myData.getDefinedEntityID());
			System.out.println(myImageName + " line 98 " + this.getClass());
			tempEntity.addComponent(new SpriteComponent(myImageName));
			myStage.close();
			EntityConfigurationWindow ecw = new EntityConfigurationWindow(util, myData, tempEntity);
			ecw.openWindow();
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
		myStage.show();
	}

}