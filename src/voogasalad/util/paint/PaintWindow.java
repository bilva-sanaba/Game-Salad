package voogasalad.util.paint;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class PaintWindow implements Paint{
	private DrawingTool myDrawer;
	private IDrawingToolbar myToolbar;
	private IMenu myMenu;
	private ICanvas myCanvas;
	private Stage myDrawingStage;
	private Group root;
	private Scene myScene;
	
	public PaintWindow(){
		myDrawingStage = new Stage();
		myDrawingStage.setScene(setDrawingScene());
		myDrawingStage.showAndWait();
		myDrawingStage.setOnCloseRequest(e -> paintImage());
	}
	
	private Scene setDrawingScene() {
		root = new Group();
		myScene = new Scene(root);
		buildDrawingArea();
		return myScene;
	}

	private void buildDrawingArea() {
		myDrawer = new DrawCircle();
		
		myCanvas = new DrawingCanvas(myDrawer, root);
		myMenu = new Menu(myCanvas);
	    //myToolbar = new Toolbar(myDrawer);
	   root.getChildren().addAll(myCanvas.getRegion(), myMenu.returnRegion()/*,
	    		myToolbar.getRegion()*/);
	}

	@Override
	public void paintImage() {
		myMenu.saveEvent();
	}
}
