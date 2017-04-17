package voogasalad.util.paint;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class PaintWindow implements Paint{
	private DrawingTool myDrawer;
	private IDrawingToolBar myToolbar;
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
		myDrawer = new Pen();
		myCanvas = new DrawingCanvas(myDrawer);
		myMenu = new Menu(myCanvas);
//	    myToolbar = new DrawingToolBar(myDrawer);
	    root.getChildren().addAll(myCanvas.getRegion(), myMenu.returnRegion()
	    	//	,myToolBar.getRegion()
	    		);
	}

	@Override
	public Image paintImage() {
		return myCanvas.getImage();
	}

	public static void main(String[] arg0){
		PaintWindow pw = new PaintWindow();
	}
}
