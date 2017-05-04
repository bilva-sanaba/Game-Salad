package view;

import java.util.HashMap;
import java.util.Map;
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


	private static final int GRIDWIDTH = 900;
	private static final int GRIDHEIGHT = 720;

	private static final int GRID_INTERVAL = 10;

	private RightClickMenu rightClick;
	private ScrollPane myScroll;
	private Pane myGrid;
	private ViewData myData;
	private UtilityFactory util;
	private Label mouseCords;
	private int myRow;
	private int myCol;
	private int myLevelNumber;
	double xClickOffset, yClickOffset;
	double imageWidth, imageHeight;
	private Map<Entity, ImageView> placedImages;
	private BorderPane myBorderPane;

	public GridView(UtilityFactory utilIn, int levelNumber, ViewData data, int rows, int cols) {
		util = utilIn;
		myLevelNumber = levelNumber;
		myRow = rows;
		myCol = cols;
		myData = data;
		placedImages = new HashMap<Entity, ImageView>();

		rightClick = new RightClickMenu(util, myData);

		myGrid = util.buildGrid(cols * 10, rows * 10, "view-grid");

		myGrid.setOnMousePressed(e -> mousePress(e));
		myGrid.setOnMouseMoved(e -> mouseMove(e));

		mouseCords = buildMouseCords();
		HBox gridMenu = buildGridMenu();

		myBorderPane = new BorderPane();
		myScroll = new ScrollPane(myGrid);
		myBorderPane.setTop(gridMenu);
		myBorderPane.setCenter(myScroll);
	}

	private HBox buildGridMenu(){
		Button butt = util.buildButton("addHo", e -> addHo());
		Button butt2 = util.buildButton("addVert", e -> addVert());
		Button butt3 = util.buildButton("removeHo", e -> removeHo());
		Button butt4 = util.buildButton("removeVert", e -> removeVert());
		HBox box = util.buildHBox(butt, butt2, butt3, butt4, mouseCords);
		box.setPadding(new Insets(10));
		box.setSpacing(10);
		return box;
	}

	public void setLevelNumber(int i) {
		myLevelNumber = i;
	}

	private Label buildMouseCords(){
		Label mouseCords = new Label();
		mouseCords.setText("X:0  Y:0");
		return mouseCords;
	}

	private void mousePress(MouseEvent e){
		if (!rightClick.isShowing() && e.isSecondaryButtonDown()) {
			rightClick.show(myGrid, myData.getCopiedEntity(), e.getScreenX(), e.getScreenY(), e.getX(), e.getY());
		}
		else if (!e.isSecondaryButtonDown() && !e.isControlDown() && !e.isAltDown()) {
			rightClick.hide();
			placeImageAtLoc(e.getX() , e.getY());
		}
	}

	private void mouseMove(MouseEvent e){
		mouseCords.setText("X:" + e.getX() + "  Y:" + e.getY());
	}

	private void addHo() {
		myGrid.setPrefWidth(myGrid.getWidth() + 60);
		myCol+= 6;
		myData.getLevelEntity(myData.getCurrentLevel()).addCol(6);

	}

	private void addVert() {
		myGrid.setPrefHeight(myGrid.getHeight() + 60);
		myRow+=6;
		myData.getLevelEntity(myData.getCurrentLevel()).addRow(6);
	}

	private void removeHo(){
		if(myCol > 66){
			myGrid.setPrefWidth(myGrid.getWidth() - 60);
			myCol-=6;
			myData.getLevelEntity(myData.getCurrentLevel()).addCol(-6);
		}
	}

	private void removeVert(){
		if(myRow > 48){
			myGrid.setPrefHeight(myGrid.getHeight() - 60);
			myRow-=6;
			myData.getLevelEntity(myData.getCurrentLevel()).addRow(-6);
		}
	}


	private void placeImageAtLoc(double row, double col) {
		Entity userSelectedEntity = myData.getUserSelectedEntity();
		if (userSelectedEntity != null) {
			Entity placedEntity = userSelectedEntity.clone();
			placedEntity.setID(myData.getPlacedEntityID());
			double newRow = snapToGrid(row, GRID_INTERVAL);
			double newCol = snapToGrid(col, GRID_INTERVAL);
			placedEntity.addComponent(new LocationComponent(newRow, newCol));
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

	private void entityMousePress(MouseEvent e, Entity entity){
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
	}

	private void entityMouseDrag(MouseEvent e, Entity entity){
		ImageView c = (ImageView) (e.getSource());
		double offsetX = e.getX() - c.getX() - xClickOffset;
		double offsetY = e.getY() - c.getY() - yClickOffset;
		if (e.isControlDown()) {
			double newX = snapToGrid(e.getX() - xClickOffset, GRID_INTERVAL);
			double newY = snapToGrid(e.getY() - yClickOffset, GRID_INTERVAL);
			c.setX(newX);
			c.setY(newY);
			entity.addComponent(new LocationComponent(newX, newY));
		}
		if (e.isAltDown()) {
			// Change 10 to static MIN_ENTITY_WIDTH/HEIGHT
			c.setFitHeight(Math.max(imageHeight + offsetY, 10));
			c.setFitWidth(Math.max(imageWidth+ offsetX, 10));
		}
	}

	private void entityMouseReleased(MouseEvent e, Entity entity){
		ImageView c = (ImageView) (e.getSource());
		unselectEntity(entity);
		entity.addComponent(new ImagePropertiesComponent(c.getFitHeight(), c.getFitWidth()));
	}

	private double snapToGrid(double val, int gridInterval) {
		double remainder = val % gridInterval;
		if (remainder > gridInterval / 2) {
			return val - remainder + gridInterval;
		}
		else {
			return val - remainder;
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
		spriteImage.setOnMousePressed(e -> entityMousePress(e, entity));
		spriteImage.setOnMouseDragged(e -> entityMouseDrag(e, entity));
		spriteImage.setOnMouseReleased(e -> entityMouseReleased(e, entity));
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
		int totalRow = myData.getLevelEntity(myData.getCurrentLevel()).getRows();
		int totalCol = myData.getLevelEntity(myData.getCurrentLevel()).getCols();
		while (myCol != totalCol) {
			addHo();
		}
		while (myRow != totalRow) {
			addVert();
		}
	}

	public void updateBackground() {
			String filePath = myData.getLevelEntity(myData.getCurrentLevel()).getBackgroundFilePath();
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
