package view;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import components.LocationComponent;
import components.entityComponents.ComponentType;
import components.entityComponents.ImagePropertiesComponent;
import components.entityComponents.SpriteComponent;
import entity.Entity;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import view.commands.RightClickMenu;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
/**
 * @author Jonathan Rub
 * @author Justin Yang
 * @author Jack Bloomfeld
 */
public class GridView extends GUIComponent {
	//private ContextMenu rightClick;
	//private ContextMenu rightClickEntity;
	private RightClickMenu rightClick;
	
	
	private static final int CELL_SIZE = 8;
	private ScrollPane myScroll;
	private GridPane myGrid;
	private ViewData myData;
	private UtilityFactory util;
	private int j = 1000;
	private int myRow;
	private int myCol;
	private HashMap<Entity, ImageView> placedImages = new HashMap<Entity, ImageView>();
	private BorderPane bp;
	public GridView(UtilityFactory utilIn, ViewData data, int rows, int cols) {
		util = utilIn;
		myRow = rows;
		myCol = cols;
		myData = data;
		rightClick = new RightClickMenu(util, myData);
		myGrid = new GridPane();
		myGrid.getStyleClass().add("view-grid");
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				addMouseListenerPane(row, col);
			}
		}
		bp = new BorderPane();
		Button butt = util.buildButton("addHo", e -> addHo());
		util.buildButton("addHo", e -> addHo());
		Button butt2 = util.buildButton("addVert", e -> addVert());
		util.buildButton("addVert", e -> addVert());
		HBox box = new HBox(butt, butt2);
		bp.setTop(box);
		myScroll = new ScrollPane(myGrid);
		bp.setCenter(myScroll);
	}

	private void addHo() {
		for (int i = 0; i < myRow; i++) {
			addMouseListenerPane(myCol, i);
		}
		myCol++;
		myData.getLevelEntity().addCol();
	}

	private void addVert() {
		for (int i = 0; i < myCol; i++) {
			addMouseListenerPane(i, myRow);
		}
		myRow++;
		myData.getLevelEntity().addRow();
	}

	private void addMouseListenerPane(int row, int col) {
		Rectangle rect = new Rectangle(CELL_SIZE, CELL_SIZE);
		rect.getStyleClass().add("view-grid-cell");
		rect.setFill(Color.GREY);
		rect.setOnMousePressed(e -> {
			System.out.println(String.format("Click at row %d col %d", row,col));
			if(rightClick != null){
				rightClick.hide();
			}
			if(e.isSecondaryButtonDown()) {
				rightClick.show(myGrid, e.getScreenX(), e.getScreenY());
				//rightClick.show(myGrid, e.getScreenX(), e.getScreenY());
			}
			else{
				Entity userSelectedEntity = myData.getUserSelectedEntity();
				if (userSelectedEntity != null && userSelectedEntity.getComponent(ComponentType.Location) == null) {
					Entity placedEntity = userSelectedEntity.clone();
					placedEntity.setID(j);
					j++;
					placedEntity.addComponent(new LocationComponent(row, col));
					myData.placeEntity(placedEntity);
				}
			}
		});
		myGrid.add(rect, row, col);
	}

	public void drawEntity(Entity entity) {
		LocationComponent entityLocation = (LocationComponent) entity.getComponent(ComponentType.Location);
		SpriteComponent entitySprite = (SpriteComponent) entity.getComponent(ComponentType.Sprite);
		ImageView spriteImage = new ImageView(entitySprite.getSprite());
		ImagePropertiesComponent imageProperties = (ImagePropertiesComponent) entity.getComponent(ComponentType.ImageProperties);
		// Modify this part to make children span multiple rows/columns
		double height = imageProperties.getHeight();
		double width = imageProperties.getWidth();
		spriteImage.setFitHeight(height);
		spriteImage.setFitWidth(width);
		spriteImage.setOnMousePressed(e ->{
			selectEntity(entity);
			if(rightClick != null){
				rightClick.hide();
			}
			if(e.isSecondaryButtonDown()) {
				rightClick.show(myGrid, e.getScreenX(), e.getScreenY());
			}
		});
		placedImages.put(entity, spriteImage);
		myGrid.add(spriteImage, util.convertToInt(entityLocation.getX()), util.convertToInt(entityLocation.getY()));
		int colSpan = (int) height / CELL_SIZE + 1;
		int rowSpan = (int) width / CELL_SIZE + 1;
		GridPane.setColumnSpan(spriteImage, colSpan);
		GridPane.setRowSpan(spriteImage, rowSpan);
		GridPane.setHalignment(spriteImage, HPos.LEFT);
		GridPane.setValignment(spriteImage, VPos.TOP);
	}

	public void clearEntitiesOnGrid() {
		for(Entity e: placedImages.keySet()){
			System.out.println("removing" + e);
			myGrid.getChildren().remove(placedImages.get(e));
		}
		placedImages.clear();
	}

	public void placeEntitiesFromFile() {
		Entity tempEntity;
		HashMap<Integer, Entity> myMap = myData.getPlacedEntityMap();
		for(Integer i: myMap.keySet()) {
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

	public void updateBackground() {
		String filePath = myData.getLevelEntity().getBackgroundFilePath();
		myGrid.setStyle(String.format("-fx-background-image: url(%s);", filePath));
	}

	
/*	private ContextMenu buildContextMenu(){
		
		ContextMenu contextMenu = new ContextMenu();
		MenuItem paste = new MenuItem("Paste");
		paste.setAccelerator(new KeyCodeCombination(KeyCode.V, KeyCombination.SHORTCUT_DOWN));
		MenuItem redo = new MenuItem("Redo");
		MenuItem undo = new MenuItem("Undo");
		undo.setAccelerator(new KeyCodeCombination(KeyCode.Z, KeyCombination.SHORTCUT_DOWN));
		contextMenu.getItems().addAll(paste, redo, undo);
		paste.setOnAction(e -> {
			rightClick.hide();
		});
		redo.setOnAction(e -> {
			rightClick.hide();
		});
		undo.setOnAction(e -> {
			rightClick.hide();
		});
		return contextMenu;
	}

	private ContextMenu buildEntityContextMenu(Entity entity){
		ContextMenu contextMenu = new ContextMenu();
		MenuItem edit = new MenuItem("Edit");
		MenuItem cut = new MenuItem("Cut");
		cut.setAccelerator(new KeyCodeCombination(KeyCode.DELETE, KeyCombination.SHORTCUT_DOWN));
		MenuItem copy = new MenuItem("Copy");
		copy.setAccelerator(new KeyCodeCombination(KeyCode.C, KeyCombination.SHORTCUT_DOWN));
		MenuItem paste = new MenuItem("Paste");
		paste.setAccelerator(new KeyCodeCombination(KeyCode.V, KeyCombination.SHORTCUT_DOWN));
		MenuItem redo = new MenuItem("Redo");
		MenuItem undo = new MenuItem("Undo");
		undo.setAccelerator(new KeyCodeCombination(KeyCode.Z, KeyCombination.SHORTCUT_DOWN));
		contextMenu.getItems().addAll(edit, cut, copy, paste, redo, undo);
		edit.setOnAction(e -> {
			rightClick.hide();
		});
		cut.setOnAction(e -> {
			myData.unplaceEntity(myData.getUserSelectedEntity());
			myGrid.getChildren().remove(placedImages.get(myData.getUserSelectedEntity()));
			placedImages.remove(myData.getUserSelectedEntity());
			myData.setUserSelectedEntity(null);
			rightClick.hide();
		});
		copy.setOnAction(e -> {
			rightClick.hide();
		});
		paste.setOnAction(e -> {
			rightClick.hide();
		});
		redo.setOnAction(e -> {
			rightClick.hide();
		});
		undo.setOnAction(e -> {
			rightClick.hide();
		});
		return contextMenu;
	} */
	
	public void removeEntity(){
		myGrid.getChildren().remove(placedImages.get(myData.getUserSelectedEntity()));
		placedImages.remove(myData.getUserSelectedEntity());
	}
	
	public void unselectEntity(Entity entity){
		ImageView temp = placedImages.get(myData.getUserSelectedEntity());
		temp.setStyle("");
	}
	
	public void selectEntity(Entity entity){
		if(placedImages.containsKey(myData.getUserSelectedEntity())){
			unselectEntity(entity);
		}
		myData.setUserSelectedEntity(entity);
		ImageView i = placedImages.get(entity);
		i.setStyle("-fx-effect: innershadow(gaussian, #039ed3, 3, 1.0, 0, 0);");
	}

	@Override
	public Region buildComponent() {
		return bp;
	}
}


