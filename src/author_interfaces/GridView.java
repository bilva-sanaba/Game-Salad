package author_interfaces;

import author_interfaces.GUIComponent;
import gameEngine_interface.Entity;
import javafx.scene.layout.GridPane;

public interface GridView extends GUIComponent {
	public void placeSprite(Entity s);
	public GridPane createGridView(GridViewData gvd);
	public GridPane getGridPane();
}
