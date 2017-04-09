package view.toolbar;

import javafx.scene.control.ToolBar;
import javafx.scene.layout.Region;
import view.GUIComponent;
import view.UtilityFactory;
import view.ViewData;

public class ToolBarView extends GUIComponent{
	private ToolBar myBar;
	private ViewData myData;
	
	public ToolBarView(UtilityFactory utilF, ViewData data) {
		myData = data;
		myBar = new ToolBar();
		fillBar(utilF);
	}
	
	private void fillBar(UtilityFactory utilF) {
		myBar.getItems().addAll(
				utilF.buildButton("Load", ""),
				utilF.buildButton("Save", ""),
				utilF.buildButton("KeyAndMouse", ""),
				utilF.buildButton("Info", ""),
				utilF.buildButton("Level", ""),
				utilF.buildButton("Hero", ""),
				utilF.buildButton("Collisions", ""),
				utilF.buildButton("PowerUp", ""),
				utilF.buildButton("Trash", "")
				);
	}
	
	@Override
	public Region buildComponent() {
		return myBar;
	}

}
