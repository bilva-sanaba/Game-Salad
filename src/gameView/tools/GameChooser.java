package gameView.tools;

import java.io.File;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class GameChooser {
	
	private Stage myStage;
	private static final String FILE_EXTENSION = "*.xlm";
	
	public GameChooser(Stage s) {
		myStage = s;
	}
	
	/**
	 * 
	 * @param extension
	 * @return The pop-up file chooser
	 */
	public File getFile() {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Open Data File");
		chooser.setInitialDirectory(new File(System.getProperty("user.dir")));
		chooser.getExtensionFilters().setAll(new ExtensionFilter("Text Files", FILE_EXTENSION));
		File dataFile = chooser.showOpenDialog(myStage);
		return dataFile;		
	}
}
