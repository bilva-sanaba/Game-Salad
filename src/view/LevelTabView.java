package view;

import java.util.ArrayList;
import java.util.HashMap;

import entity.Entity;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Region;

public class LevelTabView extends GUIComponent {
	private HashMap<Integer, LevelTab> tabsList;
	private TabPane myTabs;
	private ViewData myData;
	private ChangeListener<Tab> myListener;

	public LevelTabView(GridView myGrid, ViewData myDataIn) {
		
		
		myData = myDataIn;
		tabsList = new HashMap<Integer, LevelTab>();
		myTabs = new TabPane();
		
		myListener = new ChangeListener<Tab>(){
			@Override
			public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
				LevelTab temp = (LevelTab) newValue;
				myData.setCurrentLevel(temp.getLevel());
			}};
		
		myTabs.getSelectionModel().selectedItemProperty().addListener(myListener);
		
		addNewTab(myGrid, myData.getMaxLevel());
	}

	public void clearTabs() {
		myTabs.getSelectionModel().selectedItemProperty().removeListener(myListener);
		myTabs.getTabs().removeAll(tabsList.values());
		tabsList.clear();
		myTabs.getSelectionModel().selectedItemProperty().addListener(myListener);
	}
	
	public void selectTab(int tabIndex) {
		myTabs.getSelectionModel().select(tabIndex - 1);
	}

	public HashMap<Integer,LevelTab> getTabsList() {
		return tabsList;
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
