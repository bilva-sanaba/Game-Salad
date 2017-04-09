package view.toolbar;

import java.io.File;
import java.util.*;
import data_interfaces.*;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import view.ViewData;

public class LoadEvent extends GameSavingDataTool implements ToolBarButtonEvent {
	ViewData myData;
	
	public LoadEvent(ViewData data){
		myData = data;
	}

	@Override
	public void event() {
		Stage newStage = new Stage();
		ViewData newVD = new ViewData();
		FileChooser fc = new FileChooser();
		fc.setTitle("Choose the file to load: ");
		fc.setInitialDirectory(new File(System.getProperty("user.dir")));
		fc.getExtensionFilters().setAll(new ExtensionFilter("Text Files", "*" + getSuffix()));
		
		File dataFile = fc.showOpenDialog(newStage);
	}
	
	
}
