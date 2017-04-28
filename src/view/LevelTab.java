package view;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;

public class LevelTab extends Tab {
	private ScrollPane myGrid;
	private int myLevelNumber;
	
	public LevelTab(ScrollPane grid, int counter) {
		myGrid = grid;
		this.setContent(myGrid);
		this.setLevelNumber(counter);
	}
	
	public void setLevelNumber(int levelNumber) {
		myLevelNumber = levelNumber;
		this.setText(String.format("Level %d", myLevelNumber));
	}
}