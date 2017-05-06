// This entire file is part of my masterpiece.
// Josh Kopen

package view.toolbar;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import data_interfaces.GameSavingDataTool;
import data_interfaces.XMLWriter;
import entity.Entity;
import entity.LevelEntity;
import entity.SplashData;
import javafx.scene.control.TextInputDialog;
import view.UtilityFactory;
import view.ViewData;

/**
 * I believe that this is well written code as it now saves the data in a far more intelligent way which I use
 * in my other portions, does not use e.printStackTrace() for my error and handles it the way it makes most sense,
 * and now relies on the UtilityFactory for the prompt which keeps me up to date on the language and not relying
 * on static final Strings.
 * @author joshuakopen
 *
 */

public class SaveEvent extends GameSavingDataTool implements ToolBarButtonEvent {
	private ViewData myData;
	private UtilityFactory utilFac;

	public SaveEvent(UtilityFactory utilF, ViewData data) {
		myData = data;
		utilFac = utilF;
	}

	@Override
	public void event() {
		XMLWriter xw = new XMLWriter();
		String fileName;
		Map <Class<? extends Entity>, Map> saveMap = new HashMap<Class<? extends Entity>, Map>();
		
		saveMap.put(Entity.class, myData.getPlacedEntityMap());
		saveMap.put(LevelEntity.class, myData.getLevelEntityMap());
		Map <Integer, SplashData> tempMap = new HashMap<Integer, SplashData>();
		tempMap.put(getSplashConstant(), myData.getSplashEntity());
		saveMap.put(SplashData.class, tempMap);
		
		TextInputDialog tid = new TextInputDialog(myData.getGameName());
		tid.setTitle(utilFac.getText("FileSaveTitle"));
		tid.setHeaderText(utilFac.getText("FileSaveBody"));
		Optional<String> result = tid.showAndWait();
		try {
			myData.setGameName(result.get());
			fileName = result.get();
			xw.writeFile(fileName, saveMap);
		} catch (NoSuchElementException e) {
			return;
		}
	}
}
