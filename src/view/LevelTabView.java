package view;

import java.util.ArrayList;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Region;

public class LevelTabView extends GUIComponent{
	private ArrayList<LevelTab> tabsList;
	private TabPane myTabs;
	
	public LevelTabView(GridView myGrid) {
		myTabs = new TabPane();
		LevelTab tab1 = new LevelTab(myGrid, 1);
		tabsList = new ArrayList<LevelTab>();
		tabsList.add(tab1);
		myTabs.getTabs().add(tab1);
		
	}
	
	public ArrayList<LevelTab> getTabsList(){
		return tabsList;
	}
	
	public void loadTab(ScrollPane grid, int counter) {
		
	}
	
	public void addNewTab(int counter) {
		
	}

	@Override
	public Region buildComponent() {
		return myTabs;
	}
}
