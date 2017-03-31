package author_interfaces;

import javafx.scene.layout.GridPane;

public interface GridView {
	public void placeSprite(Sprite s);
	public GridPane createGridView(GridViewData gvd);
	public GridPane getGridPane();
}
