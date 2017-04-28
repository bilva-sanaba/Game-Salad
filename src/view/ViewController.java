package view;

import java.util.Observable;
import java.util.Observer;

import components.entityComponents.ComponentType;
import entity.Entity;

public class ViewController implements Observer {
	private ViewData myData;
	private GridView myGrid;
	private TabView myTab;
	
	public ViewController(ViewData dataIn, GridView gridIn, TabView tabIn) {
		myData = dataIn;
		myGrid = gridIn;
		myTab = tabIn;
	}
	
	public void updateSelectedEntity(Entity entity) {
		if(!myData.getUserSelectedEntity().getComponent(ComponentType.Location).equals(null)) {
			myGrid.unselectEntity(myData.getUserSelectedEntity());
		}
		else{
			myTab.clearSelected();
		}
		if(!entity.getComponent(ComponentType.Location).equals(null)) {
			myGrid.selectEntity(entity);
		}
		myData.setUserSelectedEntity(entity);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if (arg.equals("refresh")) {
			myGrid.updateBackground();
			myGrid.setUpLevel();
		}
		else if (arg.equals("reset")) {
			myGrid.clearEntitiesOnGrid();
		}
		else if (arg.equals("unplace")) {
			myGrid.removeEntity();
		}
		else if (!(((Entity) arg).getComponent(ComponentType.Location) == null)) {
			myGrid.drawEntity((Entity) arg);
		}
		else {
			myTab.addEntity((Entity) arg);
		}
		if (myData.getUserSelectedEntity() == null) {
			myTab.clearSelected();
		}
	}
}
