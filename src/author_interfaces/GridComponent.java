package author_interfaces;

import gameEngine_interface.Entity;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class GridComponent implements GridView{
	private GridPane myGrid = new GridPane();

	public GridComponent(){
		
	}
	
	@Override
	public void build(Pane root) {
		// TODO Auto-generated method stub
		root.getChildren().add(myGrid);
	}

	@Override
	public void placeSprite(Entity s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public GridPane createGridView(GridViewData gvd) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GridPane getGridPane() {
		// TODO Auto-generated method stub
		return null;
	}

}
