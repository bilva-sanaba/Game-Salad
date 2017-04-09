package view.toolbar;

import java.io.File;
import java.util.*;

import components.ComponentType;
import components.LocationComponent;
import data_interfaces.*;
import entity.*;

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
		Communicator c;
		Stage newStage = new Stage();
		ViewData newVD = new ViewData();
		FileChooser fc = new FileChooser();
		fc.setTitle("Choose the file to load: ");
		fc.setInitialDirectory(new File(System.getProperty("user.dir")));
		fc.getExtensionFilters().setAll(new ExtensionFilter("Text Files", "*" + getSuffix()));
		
		File dataFile = fc.showOpenDialog(newStage);
		if (!dataFile.equals(null)) {
			String dataPath = dataFile.getAbsolutePath();
			
			String [] splitS = dataPath.split("/");
			String firstSplit = splitS[splitS.length -1];
			String name = firstSplit.substring(0, firstSplit.length() - getSuffix().length());
			newVD.setGameName(name);
			c = new Communicator(name);
			Collection <Entity> col = c.getData();
			for (Entity e: col) {
				if (isPlaced(e)) {
					newVD.placeEntity(e);
				}
				else {
					newVD.defineEntity(e);
				}
			}
		}
		myData = newVD;
	}
	
	private boolean isPlaced(Entity e) {
		return (!e.getComponent(ComponentType.Location).equals(null));
	}
	
}
