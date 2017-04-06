package view.toolbar;

import javafx.scene.control.ToolBar;
import javafx.scene.layout.Region;
import view.GUIComponent;
import view.UtilityFactory;

public class ToolBarView extends GUIComponent{
	private ToolBar myBar;
	
	public ToolBarView(UtilityFactory utilF){
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
