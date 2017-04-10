package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import components.ComponentType;
import components.LocationComponent;
import components.SpriteComponent;
import entity.Entity;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

/**
 * @author Justin Yang
 * @author Jack Bloomfeld
 */
public class GridView extends GUIComponent implements Observer{
	private GridPane myGrid;
	private ViewData myData;
	private int i = 1000;
	private ArrayList<ImageView> placedImages = new ArrayList<ImageView>();

	public GridView(UtilityFactory utilF, ViewData data, int rows, int cols) {
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
		myGrid.add(spriteImage, (int) entityLocation.getX(), (int) entityLocation.getY());
	}
	
	private void clearEntitiesOnGrid(){
		for(ImageView i: placedImages){
			myGrid.getChildren().remove(i);
		}
		placedImages.clear();
	}
	
	private void placeEntitiesFromFile(){
		Entity tempEntity;
		HashMap<Integer, Entity> myMap = myData.getPlacedEntityMap();
		for(Integer i: myMap.keySet()){
			tempEntity = myMap.get(i);
			drawEntity(tempEntity);
		}
	}

	@Override
	public Region buildComponent() {
		return myGrid;
	}

	@Override
	public void update(Observable o, Object arg) {
		clearEntitiesOnGrid();
		placeEntitiesFromFile();
	}
}