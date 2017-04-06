package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;

public class UtilityFactory {
	
	public Tab buildTab(String label, Boolean closable){
		Tab myTab = new Tab();
		myTab.setText(label);
		myTab.setClosable(closable);
		return myTab;
	}
	
	public Button buildButton(String s, EventHandler<ActionEvent> e){
		Button myButton = new Button();
		myButton.setText(s);
		myButton.setOnAction(e);
		return myButton;
	}
	
	public MenuItem builtMenuItem(){
		MenuItem myMenuItem = new MenuItem();
		return myMenuItem;
	}

}
