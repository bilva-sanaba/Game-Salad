package view;

import java.util.Observable;
import java.util.Observer;

public class ViewController implements Observer {
	private ViewData myData;
	private GridView myGrid;
	private TabView myTab;
	
	public ViewController(ViewData dataIn, GridView gridIn, TabView tabIn){
		myData = dataIn;
		myGrid = gridIn;
		myTab = tabIn;
		myData.addObserver(this);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		myTab.clearEntitiesOnTab();
		myTab.placeEntitiesFromFile();
		
		myGrid.clearEntitiesOnGrid();
		myGrid.placeEntitiesFromFile();
	}
}
