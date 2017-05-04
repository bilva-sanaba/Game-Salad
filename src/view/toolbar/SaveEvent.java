package view.toolbar;

import java.util.*;
import data_interfaces.*;

import entity.Entity;
import entity.IEntity;
import entity.SplashData;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;
import view.UtilityFactory;
import view.ViewData;

public class SaveEvent extends GameSavingDataTool implements ToolBarButtonEvent {
	private ViewData myData;
	private final static String FILESAVETITLE = "Saving File";
	private final static String FILESAVEBODY = "Please choose a name for your game: ";

	public SaveEvent(UtilityFactory utilF, ViewData data) {
		myData = data;
	}

	@Override
	public void event() {
		XMLWriter xw = new XMLWriter();
		String fileName;
		List <Map> l = new ArrayList<Map>();
		
		l.add(myData.getPlacedEntityMap());
		l.add(myData.getLevelEntityMap());
		Map <Integer, SplashData> m = new HashMap<Integer, SplashData>();
		m.put(getSplashConstant(), myData.getSplashEntity());
		l.add(m);
		
		TextInputDialog tid = new TextInputDialog(myData.getGameName());
		tid.setTitle(FILESAVETITLE);
		tid.setHeaderText(FILESAVEBODY);
		Optional<String> result = tid.showAndWait();
		try {
			myData.setGameName(result.get());
			fileName = result.get();
			xw.writeFile(fileName, l);
		} catch (NoSuchElementException e) {
			return;
		}
	}
}
