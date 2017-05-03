package view;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import components.entityComponents.ComponentType;
import components.entityComponents.ImagePropertiesComponent;
import components.entityComponents.LocationComponent;
import components.entityComponents.SpriteComponent;
import entity.Entity;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import view.commands.RightClickMenu;


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
	double xClickOffset, yClickOffset;
	double imageWidth, imageHeight;
	private HashMap<Entity, ImageView> placedImages = new HashMap<Entity, ImageView>();
	private BorderPane myBorderPane;

	// Refactor gridview and make it nonshitty

	public GridView(UtilityFactory utilIn, int levelNumber, ViewData data, int rows, int cols) {
		util = utilIn;
		myLevelNumber = levelNumber;
		myRow = rows;
		myCol = cols;
		myData = data;
		rightClick = new RightClickMenu(util, myData);
		myGrid = new Pane();
		myGrid.setPrefSize(720, 500);
		myGrid.setOnMousePressed(e -> mousePress(e));
		myGrid.setOnMouseMoved(e -> mouseMove(e));
		myGrid.getStyleClass().add("view-grid");
		myBorderPane = new BorderPane();
		Button butt = util.buildButton("addHo", e -> addHo());
		Button butt2 = util.buildButton("addVert", e -> addVert());
		mouseCords = buildMouseCords();
		HBox box = util.buildHBox(butt, butt2, mouseCords);
		box.setPadding(new Insets(10));
		box.setSpacing(10);
		myBorderPane.setTop(box);
		myScroll = new ScrollPane(myGrid);
		myBorderPane.setCenter(myScroll);
	}
	
	public void setLevelNumber(int i) {
		myLevelNumber = i;
	}
	
	private Label buildMouseCords(){
		Label mouseCords = new Label();
		mouseCords.setText("X:0  Y:0");
		return mouseCords;
	}

	private void mousePress(MouseEvent e) {
		if (!rightClick.isShowing() && e.isSecondaryButtonDown()) {
			rightClick.show(myGrid, myData.getCopiedEntity(), e.getScreenX(), e.getScreenY(), e.getX(), e.getY());
		}
		else if (!e.isSecondaryButtonDown() && !e.isControlDown() && !e.isAltDown()) {
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
		System.out.println(userSelectedEntity);
		if (userSelectedEntity != null) {
			Entity placedEntity = userSelectedEntity.clone();
			placedEntity.setID(myData.getPlacedEntityID());
			placedEntity.addComponent(new LocationComponent(row, col));
			myData.placeEntity(myLevelNumber, placedEntity);
		}
	}

	public void drawPlacedEntities() {
		Set<Integer> entitySet = myData.getPlacedEntityMap().get(myLevelNumber).keySet();
		if (entitySet.size() != 0) {
			for (int entityID : entitySet) {
				drawEntity(myData.getPlacedEntityMap().get(myLevelNumber).get(entityID));
			}
		}
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
		spriteImage.setX(entityLocation.getX());
		spriteImage.setY(entityLocation.getY());
		spriteImage.setOnMousePressed(e -> {
			if (e.isControlDown() || e.isAltDown()) {
				selectEntity(entity);
			}
			if (!rightClick.isShowing() && e.isSecondaryButtonDown()) {
				selectEntity(entity);
				rightClick.show(myGrid, entity, e.getScreenX(), e.getScreenY(), e.getX(), e.getY());
			}
			ImageView c = (ImageView) (e.getSource());
			xClickOffset = e.getX() - c.getX();
			yClickOffset = e.getY() - c.getY();
			
			imageWidth = c.getFitWidth();
			imageHeight = c.getFitHeight();
		});
		spriteImage.setOnMouseDragged(e -> {
			ImageView c = (ImageView) (e.getSource());
			double offsetX = e.getX() - c.getX() - xClickOffset;
			double offsetY = e.getY() - c.getY() - yClickOffset;
			if (e.isControlDown()) {
				c.setX(e.getX() - xClickOffset);
				c.setY(e.getY() - yClickOffset);
				entity.addComponent(new LocationComponent(e.getX() - xClickOffset, e.getY() - yClickOffset));
			}
			if (e.isAltDown()) {
				// Change 10 to static MIN_ENTITY_WIDTH/HEIGHT
				c.setFitHeight(Math.max(imageHeight + offsetY, 10));
				c.setFitWidth(Math.max(imageWidth+ offsetX, 10));
			}
		});
		spriteImage.setOnMouseReleased(e -> {
			ImageView c = (ImageView) (e.getSource());
			unselectEntity(entity);
			entity.addComponent(new ImagePropertiesComponent(c.getFitHeight(), c.getFitWidth()));
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
		if (myData.getUserGridSelectedEntity() != null) {
			ImageView temp = placedImages.get(myData.getUserGridSelectedEntity());
			temp.setStyle("");
			myData.setUserGridSelectedEntity(null);
		}
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
