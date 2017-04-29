package view.toolbar;

import java.util.*;
import data_interfaces.*;

import entity.Entity;
import entity.IEntity;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;
import view.ViewData;

public class SaveEvent extends GameSavingDataTool implements ToolBarButtonEvent {
	ViewData myData;

	public SaveEvent(ViewData data) {
		myData = data;
	}

	@Override
	public void event() {
		XMLWriter xw = new XMLWriter();
		String fileName;
		List <Map> l = new ArrayList<Map>();
		
		//how do i get this list???? BLOOOMFELD!!!!!
		l.add(myData.getPlacedEntityMap());
		l.add(myData.getLevelEntityMap());
		
		
		
		TextInputDialog tid = new TextInputDialog(myData.getGameName());
		tid.setTitle("Saving File");
		tid.setHeaderText("Please choose a name for your game: ");
		Optional<String> result = tid.showAndWait();
		try {
			myData.setGameName(result.get());
			fileName = result.get();
			System.out.print(result.get());
			xw.writeFile(fileName, l);
		} catch (NoSuchElementException e) {
			return;
		}
	}
}
