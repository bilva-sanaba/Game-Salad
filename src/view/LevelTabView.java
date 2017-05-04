package view;

import java.util.ArrayList;
import java.util.HashMap;

import entity.Entity;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Region;

public class LevelTabView extends GUIComponent {
	private HashMap<Integer, LevelTab> tabsList;
	private TabPane myTabs;
	private ViewData myData;

	public LevelTabView(GridView myGrid, ViewData myDataIn) {
		myData = myDataIn;
		tabsList = new HashMap<Integer, LevelTab>();
		myTabs = new TabPane();
		myTabs.getSelectionModel().selectedItemProperty().addListener((obs,oldTab,newTab) -> {
			LevelTab temp = (LevelTab) newTab;
			myData.setCurrentLevel(temp.getLevel());
		});
		
		addNewTab(myGrid, myData.getMaxLevel());
	}

	public void clearTabs() {
		myTabs.getSelectionModel().clearSelection();
		System.out.println("pre error");
		myTabs.getTabs().removeAll(tabsList.values());
		System.out.println("post error");
		tabsList.clear();
	}
	
	public void selectTab(int tabIndex) {
		myTabs.getSelectionModel().select(tabIndex - 1);
	}

	public HashMap<Integer,LevelTab> getTabsList() {
		return tabsList;
	}

	public void loadTab(ScrollPane grid, int counter) {

	}

	public void addNewTab(GridView myGrid, int level) {
		LevelTab tab1 = new LevelTab(myGrid, level);
		tabsList.put(level,tab1);
		myTabs.getTabs().add(tab1);
		myTabs.getSelectionModel().select(tab1);
	}

	public void removeTab(int level) {
		LevelTab tab = tabsList.get(level);
		for(int i = level; i < tabsList.size(); i++) {
			tabsList.put(i, tabsList.get(i+1));
			tabsList.get(i).setLevelNumber(i);
		}
		myTabs.getTabs().remove(tab);
		tabsList.remove(tabsList.size());
		myTabs.getSelectionModel().select(level-1);
	}

	/*public void moveTab(int level, int destination) {
		LevelTab swapper = tabsList.get(destination);
		swapper.setLevelNumber(level);
		LevelTab current = tabsList.get(level);
		current.setLevelNumber(destination);
		tabsList.put(destination, current);
		tabsList.put(level, swapper); 
		myTabs.getSelectionModel().select(destination);
	}*/

	@Override
	public Region buildComponent() {
		return myTabs;
	}
}
