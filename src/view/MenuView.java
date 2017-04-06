package view;


import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

public class MenuView extends GUIComponent{
	private MenuBar myMenu;
	
	public MenuView(){
		myMenu = new MenuBar(new Menu("Temporary"));
		GridPane.setConstraints(myMenu, 0, 0);
	}
	
	public Region buildComponent(){
		Region myRegion = myMenu;
		return myRegion;
	}

}
