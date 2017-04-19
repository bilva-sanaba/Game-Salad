package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import components.entityComponents.ComponentType;
import components.entityComponents.ImagePropertiesComponent;
import components.entityComponents.SpriteComponent;
import components.movementcomponents.LocationComponent;
import entity.Entity;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * @author Jonathan Rub
 * @author Justin Yang
 * @author Jack Bloomfeld
 */
public class GridView extends GUIComponent{ 
//implements Observer {
	private ScrollPane myScroll;
	private double Initial_X = 500.0;
	private double Initial_Y = 500.0;
	private Canvas myGrid = new Canvas(Initial_X,Initial_Y);
    private GraphicsContext gc = myGrid.getGraphicsContext2D();
	private ViewData myData;
	private UtilityFactory util;
	private int i = 1000;
	private int myRow;
	private int myCol;
	private ArrayList<ImageView> placedImages = new ArrayList<ImageView>();
	private ArrayList<Rectangle> placedGrids = new ArrayList<Rectangle>();
	private BorderPane bp;

	public GridView(UtilityFactory utilIn, ViewData data, int rows, int cols) {
		util = utilIn;
		myRow = rows;
		myCol = cols;
		myData = data;
		myGrid.setCursor(Cursor.CROSSHAIR);
//		myData.addObserver(this);
		bp = new BorderPane();
		Button butt = util.buildButton("addHo", e-> addHo());
		util.buildButton("addHo", e->addHo());
		Button butt2 = util.buildButton("addVert", e -> addVert());
		util.buildButton("addVert", e -> addVert());
		HBox box = new HBox(butt,butt2);
		bp.setTop(box);
		myScroll = new ScrollPane(myGrid);
	//	myScroll.getStyleClass().add("myGrid");
		bp.setCenter(myScroll);
		myGrid.setOnMouseClicked(e -> {
			int row = (int) (Math.round(e.getX()/10));
			int col = (int) (Math.round(e.getY()/10));
			System.out.println(Math.round(e.getX()/10)*10 + "   " + Math.round(e.getY()/10)*10);
			System.out.println(Math.round(e.getX()/10) + "   " + Math.round(e.getY()/10));
			Entity userSelectedEntity = myData.getUserSelectedEntity();
			if (userSelectedEntity != null) {
				Entity placedEntity = userSelectedEntity.clone();
				placedEntity.setID(i);
				i++;
				placedEntity.addComponent(new LocationComponent(row*10, col*10));
				myData.placeEntity(placedEntity);
				drawEntity(placedEntity);
			}
		});
	}

	private void addVert() {
		myGrid.setHeight(Initial_Y+=10);
		myData.getLevelEntity().addCol();
	}
		
	private void addHo() {
		myGrid.setWidth(Initial_X+=10);
		myData.getLevelEntity().addRow();
	}

	public void drawEntity(Entity entity) {
		LocationComponent entityLocation = (LocationComponent) entity.getComponent(ComponentType.Location);
		SpriteComponent entitySprite = (SpriteComponent) entity.getComponent(ComponentType.Sprite);
		ImageView spriteImage = new ImageView(entitySprite.getSprite());
		double h =0;
		double w =0; 
		if(entity.getComponent(ComponentType.ImageProperties) != null){
			ImagePropertiesComponent imageProp = (ImagePropertiesComponent) entity.getComponent(ComponentType.ImageProperties);
			System.out.println(imageProp.getHeight());
			System.out.println(imageProp.getWidth());
			h=imageProp.getHeight();
			w=imageProp.getWidth();
			spriteImage.setFitHeight(imageProp.getHeight());
			spriteImage.setFitWidth(imageProp.getWidth());
		}
		else{
			spriteImage.setFitHeight(50);
			spriteImage.setFitWidth(50);
		}
		placedImages.add(spriteImage);
		gc.drawImage(spriteImage.imageProperty().get(), entityLocation.getX(), entityLocation.getY(), w, h);
	}
	
	public void clearEntitiesOnGrid(){
		gc.clearRect(0, 0, Initial_X, Initial_Y);
		placedImages.clear();
	}
	
	public void placeEntitiesFromFile(){
		Entity tempEntity;
		HashMap<Integer, Entity> myMap = myData.getPlacedEntityMap();
		for(Integer i: myMap.keySet()){
			System.out.println("THINGS ARE PLACED");
			tempEntity = myMap.get(i);
			drawEntity(tempEntity);
		}
	}
	
	public void setUpLevel() {
		System.out.println("NEW METHOD HAPPENS");
		int totalRow = myData.getLevelEntity().getRows();
		int totalCol = myData.getLevelEntity().getCols();
		
		while (myCol != totalCol) {
			System.out.println("HORIZONTAL IS DONE");
			addHo();
		}
		while (myRow != totalRow) {
			System.out.println("VERTICAL IS DONE");
			addVert();
		}
	}

	@Override
	public Region buildComponent() {
		return bp;
	}
	
	public void updateBackground() {
		String filePath = myData.getLevelEntity().getBackgroundFilePath();
		myGrid.setStyle(String.format("-fx-background-image: url(%s);", filePath));
	}

/*	@Override
	public void update(Observable arg0, Object arg1) {
		updateBackground();
		setUpLevel();
		clearEntitiesOnGrid();
		placeEntitiesFromFile();
	}*/
}