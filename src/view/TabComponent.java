package view;

import javafx.scene.control.TabPane;
import javafx.scene.layout.Region;

public class TabComponent extends GUIcomponent{
	Region myRegion = new TabPane();
	
	public Region buildComponent(){
		return myRegion;
	}
	
}
