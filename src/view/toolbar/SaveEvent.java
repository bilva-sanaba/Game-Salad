package view.toolbar;

import java.util.*;
import data_interfaces.*;

import entity.Entity;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;
import view.ViewData;

public class SaveEvent implements ToolBarButtonEvent{
	ViewData myData;
	
	public SaveEvent(ViewData data){
		myData = data;
	}

	@Override
	public void event() {
		XMLWriter xw = new XMLWriter();
		String fileName;
		Collection <Entity> l = myData.getEntityMap().values();
		
		TextInputDialog tid = new TextInputDialog(myData.getGameName());
		tid.setTitle("Saving File");
		tid.setHeaderText("Please choose a name for your game: ");
		Optional <String> result = tid.showAndWait();
		myData.setGameName(result.get());
		fileName = result.get();
				
		xw.writeFile(fileName, l);
	}
	
}
