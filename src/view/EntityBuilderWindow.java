package view;

import java.util.ArrayList;

import components.LocationComponent;
import components.SpriteComponent;
import entity.Entity;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class EntityBuilderWindow {

	private ObservableList<Entity> blocksList;
	private ArrayList<Node> nodeList = new ArrayList<Node>();
	private Image myImageImage = new Image(getClass().getClassLoader().getResourceAsStream("empty.jpg"));
	private ImageView myImage = new ImageView(myImageImage);
	private String myImagePath = "";
	private Entity myEntity;
	private ImageChooser imageChooser = new ImageChooser();
	private UtilityFactory util;
	private Stage myStage = new Stage();
	private ViewData myData;
	private int i = 0;

	public EntityBuilderWindow(UtilityFactory utilIn, ObservableList<Entity> blocksListIn, ViewData dataIn){
		myData = dataIn;
		blocksList = blocksListIn;
		util = utilIn;
		nodeList.add(myImage);
		GridPane.setConstraints(myImage, 0, 0);
		myStage.setScene(buildScene());
	}

	public void showEntityBuilder(){
		myStage.show();
	}

	private Scene buildScene(){
		buildNodes();
		GridPane pane = buildPane();
		return new Scene(pane);
	}

	public ImageView getImage(){
		return myImage;
	}

	public Entity getEntity(){
		return myEntity;
	}

	private void buildNodes(){
		Node imageButton = util.buildButton("ChooseImageLabel", e->{
			myImagePath = imageChooser.chooseFile();
			Image image = new Image(myImagePath);
			myImage.setImage(image);
			myImage.setFitWidth(50);
			myImage.setFitHeight(50);
		});
		nodeList.add(imageButton);
		GridPane.setConstraints(imageButton, 0, 1);

		Node okayButton = util.buildButton("OkayLabel", e->{
			Entity tempEntity = new Entity(i);
			i++;
			tempEntity.addComponent(new SpriteComponent(myImagePath));
			blocksList.add(tempEntity);
			myData.defineEntity(tempEntity);
			myStage.close();
		});
		nodeList.add(okayButton);
		GridPane.setConstraints(okayButton, 0, 2);
	}

	private GridPane buildPane(){
		GridPane pane = new GridPane();
		pane.getChildren().addAll(nodeList);
		return pane;

	}

}