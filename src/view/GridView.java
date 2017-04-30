package view;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import components.IComponent;
import components.entityComponents.ComponentType;
import components.entityComponents.ImagePropertiesComponent;
import components.entityComponents.LocationComponent;
import components.entityComponents.SpriteComponent;
import entity.Entity;
import javafx.event.EventType;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import view.commands.RightClickMenu;
import view.window.EntityConfigurationWindow;

/**
 * @author Jonathan Rub
 * @author Justin Yang
 * @author Jack Bloomfeld
 */
public class GridView extends GUIComponent {
	private RightClickMenu rightClick;
	private ScrollPane myScroll;
	private Pane myGrid;
	private ViewData myData;
	private UtilityFactory util;
	private int j = 1000;
	private Label mouseCords;
	private int myRow;
	private int myCol;
	private int myLevelNumber;
	double orgSceneX, orgSceneY;
	double orgTranslateX, orgTranslateY;
	private HashMap<Entity, ImageView> placedImages = new HashMap<Entity, ImageView>();
	private BorderPane myBorderPane;

	// TODO: change placedentitymap to a map from levelnumber to placedentitymap
	// Refactor gridview and make it nonshitty

	public GridView(UtilityFactory utilIn, int levelNumber, ViewData data, int rows, int cols) {
		util = utilIn;
		myLevelNumber = levelNumber;
		myRow = rows;
		myCol = cols;
		myData = data;
		rightClick = new RightClickMenu(util, myData, 0, 0);
		myGrid = new Pane();
		myGrid.setPrefSize(720, 500);
		myGrid.setOnMousePressed(e -> mousePress(e));
		myGrid.setOnMouseMoved(e -> mouseMove(e));
		myGrid.getStyleClass().add("view-grid");
		myBorderPane = new BorderPane();
		Button butt = util.buildButton("addHo", e -> addHo());
		util.buildButton("addHo", e -> addHo());
		Button butt2 = util.buildButton("addVert", e -> addVert());
		util.buildButton("addVert", e -> addVert());
		mouseCords = buildMouseCords();
		HBox box = new HBox(butt, butt2, mouseCords);
		box.setPadding(new Insets(10));
		box.setSpacing(10);
		myBorderPane.setTop(box);
		myScroll = new ScrollPane(myGrid);
		myBorderPane.setCenter(myScroll);
	}
	
	public void setEntityIDcount(int in){
		j = 1000 + in;
		System.out.println(j + "is the number of entities");
	}
	
	private Label buildMouseCords(){
		Label mouseCords = new Label();
		mouseCords.setText("X:0  Y:0");
		return mouseCords;
	}

	private void mousePress(MouseEvent e) {
		if(rightClick.isShowing()){
			rightClick.hide();
		}
		if (e.isSecondaryButtonDown()) {
			rightClick.show(myGrid, e.getScreenX(), e.getScreenY(), e.getX(), e.getY());
		}
		else if (!e.isControlDown()) {
			placeImageAtLoc(e.getX(), e.getY());
		}
	}
	
	private void mouseMove(MouseEvent e){
		mouseCords.setText("X:" + e.getX() + "  Y:" + e.getY());
	}

	private void addHo() {
		myGrid.setPrefWidth(myGrid.getWidth() + 60);
		myCol++;
		myData.getLevelEntity().addCol();
	}

	private void addVert() {
		myGrid.setPrefHeight(myGrid.getHeight() + 60);
		myRow++;
		myData.getLevelEntity().addRow();
	}

	private void placeImageAtLoc(double row, double col) {
		Entity userSelectedEntity = myData.getUserSelectedEntity();
		if (userSelectedEntity != null && userSelectedEntity.getComponent(ComponentType.Location) == null) {
			Entity placedEntity = userSelectedEntity.clone();
			placedEntity.setID(j);
			j++;
			placedEntity.addComponent(new LocationComponent(row, col));
			myData.placeEntity(myLevelNumber, placedEntity);
		}
	}

	public void drawPlacedEntities() {
		Set<Integer> entitySet = myData.getPlacedEntityMap().get(myLevelNumber).keySet();
		if(entitySet.size() != 0){
			for (int entityID : entitySet) {
				drawEntity(myData.getPlacedEntityMap().get(myLevelNumber).get(entityID));
			}
		}
	}

	public void drawEntity(Entity entity) {
		LocationComponent entityLocation = (LocationComponent) entity.getComponent(ComponentType.Location);
		SpriteComponent entitySprite = (SpriteComponent) entity.getComponent(ComponentType.Sprite);
		ImageView spriteImage = new ImageView(entitySprite.getSprite());
		ImagePropertiesComponent imageProperties = (ImagePropertiesComponent) entity
				.getComponent(ComponentType.ImageProperties);
		// Modify this part to make children span multiple rows/columns
		double height = imageProperties.getHeight();
		double width = imageProperties.getWidth();
		spriteImage.setFitHeight(height);
		spriteImage.setFitWidth(width);
		spriteImage.setX(entityLocation.getX());
		spriteImage.setY(entityLocation.getY());
		spriteImage.setOnMousePressed(e -> {
			if (e.isControlDown() || e.isAltDown()) {
				selectEntity(entity);
				orgSceneX = e.getSceneX();
				orgSceneY = e.getSceneY();
				
			}
			if (rightClick.isShowing()) {
				rightClick.hide();
			}
			if (e.isSecondaryButtonDown()) {
				selectEntity(entity);
				rightClick.show(myGrid, e.getScreenX(), e.getScreenY(), e.getX(), e.getY());
			}
		});
		spriteImage.setOnMouseDragged(e -> {
			ImageView c = (ImageView) (e.getSource());
			double offsetX = e.getSceneX() - orgSceneX;
			double offsetY = e.getSceneY() - orgSceneY;
			
			if (e.isControlDown()) {
									
				c.setTranslateX(c.getTranslateX() + offsetX);
				c.setTranslateY(c.getTranslateY() + offsetY);

				orgSceneX = e.getSceneX();
				orgSceneY = e.getSceneY();
			}
			if (e.isAltDown()) {
				ImagePropertiesComponent imageProp = (ImagePropertiesComponent) entity
						.getComponent(ComponentType.ImageProperties);
				c.setFitHeight(imageProp.getWidth() + offsetY);
				c.setFitWidth(imageProp.getHeight() + offsetX);
			}
		});
		spriteImage.setOnMouseReleased(e -> {
			ImageView c = (ImageView) (e.getSource());
			if (e.isControlDown()) {
				unselectEntity(entity);
				System.out.println("dropped at " + e.getSceneX() + " " + e.getSceneY());
				entity.addComponent(new LocationComponent(e.getSceneX(), e.getSceneY()));
//				Iterator<IComponent> iter = entity.getComponents().iterator();
//				while (iter.hasNext()) {
//					System.out.println(iter.next().getComponentType().toString());
//				}
			}
			if (e.isAltDown()){
				unselectEntity(entity);
				entity.addComponent(new ImagePropertiesComponent(c.getFitWidth(), c.getFitHeight()));
			}
		});
		placedImages.put(entity, spriteImage);
		myGrid.getChildren().add(spriteImage);
	}

	public void clearEntitiesOnGrid() {
		for (Entity e : placedImages.keySet()) {
			myGrid.getChildren().remove(placedImages.get(e));
		}
		placedImages.clear();
	}

/*	public void placeEntitiesFromFile(int levelNumber) {
		Entity tempEntity;
		Map<Integer, Map<Integer, Entity>> myMap = myData.getPlacedEntityMap();
		for (int i : myMap.get(myLevelNumber).keySet()) {
			tempEntity = myMap.get(myLevelNumber).get(i);
			drawEntity(tempEntity);
			j++;
			System.out.println(j + "is the current entityID value");
		}
	} */

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

	private void removeEntity(Entity entity) {
		myGrid.getChildren().remove(placedImages.get(entity));
		placedImages.remove(entity);
	}

	public void removeEntity() {
		removeEntity(myData.getUserGridSelectedEntity());
	}

	public void unselectEntity(Entity entity) {
		ImageView temp = placedImages.get(myData.getUserGridSelectedEntity());
		temp.setStyle("");
	}

	public Node getContent() {
		return myBorderPane;
	}

	public void selectEntity(Entity entity) {
		if (placedImages.containsKey(myData.getUserGridSelectedEntity())) {
			unselectEntity(entity);
		}
		myData.setUserGridSelectedEntity(entity);
		ImageView i = placedImages.get(entity);
		i.setStyle("-fx-effect: innershadow(gaussian, #039ed3, 3, 1.0, 0, 0);");
	}

	@Override
	public Region buildComponent() {
		return myBorderPane;
	}
}
