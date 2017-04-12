package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import components.entityComponents.ComponentType;
import components.entityComponents.LocationComponent;
import components.entityComponents.SpriteComponent;
import entity.Entity;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
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

/**
 * @author Jonathan Rub
 * @author Justin Yang
 * @author Jack Bloomfeld
 */
public class GridView extends GUIComponent implements Observer{
	private ScrollPane myScroll;
	private GridPane myGrid;
	private ViewData myData;
	private UtilityFactory util;
	private int i = 1000;
	private int myRow;
	private int myCol;
	private ArrayList<ImageView> placedImages = new ArrayList<ImageView>();
	private BorderPane bp;

	public GridView(UtilityFactory utilIn, ViewData data, int rows, int cols) {
		util = utilIn;
		myRow = rows;
		myCol = cols;
		myData = data;
		myGrid = new GridPane();
		myGrid.getStyleClass().add("view-grid");
		myGrid.setAlignment(Pos.CENTER);
		myData.addObserver(this);
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				addMouseListenerPane(row, col);
			}
		}
		bp = new BorderPane();
		Button butt = util.buildButton("addHo", e-> addHo());
		util.buildButton("addHo", e->addHo());
		Button butt2 = util.buildButton("addVert", e -> addVert());
		util.buildButton("addVert", e -> addVert());
		HBox box = new HBox(butt,butt2);
		bp.setTop(box);
		
		myScroll = new ScrollPane(myGrid);
		bp.setCenter(myScroll);
	}

	private void addHo() {
		for (i=0;i<myRow;i++){
			Rectangle rect = new Rectangle(40, 40);
			rect.getStyleClass().add("view-grid-cell");
			
			myGrid.add(rect, myCol, i);
			addMouseListenerPane(myCol, i);
		}
		myCol++;
		myData.getLevelEntity().addCol();
	}
		
	private void addVert() {
		for (i=0;i<myCol;i++){
			Rectangle rect = new Rectangle(40, 40);
			rect.getStyleClass().add("view-grid-cell");
			
			myGrid.add(rect, i, myRow);
			addMouseListenerPane(i, myRow);
		}
		myRow++;
		myData.getLevelEntity().addRow();
	}

	private void addMouseListenerPane(int row, int col) {
		Rectangle rect = new Rectangle(40, 40);
		rect.getStyleClass().add("view-grid-cell");
		rect.setFill(Color.GREY);
		rect.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				System.out.println(String.format("Click at row %d col %d", row,
						col));
				Entity userSelectedEntity = myData.getUserSelectedEntity();
				if (userSelectedEntity != null) {
					Entity placedEntity = userSelectedEntity.clone();
					placedEntity.setID(i);
					i++;
					myData.placeEntity(placedEntity);
					myData.setEntityLocation(placedEntity.getID(), row, col);
					drawEntity(placedEntity);
				}
			}
		});
		myGrid.add(rect, row, col);
	}

	private void drawEntity(Entity entity) {
		LocationComponent entityLocation = (LocationComponent) entity
				.getComponent(ComponentType.Location);
		SpriteComponent entitySprite = (SpriteComponent) entity
				.getComponent(ComponentType.Sprite);
		ImageView spriteImage = new ImageView(entitySprite.getSprite());
		spriteImage.setFitHeight(40);
		spriteImage.setFitWidth(40);
		placedImages.add(spriteImage);
		myGrid.add(spriteImage, util.convertToInt(entityLocation.getX()), util.convertToInt(entityLocation.getY()));
	}
	
	public void clearEntitiesOnGrid(){
		for(ImageView i: placedImages){
			myGrid.getChildren().remove(i);
		}
		placedImages.clear();
	}
	
	public void placeEntitiesFromFile(){
		Entity tempEntity;
		HashMap<Integer, Entity> myMap = myData.getPlacedEntityMap();
		for(Integer i: myMap.keySet()){
			tempEntity = myMap.get(i);
			drawEntity(tempEntity);
		}
	}
	
	public void setUpLevel() {
		int totalRow = myData.getLevelEntity().getRows();
		int totalCol = myData.getLevelEntity().getCols();
		
		
		while (myCol != totalCol) {
			addHo();
		}
		while (myRow != totalRow) {
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

	@Override
	public void update(Observable arg0, Object arg1) {
		updateBackground();
		setUpLevel();
		clearEntitiesOnGrid();
		placeEntitiesFromFile();
	}
}