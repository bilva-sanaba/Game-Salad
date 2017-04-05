package view;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

public class GridView extends GUIComponent {
	GridPane myGrid;
	
	public GridView() {
		myGrid = new GridPane();
	}
	
	@Override
	public Region buildComponent() {
		return myGrid;
	}
}
