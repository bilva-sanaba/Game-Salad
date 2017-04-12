package view.toolbar;

import java.util.*;
import data_interfaces.*;

import entity.Entity;
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
		List <Entity> l = new ArrayList<Entity>();
		l.add(myData.getSplashEntity());
		l.add(myData.getLevelEntity());
		updateList(l, myData.getDefinedEntityMap());
		updateList(l, myData.getPlacedEntityMap());
		//updateList(l, myData.getConfigurationEntityMap());
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
	
	private void updateList (List <Entity> l, Map <Integer,Entity> m) {
		for (Integer key : m.keySet()) {
			l.add(m.get(key));
		}
	}
}
