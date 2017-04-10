package view;

import java.util.ArrayList;

import components.SpriteComponent;
import entity.Entity;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class EntityBuilderWindow {

	private ObservableList<Entity> blocksList;
	private ArrayList<Node> nodeList = new ArrayList<Node>();
	private Image myImageImage = new Image(getClass().getClassLoader()
			.getResourceAsStream("empty.jpg"));
	private ImageView myImage = new ImageView(myImageImage);
	private String myImagePath = "";
	private Entity myEntity;
	private ImageChooser imageChooser = new ImageChooser();
	private UtilityFactory util;
	private Stage myStage = new Stage();
	private ViewData myData;
	private int i = 0;
	

	private static final String[] BLOCK_ENTITY = {"Label", "ImageProperties"};//,"Location","Sprite"};
//	private static final String[] CHARACTER_ENTITY = {"Label","Location","Sprite", "ImageProperties", "Velocity", "Health", "Accele.ration"};
//	private static final String[] POWERUP_ENTITY = {"Label","Location","Sprite", "ImageProperties"};

	public EntityBuilderWindow(UtilityFactory utilIn,
			ObservableList<Entity> blocksListIn, ViewData dataIn) {
		myData = dataIn;
		blocksList = blocksListIn;
		util = utilIn;
		nodeList.add(myImage);
		GridPane.setConstraints(myImage, 0, 0);
		GridPane.setColumnSpan(myImage, 3);
		myStage.setScene(buildScene());
		EntityConfigurationWindow ebw = new EntityConfigurationWindow(utilIn, dataIn, BLOCK_ENTITY);
		ebw.show();
	}

	public void showEntityBuilder() {
		myStage.show();
	}

	private Scene buildScene() {
		buildNodes();
		GridPane pane = buildPane();
		return new Scene(pane, 300, 350);
	}

	public ImageView getImage() {
		return myImage;
	}

	public Entity getEntity() {
		return myEntity;
	}

	private void buildNodes() {
		Node imageButton = util.buildButton("ChooseImageLabel", e -> {
			myImagePath = imageChooser.chooseFile();
			Image image = new Image(myImagePath);
			myImage.setImage(image);
			myImage.setFitWidth(200);
			myImage.setFitHeight(200);
		});
		nodeList.add(imageButton);
		GridPane.setConstraints(imageButton, 0, 1);

		Node okayButton = util.buildButton("OkayLabel", e -> {
			Entity tempEntity = new Entity(i);
			i++;
			tempEntity.addComponent(new SpriteComponent(myImagePath));
			blocksList.add(tempEntity);
			myData.defineEntity(tempEntity);
			myStage.close();
		});
		nodeList.add(okayButton);
		GridPane.setConstraints(okayButton, 0, 4);

		Node entityType = new Label("King of Entity");
		nodeList.add(entityType);
		GridPane.setConstraints(entityType, 0, 2);

		final ToggleGroup group = new ToggleGroup();

		RadioButton rb1 = new RadioButton("Block");
		rb1.setToggleGroup(group);
		rb1.setSelected(true);
		GridPane.setConstraints(rb1, 0, 3);
		nodeList.add(rb1);

		RadioButton rb2 = new RadioButton("Character");
		rb2.setToggleGroup(group);
		GridPane.setConstraints(rb2, 1, 3);
		nodeList.add(rb2);

		RadioButton rb3 = new RadioButton("Item");
		rb3.setToggleGroup(group);
		GridPane.setConstraints(rb3, 2, 3);
		nodeList.add(rb3);
		
	}

	private GridPane buildPane() {
		GridPane pane = new GridPane();
		pane.getChildren().addAll(nodeList);
		return pane;
	}

	private ToggleGroup addRadialButtons(){
		ToggleGroup group = new ToggleGroup();

		RadioButton rb1 = new RadioButton("Block");
		rb1.setToggleGroup(group);
		rb1.setSelected(true);
		GridPane.setConstraints(rb1, 0, 3);

		RadioButton rb2 = new RadioButton("Character");
		rb2.setToggleGroup(group);
		GridPane.setConstraints(rb2, 1, 3);

		RadioButton rb3 = new RadioButton("Item");
		rb3.setToggleGroup(group);
		GridPane.setConstraints(rb3, 2, 3);
		
		return group;
	}
}