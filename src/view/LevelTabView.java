package view;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Region;

public class LevelTabView extends GUIComponent{
	private HashMap<Integer, LevelTab> tabsList;
	private TabPane myTabs;
	private ViewData myData;
	
	public LevelTabView(GridView myGrid, ViewData myDataIn) {
		myData = myDataIn;
		tabsList = new HashMap<Integer, LevelTab>();
		myTabs = new TabPane();
		myTabs.getSelectionModel().selectedItemProperty().addListener((obs,oldTab,newTab)->{
            String temp = newTab.getText();
            myData.setCurrentLevel(Integer.parseInt(temp.substring(temp.length()-1)));
            System.out.println("switched tabs to" + temp + "and the highest level is" + myData.getMaxLevel());
        });
		addNewTab(myGrid);
	}
	
	public void clearTabs(){
		myTabs.getTabs().clear();
		tabsList.clear();
	}
	
	public HashMap<Integer,LevelTab> getTabsList(){
		return tabsList;
	}
	
	public void loadTab(ScrollPane grid, int counter) {
		
	}
	
	public void addNewTab(GridView myGrid) {
		LevelTab tab1 = new LevelTab(myGrid, myData.getMaxLevel());
		tabsList.put(myData.getMaxLevel(),tab1);
		myTabs.getTabs().add(tab1);
		myTabs.getSelectionModel().select(tab1);
	}
	
	public void removeTab(int level){
		LevelTab tab = tabsList.get(level);
		for(int i = level; i < tabsList.size(); i++){
			tabsList.put(i, tabsList.get(i+1));
			tabsList.get(i).setLevelNumber(i);
		}
		myTabs.getTabs().remove(tab);
		tabsList.remove(tabsList.size());
		myTabs.getSelectionModel().select(level-1);
		System.out.println("switching to tab" + (level-1));
		//myData.setCurrentLevel(level-1);
	}

	@Override
	public Region buildComponent() {
		return myTabs;
	}
}
