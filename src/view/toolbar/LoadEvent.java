// This entire file is part of my masterpiece.
// Josh Kopen

package view.toolbar;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import data_interfaces.GameSavingDataTool;
import data_interfaces.XMLPlacedParser;
import entity.Entity;
import entity.LevelEntity;
import entity.SplashData;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import view.UtilityFactory;
import view.ViewData;

/**
 *  I believe that this file now represents good design as it utilizes the GameSavingDataTool to reduce redundancies
 * with Communicator, no longer relies on premade dialogue, and is overall well written code. I believe that I have used
 * the utility factory well to create my necessary items and created a far more justifiable parent class.
 * @author joshuakopen
 *
 */

public class LoadEvent extends GameSavingDataTool implements ToolBarButtonEvent {
	private ViewData myData;
	private UtilityFactory utilFac;
	
	public LoadEvent(UtilityFactory utilF, ViewData data){
		myData = data;
		utilFac = utilF;
	}

	@Override
	public void event() {
		XMLPlacedParser xpp = new XMLPlacedParser();
		Stage newStage = new Stage();
		FileChooser fc = new FileChooser();
		fc.setTitle(utilFac.getText("FileLoadString"));
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
			Map<Class<? extends Entity>, Map> toPlace = xpp.getData(name);
			setLevelEntities(toPlace);
			setPlacedEntities(toPlace);
			setSplashEntity(toPlace);
			myData.refresh();
		}
	}
	
	private void setPlacedEntities(Map <Class<? extends Entity>, Map> m) {
		Function f = getFunctionMap().get(Entity.class);
		Map <Integer, HashMap<Integer, Entity>> ret = (Map<Integer, HashMap<Integer, Entity>>) f.apply(m);
		myData.getPlacedEntityMap().clear();
		for (int i = 1; i <= ret.keySet().size(); i++) {
			myData.getPlacedEntityMap().put(i, new HashMap<Integer, Entity>());
		}
		myData.resetLevelTabs();
		for (int i = 1; i <= ret.keySet().size(); i++) {
			myData.getPlacedEntityMap().put(i, ret.get(i));
		}
	}
	
	private void setLevelEntities(Map <Class<? extends Entity>, Map> m) {
		Function f = getFunctionMap().get(LevelEntity.class);
		Map <Integer, LevelEntity> lm = (Map<Integer, LevelEntity>) f.apply(m);
		for (int i = 1; i <= lm.size(); i++) {
			myData.setLevelEntity(i, lm.get(i));
		}
	}
	
	private void setSplashEntity(Map <Class<? extends Entity>, Map> m) {
		Function f = getFunctionMap().get(SplashData.class);
		myData.setSplashEntity((SplashData) f.apply(m)); 
	}
}