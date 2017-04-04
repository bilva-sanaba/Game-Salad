package view;

import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;

public class UtilityFactory {
	
	public Tab buildTab(){
		Tab myTab = new Tab();
		return myTab;
	}
	
	public Button buildButton(){
		Button myButton = new Button();
		return myButton;
	}
	
	public MenuItem builtMenuItem(){
		MenuItem myMenuItem = new MenuItem();
		return myMenuItem;
	}

}
