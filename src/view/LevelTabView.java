package view;

import java.util.ArrayList;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Region;

public class LevelTabView extends GUIComponent{
	private ArrayList<LevelTab> tabsList;
	private TabPane myTabs;
	private int currentLevel;
	
	public LevelTabView(GridView myGrid, ViewData myData) {
		currentLevel = 1;
		tabsList = new ArrayList<LevelTab>();
		myTabs = new TabPane();
		myTabs.getSelectionModel().selectedItemProperty().addListener((obs,oldTab,newTab)->{
            String temp = newTab.getText();
            myData.setCurrentLevel(Integer.parseInt(temp.substring(temp.length()-1)));
            System.out.println("switched tabs");
        });
		addNewTab(myGrid);
	}
	
	public ArrayList<LevelTab> getTabsList(){
		return tabsList;
	}
	
	public void loadTab(ScrollPane grid, int counter) {
		
	}
	
	public void addNewTab(GridView myGrid) {
		LevelTab tab1 = new LevelTab(myGrid, currentLevel);
		currentLevel++;
		tabsList.add(tab1);
		myTabs.getTabs().add(tab1);
	}

	@Override
	public Region buildComponent() {
		return myTabs;
	}
}
