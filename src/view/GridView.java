package view;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

/**
 * @author Justin Yang
 *
 */
public class GridView extends GUIComponent {
	private GridPane myGrid;
	private ViewData myData;
	
	public GridView(UtilityFactory utilF, ViewData data, int rows, int cols) {
		myData = data;
		myGrid = new GridPane();
		myGrid.getStyleClass().add("view-grid");
		myGrid.setAlignment(Pos.CENTER);
		
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				addMouseListenerPane(row, col);
			}
		}
	}
	
	private void addMouseListenerPane(int row, int col) {
		Rectangle rect = new Rectangle(40, 40);
		rect.getStyleClass().add("view-grid-cell");
		rect.setFill(Color.GREY);
		rect.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				System.out.println(String.format("Click at row %d col %d", row, col));
				if (myData.getUserSelectedEntity() != null) {
					
				}
			}
		});
		myGrid.add(rect, row, col);
	}
	
//	public void loadData(ViewData data) {
//		myLevel = level;
//		myLevel.addObserver(this);
//	}
//	
//	public void addLevelEntity(Entity entity) {
//		myLevel.add(entity);
//	}
	
	@Override
	public Region buildComponent() {
		return myGrid;
	}
}
