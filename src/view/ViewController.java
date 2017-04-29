package view;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import components.entityComponents.ComponentType;
import entity.Entity;

public class ViewController implements Observer {
	private ViewData myData;
	private LevelTabView levelTabs;
	private TabView myTab;
	
	public ViewController(ViewData dataIn, LevelTabView levelIn, TabView tabIn) {
		myData = dataIn;
		levelTabs = levelIn;
		myTab = tabIn;
	}

	
/*	@Override
	public void update(Observable o, Object arg){
		myGrid.clearEntitiesOnGrid();
		myGrid.drawAllEntities();
	} */
	
	@Override
	public void update(Observable o, Object arg) {
		
		if (myData.getUserSelectedEntity() == null) {
			myTab.clearSelected();
		}
		
		LevelTab level = levelTabs.getTabsList().get(myData.getCurrentLevel() - 1);
		level.getGrid().clearEntitiesOnGrid();
		level.getGrid().drawPlacedEntities();
		
		myTab.clearEntitiesOnTab();
		myTab.addPresetEntities();
		myTab.addDefinedEntities();
	}

}
