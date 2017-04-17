package voogasalad.util.paint;

import java.io.File;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class Menu implements IMenu {
	
	private ToolBar myToolBar;
	private Canvas myCanvas;
	
	public Menu(Canvas c) {
		myCanvas = c;
		myToolBar = new ToolBar();
		Button save = new Button();
		Button load = new Button();
		save.setOnAction(e -> saveEvent());
		load.setOnAction(e -> loadEvent());
		
		myToolBar.getItems().add(save);
		myToolBar.getItems().add(load);
	}

	@Override
	public Region returnRegion() {
		return myToolBar;
	}
	
	private void saveEvent() {
		
	}

	private void loadEvent() {
		Image img;
		
		Stage newStage = new Stage();
		FileChooser fc = new FileChooser();
		fc.setTitle("Choose the file to load: ");
		fc.setInitialDirectory(new File(System.getProperty("user.dir")));
		fc.getExtensionFilters().setAll(
				new ExtensionFilter("Image Files", "jpg", "png", "gif", "jpeg"));
		File dataFile = fc.showOpenDialog(newStage);
		
		img = new Image(dataFile.getAbsolutePath());

		GraphicsContext gc = myCanvas.getGraphicsContext2D();
		gc.drawImage(img, 0, 0, myCanvas.getHeight(), myCanvas.getWidth());
	}
}
