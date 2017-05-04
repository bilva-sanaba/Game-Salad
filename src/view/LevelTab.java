package view;

import javafx.scene.control.Tab;

public class LevelTab extends Tab {
	private GridView myGrid;
	private int tabLevel;

	public LevelTab(GridView grid, int counter) {
		myGrid = grid;
		tabLevel = counter;
		this.setContent(grid.getContent());
		this.setLevelNumber(counter);
		this.setClosable(false);
	}

	public void setLevelNumber(int levelNumber) {
		this.setText(String.format("Level %d", levelNumber));
		tabLevel = levelNumber;
		myGrid.setLevelNumber(levelNumber);
	}
	
	public int getLevel(){
		return tabLevel;
	}

	public GridView getGrid(){
		return myGrid;
	}


}