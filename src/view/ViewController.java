package view;

import java.util.Observable;
import java.util.Observer;

import components.entityComponents.ComponentType;
import entity.Entity;

public class ViewController implements Observer {
	private ViewData myData;
	private GridView myGrid;
	private TabView myTab;
	
	public ViewController(ViewData dataIn, GridView gridIn, TabView tabIn){
		myData = dataIn;
		myGrid = gridIn;
		myTab = tabIn;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if(arg.equals("refresh")){
			myTab.clearEntitiesOnTab();
			
			myGrid.updateBackground();
			myGrid.setUpLevel();
			myGrid.clearEntitiesOnGrid();
		}
		else if (!(((Entity) arg).getComponent(ComponentType.Location) == null)){
			myGrid.drawEntity((Entity) arg);
		}
		else {
			myTab.addEntity((Entity) arg);
		}
	}
}
