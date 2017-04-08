package view;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

import java.util.Observable;
import java.util.Observer;

import entity.Entity;
import entity.Level;

/**
 * @author Justin Yang
 *
 */
public class GridView extends GUIComponent implements Observer {
	private Level myLevel;
	private GridPane myGrid;
	
	public GridView(UtilityFactory utilF, int rows, int cols) {
		myGrid = new GridPane();
		myGrid.getStyleClass().add("view-grid");
		myGrid.setAlignment(Pos.CENTER);
		
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				addMouseListenerPane(row, col);
			}
		}
		
		myLevel = new Level();
		myLevel.addObserver(this);
	}
	
	private void addMouseListenerPane(int row, int col) {
		Rectangle rect = new Rectangle(40, 40);
		rect.getStyleClass().add("view-grid-cell");
		rect.setFill(Color.GREY);
		rect.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				System.out.println(String.format("Click at row %d col %d", row, col));
				//Add entity from mouse
				addLevelEntity(new Entity(19));
			}
		});
		myGrid.add(rect, row, col);
	}
	
	public void loadLevel(Level level) {
		myLevel = level;
		myLevel.addObserver(this);
	}
	
	public void addLevelEntity(Entity entity) {
		myLevel.add(entity);
	}
	
	@Override
	public Region buildComponent() {
		return myGrid;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		System.out.println("so triggered");
	}
}
