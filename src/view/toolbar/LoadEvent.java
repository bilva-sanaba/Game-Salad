package view.toolbar;

import java.io.File;
import java.util.*;
import java.util.function.Consumer;

import components.entityComponents.ComponentType;
import components.entityComponents.LocationComponent;
import data_interfaces.*;
import entity.*;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import view.GridView;
import view.UtilityFactory;
import view.ViewData;

public class LoadEvent extends GameSavingDataTool implements ToolBarButtonEvent {
	private ViewData myData;
	private final static String FILELOADSTRING = "Choose the file to load: ";
	
	public LoadEvent(UtilityFactory utilF, ViewData data){
		myData = data;
		//whattodo.put(SplashEntity.class, e -> myData.setSplashEntity((SplashEntity) e));
		//whattodo.put(LevelEntity.class, e -> myData.setLevelEntity((LevelEntity) e));
	}

	@Override
	public void event() {
		XMLPlacedParser xpp = new XMLPlacedParser();
		Stage newStage = new Stage();
		FileChooser fc = new FileChooser();
		fc.setTitle(FILELOADSTRING);
		fc.setInitialDirectory(new File(System.getProperty("user.dir")));
		fc.getExtensionFilters().setAll(
				new ExtensionFilter("Text Files", "*" + getSuffix()));

		File dataFile = fc.showOpenDialog(newStage);
		if (dataFile != null) {
			String dataPath = dataFile.getAbsolutePath();
			String[] splitS = dataPath.split("[\\\\/]");
			String firstSplit = splitS[splitS.length - 1];
			String name = firstSplit.substring(0, firstSplit.length()
					- getSuffix().length());
			myData.setGameName(name);
			List<Map> toPlace = xpp.getData(name);
			setLevelEntities(toPlace.get(1));
			setPlacedEntities(toPlace.get(0));
			setSplashEntity(toPlace.get(2));
			myData.refresh();
		}
	}
	
	private void setPlacedEntities(Map m) {
		Map <Integer, HashMap<Integer, Entity>> ret = m;
		myData.getPlacedEntityMap().clear();
		for (int i = 1; i <= ret.keySet().size(); i++) {
			myData.getPlacedEntityMap().put(i, new HashMap<Integer, Entity>());
		}
		myData.resetLevelTabs();
		for (int i = 1; i <= ret.keySet().size(); i++) {
			myData.getPlacedEntityMap().put(i, ret.get(i));
		}
	}
	
	private void setLevelEntities(Map m) {
		myData.getLevelEntityMap().clear();
		Map <Integer, LevelEntity> lm = m;
		for (int i = 1; i <= lm.size(); i++) {
			myData.setLevelEntity(i, lm.get(i));
		}
	}
	
	private void setSplashEntity(Map m) {

		Map<Integer, SplashData> sm = m;
		myData.setSplashEntity((SplashData) sm.get(getSplashConstant())); 

	}
}