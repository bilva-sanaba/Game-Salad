package view;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import components.entityComponents.ComponentType;
import entity.Entity;

public class ViewController implements Observer {
	private ViewData myData;
	private HashMap<Integer, GridView> myGrids;
	private TabView myTab;
	
	public ViewController(ViewData dataIn, HashMap<Integer, GridView> grids, TabView tabIn) {
		myData = dataIn;
		myGrids = grids;
		myTab = tabIn;
	}

	
/*	@Override
	public void update(Observable o, Object arg){
		myGrid.clearEntitiesOnGrid();
		myGrid.drawAllEntities();
	} */
	
	@Override
	public void update(Observable o, Object arg) {
//		if (arg.equals("refresh")) {
//			myGrid.updateBackground();
//			myGrid.setUpLevel();
//		}
//		else if (arg.equals("reset")) {
//			myGrid.clearEntitiesOnGrid();
//		}
//		else if (arg.equals("unplace")) {
//			myGrid.removeEntity();
//		}
//		else if (!(((Entity) arg).getComponent(ComponentType.Location) == null)) {
//			myGrid.drawEntity((Entity) arg);
//		}
//		else {
//			myTab.addEntity((Entity) arg);
//		}
		
		if (myData.getUserSelectedEntity() == null) {
			myTab.clearSelected();
		}
		
		for (Integer levelNumber : myGrids.keySet()) {
			myGrids.get(levelNumber).clearEntitiesOnGrid();
			myGrids.get(levelNumber).drawPlacedEntities();
		}
		
		myTab.clearEntitiesOnTab();
		myData.addPresetEntities();
		myTab.addDefinedEntities();
	}

}
