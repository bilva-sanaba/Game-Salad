package view;

import javafx.scene.control.ToolBar;
import javafx.scene.layout.Region;

public class ToolBarView extends GUIComponent{
	private ToolBar myBar;
	
	public ToolBarView(UtilityFactory utilF){
		myBar = new ToolBar();
	}
	@Override
	public Region buildComponent() {
		return myBar;
	}

}
