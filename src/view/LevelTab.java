package view;

import javafx.scene.control.Tab;

public class LevelTab extends Tab {
	private GridView myGrid;

	public LevelTab(GridView grid, int counter) {
		myGrid = grid;
		this.setContent(grid.getContent());
		this.setLevelNumber(counter);
		this.setClosable(false);
	}

	public void setLevelNumber(int levelNumber) {
		this.setText(String.format("Level %d", levelNumber));
	}

	public GridView getGrid(){
		return myGrid;
	}


}