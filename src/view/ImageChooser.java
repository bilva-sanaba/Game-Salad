package view;

import java.io.File;
import java.net.MalformedURLException;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class ImageChooser {
	
	static final String[] EXTENSIONS = new String[]{
	        "gif", "png", "bmp" // and other formats you need
	    };

	public void chooseFile(){
		ImageView imgv = new ImageView();
	    FileChooser chooser = makeChooser();
	    File file = chooser.showOpenDialog(new Stage());
	    if(file != null) {
	        String imagepath = "";
			try {
				imagepath = file.toURI().toURL().toString();
			} catch (MalformedURLException e) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
		        alert.setTitle("Information Dialog");
		        alert.setHeaderText("Error");
		        alert.setContentText("Invalid URL");
		        alert.showAndWait();
			}
	        Image image = new Image(imagepath);
	        imgv.setImage(image);
	    }
	    else
	    {
	        Alert alert = new Alert(Alert.AlertType.INFORMATION);
	        alert.setTitle("Information Dialog");
	        alert.setHeaderText("Error");
	        alert.setContentText("Please Select a File");
	        alert.showAndWait();
	    }
	}
	
	private FileChooser makeChooser() {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Select Image");
		chooser.setInitialDirectory(new File(System.getProperty("user.dir")));
		chooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files","*.bmp", "*.png", "*.jpg", "*.gif"));
		return chooser;
	}
}
