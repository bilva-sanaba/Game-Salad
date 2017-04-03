package author_interfaces;

import author_interfaces.GUIComponent;
import gameEngine_interface.Sprite;
import javafx.scene.layout.GridPane;

public interface GridView extends GUIComponent {
	public void placeSprite(Sprite s);
	public GridPane createGridView(GridViewData gvd);
	public GridPane getGridPane();
}
