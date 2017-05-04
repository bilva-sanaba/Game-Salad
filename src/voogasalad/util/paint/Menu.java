package voogasalad.util.paint;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Orientation;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class Menu implements IMenu {
	
	private ToolBar myToolBar;
	private final static String PREFIX = "images" + File.separator;
	private ICanvas myCanvas;

	public Menu(ICanvas myCanvas2) {
		myCanvas = myCanvas2;
		myToolBar = new ToolBar();
		Button save = new Button("Save");
		Button load = new Button("Load");
		save.setOnAction(e -> saveEvent());
		load.setOnAction(e -> loadEvent());
		myToolBar.setOrientation(Orientation.HORIZONTAL);
		myToolBar.getItems().add(save);
		myToolBar.getItems().add(load);
	}

	@Override
	public Region returnRegion() {
		return myToolBar;
	}
	
	public void saveEvent() {
		RenderedImage ri;
		ImageRefiner ir = new ImageRefiner();
		TextInputDialog tid = new TextInputDialog();
		tid.setTitle("Saving File");
		tid.setHeaderText("Please choose a name for your image: ");
		Optional<String> result = tid.showAndWait();
		try {
			WritableImage wi = new WritableImage((int)myCanvas.getWidth(), (int)myCanvas.getHeight());
			myCanvas.snapshot(wi);
			ir.turnAllWhiteTransparent(wi);
			ri = SwingFXUtils.fromFXImage(wi, null);
		} catch (NoSuchElementException e) {
			return;
		}
	}

	private void loadEvent() {
		Image img;
		double newheight, newwidth;
		
		Stage newStage = new Stage();
		FileChooser fc = new FileChooser();
		fc.setTitle("Choose the file to load: ");
		fc.setInitialDirectory(new File(System.getProperty("user.dir")));
		fc.getExtensionFilters().setAll(
				new ExtensionFilter("Image Files", "*.jpg", "*.png", "*.gif", "*.jpeg"));
		File dataFile = fc.showOpenDialog(newStage);
		try {
			img = new Image(dataFile.toURI().toURL().toString());
		} catch (MalformedURLException e) {
			return;
		}

		GraphicsContext gc = myCanvas.getGraphicsContext2D();
		if (img.getHeight() > img.getWidth()) {
			newheight = myCanvas.getHeight();
			newwidth = scaleProportional(img.getHeight(), img.getWidth(), myCanvas.getWidth());
		}
		else {
			newwidth = myCanvas.getWidth();
			newheight = scaleProportional(img.getWidth(), img.getHeight(), myCanvas.getHeight());
		}
		gc.drawImage(img, 0, 0, newwidth, newheight);
	}
	
	private double scaleProportional(double bigger, double smaller, double canVal) {
		return (smaller / bigger) * canVal;
 	}
}
