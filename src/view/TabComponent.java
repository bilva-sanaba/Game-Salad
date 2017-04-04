package view;

import javafx.scene.control.TabPane;
import javafx.scene.layout.Region;

public class TabComponent extends GUIComponent{
	Region myRegion = new TabPane();
	
	public Region buildComponent(){
		return myRegion;
	}
	
}
