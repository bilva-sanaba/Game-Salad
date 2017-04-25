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
		whattodo.put(SplashEntity.class, e -> myData.setSplashEntity((SplashEntity) e));
		whattodo.put(LevelEntity.class, e -> myData.setLevelEntity((LevelEntity) e));
	}

	@Override
	public void event() {
		Communicator c;
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
			c = new Communicator(name);
			Collection <Entity> col = c.getData();
//			System.out.println(col + " line 45 " + this.getClass());
			myData.refresh();
			for (Entity e: col) {
				System.out.println(e.getClass().toString());
				Class<? extends Entity> eClass = e.getClass();
				Consumer<Entity> cc = whattodo.get(eClass);
				if (cc == null) throw new RuntimeException();
				cc.accept(e);;
				
				
				if (e.getClass().toString().equals("class entity.LevelEntity")) {
					System.out.println("Level entity is set");
					myData.setLevelEntity((LevelEntity) e);
				}
				else if (e instanceof SplashEntity) {
					myData.setEntity((SplashEntity) e);
				}
				else if (isPlaced(e)) {
					System.out.println("isplaced");
					myData.placeEntity(e);
				}
				else {
					System.out.println("defines an entity");
					myData.defineEntity(e);
				}
			}
		}
		
	}
	
	private boolean isPlaced(Entity e) {
		try {
			return (!e.getComponent(ComponentType.Location).equals(null));
		}
		catch (NullPointerException npe) {
			return false;
		}
	}
	
	
	
}