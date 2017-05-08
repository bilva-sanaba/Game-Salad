// This entire file is part of my masterpiece.
// Justin Yang
//
// ViewController is the class that connects all of the GUI components in the
// authoring environment. Whenever ViewData is modified, it notifies its observer,
// ViewController, which then calls the update method in ViewController. This method
// redraws all of the GUI components based on the data in ViewData. This design is
// very flexible because if you want to add a new GUI component, you don't have to
// change the code for any of the other GUI components; you just have to add some
// methods to ViewData for modifying the data pertaining to that GUI component, and
// then add a few lines to the update method in ViewController specifying how to handle
// the changes in data.

package view;

import java.util.Observable;
import java.util.Observer;

import entity.Entity;

public class ViewController implements Observer {
	private ViewData myData;
	private LevelTabView levelTabs;
	private TabView myTab;
	private UtilityFactory utilF;

	public ViewController(ViewData dataIn, LevelTabView levelIn, TabView tabIn, UtilityFactory utilIn) {
		myData = dataIn;
		levelTabs = levelIn;
		myTab = tabIn;
		utilF = utilIn;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg == null){
			Entity currentlySelected = myData.getUserSelectedEntity();
			LevelTab level = levelTabs.getTabsList().get(myData.getCurrentLevel());
			level.getGrid().clearEntitiesOnGrid();
			level.getGrid().drawPlacedEntities();
			level.getGrid().updateBackground();
			
			myTab.clearEntitiesOnTab();
			myTab.addDefinedEntities();
			myTab.selectEntity(currentlySelected);
		}
		else if(arg.equals("reset")){
			levelTabs.clearTabs();
			for(int i : myData.getPlacedEntityMap().keySet()){
				GridView tempGrid = new GridView(utilF, i, myData, myData.getLevelEntity(i).getRows(), myData.getLevelEntity(i).getCols());
				levelTabs.addNewTab(tempGrid, i);
			}
			myTab.setCamera(myData.getLevelEntity(1).getCamera());
		}
	}

}
