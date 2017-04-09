package view;

import components.ComponentType;
import components.SpriteComponent;
import entity.Entity;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

public class TabView extends GUIComponent{
	private ObservableList<Entity> blocksList = FXCollections.observableArrayList();
	private ListView<Entity> blocksView = new ListView<Entity>();
	private GridPane pane = new GridPane();
	private TabPane myTab = new TabPane();
	private Button b;
	private UtilityFactory util;
	private ViewData myData;
	private EntityBuilderWindow entityBuilder;

	public TabView(UtilityFactory utilIn, ViewData data){
	/*	Entity entity = new Entity(7);
		SpriteComponent entitySprite = (SpriteComponent) entity.getComponent(ComponentType.Sprite);
		ImageView spriteImage = new ImageView(entitySprite.getSprite()); */
		myData = data;
		util = utilIn;
		entityBuilder = new EntityBuilderWindow(util, blocksList, myData);
		blocksView.setItems(blocksList);
		blocksView.setOrientation(Orientation.VERTICAL);
		blocksView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Entity>() {
			@Override
			public void changed(ObservableValue<? extends Entity> observable, Entity oldVal, Entity newVal) {                                                                           
				myData.setUserSelectedEntity(newVal);
				System.out.println("asd");
			}
		});
		Tab blockTab = util.buildTab("BlockTabLabel", false);
		blockTab.setContent(blocksView);
		b = util.buildButton("AddEntityButton", e->
		{
			entityBuilder.showEntityBuilder();
		});
		myTab.getTabs().add(blockTab);
	}

	@Override
	public Region buildComponent(){
		pane.getChildren().add(myTab);
		GridPane.setConstraints(myTab, 0, 0);
		pane.getChildren().add(b);
		GridPane.setConstraints(b, 0, 1);
		Region myRegion = pane;
		GridPane.setConstraints(pane, 0, 1);
		return myRegion;

	}

}