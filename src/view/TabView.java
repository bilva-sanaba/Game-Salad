package view;

import java.util.HashMap;

import components.entityComponents.ComponentType;
import components.entityComponents.ImagePropertiesComponent;
import components.entityComponents.SpriteComponent;
import entity.Entity;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import view.window.EntityBuilderWindow;

public class TabView extends GUIComponent {
	private ObservableList<Entity> blocksList = FXCollections.observableArrayList();
	private ListView<Entity> blocksView = new ListView<Entity>();
	private VBox myBox = new VBox();
	private Button b;
	private Button u;
	private UtilityFactory util;
	private ViewData myData;
	private EntityBuilderWindow entityBuilder;

	public TabView(UtilityFactory utilIn, ViewData data) {
		/*
		 * Entity entity = new Entity(7); SpriteComponent entitySprite =
		 * (SpriteComponent) entity.getComponent(ComponentType.Sprite);
		 * ImageView spriteImage = new ImageView(entitySprite.getSprite());
		 */
		myData = data;
		util = utilIn;
		entityBuilder = new EntityBuilderWindow(util, blocksList, myData);
		blocksView.setId("blocksview");
		blocksView.setItems(blocksList);
		blocksView.setCellFactory(e -> new ListCell<Entity>() {
			@Override
			protected void updateItem(Entity item, boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null || item.getComponent(ComponentType.Sprite) == null) {
					this.setGraphic(null);
				} else {
					SpriteComponent entitySprite = (SpriteComponent) item.getComponent(ComponentType.Sprite);
					ImageView spriteImage = new ImageView(entitySprite.getSprite());
					if (item.getComponent(ComponentType.ImageProperties) != null) {
						ImagePropertiesComponent imageProp = (ImagePropertiesComponent) item
								.getComponent(ComponentType.ImageProperties);
						spriteImage.setFitHeight(imageProp.getHeight());
						spriteImage.setFitWidth(imageProp.getWidth());
					} else {
						spriteImage.setFitHeight(40);
						spriteImage.setFitWidth(40);
					}
					this.setGraphic(spriteImage);
				}
			}
		});
		blocksView.setOrientation(Orientation.VERTICAL);
		blocksView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Entity>() {
			@Override
			public void changed(ObservableValue<? extends Entity> observable, Entity oldVal, Entity newVal) {
				myData.setUserSelectedEntity(newVal);
				System.out.println("asd");
			}
		});
		b = util.buildButton("AddEntityButton", e -> {
			entityBuilder.showEntityBuilder();
		});
		u = util.buildButton("UploadEntities", e -> {
			System.out.println("upload f***");
		});
		util.setPresets(blocksList);
	}

	public void clearEntitiesOnTab() {
		blocksList.clear();
	}

	public void addEntity(Entity e) {
		blocksList.add(e);
	}

	@Override
	public Region buildComponent() {
		myBox.getChildren().add(blocksView);
		myBox.getChildren().add(b);
		myBox.getChildren().add(u);
		return myBox;
	}

}