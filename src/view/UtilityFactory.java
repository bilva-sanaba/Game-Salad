package view;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;

public class UtilityFactory {
	
	private ResourceBundle myResources;
	
	public UtilityFactory(String language){
		myResources = ResourceBundle.getBundle(language);
	}
	
	public Tab buildTab(){
		Tab myTab = new Tab();
		return myTab;
	}
	
	public Button buildButton(){
		Button myButton = new Button();
		return myButton;
	}
	
	public MenuItem builtMenuItem(String name, EventHandler<ActionEvent> event){
		MenuItem myMenuItem = new MenuItem(name);
		myMenuItem.setOnAction(event);
		return myMenuItem;
	}

}
