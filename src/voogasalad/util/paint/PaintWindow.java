package voogasalad.util.paint;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class PaintWindow implements Paint {
	
	public static final String RESOURCE_PACKAGE = "resources/";
	public static final String STYLESHEET = "PaintWindowView.css";
	
	private DrawingToolChooser myDrawer;
	private IDrawingToolbar myToolbar;
	private IMenu myMenu;
	private ICanvas myCanvas;
	private Stage myDrawingStage;
	private BorderPane root;
	private Scene myScene;

	public PaintWindow() {
		myDrawingStage = new Stage();
		myDrawingStage.setScene(setDrawingScene());
		myDrawingStage.showAndWait();
		myDrawingStage.setOnCloseRequest(e -> paintImage());
	}

	private Scene setDrawingScene() {
		root = new BorderPane();
		myScene = new Scene(root, 800, 800);
		myScene.getStylesheets().add(RESOURCE_PACKAGE + STYLESHEET);
		buildDrawingArea();
		return myScene;
	}

	private void buildDrawingArea() {
		myDrawer = new DrawingToolChooser();
		myCanvas = new DrawingCanvas(myDrawer, root);
		myMenu = new Menu(myCanvas);
		myToolbar = new Toolbar(myDrawer);
		root.setCenter(myCanvas.getRegion());
		root.setLeft(myMenu.returnRegion());
		root.setTop(myToolbar.getRegion());
	}

	@Override
	public void paintImage() {
		myMenu.saveEvent();
	}
}
