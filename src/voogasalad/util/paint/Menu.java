package voogasalad.util.paint;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class Menu implements IMenu {
	
	private ToolBar myToolBar;
	private Canvas myCanvas;
	private final static String PREFIX = "images/";
	
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
		TextInputDialog tid = new TextInputDialog();
		tid.setTitle("Saving File");
		tid.setHeaderText("Please choose a name for your image: ");
		Optional<String> result = tid.showAndWait();
		try {
			WritableImage wi = new WritableImage((int)myCanvas.getHeight(), (int)myCanvas.getWidth());
			myCanvas.snapshot(null, wi);
			RenderedImage ri = SwingFXUtils.fromFXImage(wi, null);
			ImageIO.write(ri, ".png", new File(PREFIX + result.get()));
		} catch (NoSuchElementException e) {
			return;
		} catch (IOException e) {
			return;
		}
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
