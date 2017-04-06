package view.toolbar;

import javafx.scene.control.ToolBar;
import javafx.scene.layout.Region;
import view.GUIComponent;
import view.UtilityFactory;

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
