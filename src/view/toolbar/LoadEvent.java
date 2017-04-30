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
import view.ViewData;

public class LoadEvent extends GameSavingDataTool implements ToolBarButtonEvent {
	private ViewData myData;
	Map<Class<? extends Entity>, Consumer<Entity>> whattodo;
	
	public LoadEvent(ViewData data){
		myData = data;
		whattodo = new HashMap<Class<? extends Entity>, Consumer<Entity>>();
		//whattodo.put(SplashEntity.class, e -> myData.setSplashEntity((SplashEntity) e));
		//whattodo.put(LevelEntity.class, e -> myData.setLevelEntity((LevelEntity) e));
	}

	@Override
	public void event() {
		XMLPlacedParser xpp = new XMLPlacedParser();
		Stage newStage = new Stage();
		FileChooser fc = new FileChooser();
		fc.setTitle("Choose the file to load: ");
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
			System.out.println("BLOOMFELD FELD FELD FIELD FIELD" + name);
			myData.setGameName(name);
			//			System.out.println(col + " line 45 " + this.getClass());
			List <Map> toPlace = xpp.getData(name);
			
			setPlacedEntities(toPlace.get(0));
			setLevelEntities(toPlace.get(1));
			setSplashEntity(toPlace.get(2));
			//idk what method to use here
		}
		
	}
	
	private void setPlacedEntities(Map m) {
		
	}
	
	private void setLevelEntities(Map m) {
		
	}
	
	private void setSplashEntity(Map m) {
		myData.setSplashEntity((SplashEntity) m.get(getSplashConstant())); 
	}
}