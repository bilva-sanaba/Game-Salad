package entity;

import data_interfaces.InfiniteEnum;

public class LevelEntity extends Entity {
	
	private int rows;
	private int cols;
	private String backgroundFilePath;
	private InfiniteEnum ie;

	public LevelEntity(int id) {
		super(id);
	}

	public LevelEntity(int id, int r, int c, String bfp) {
		super(id);
		rows = r;
		cols = c;
		backgroundFilePath = bfp;
		ie = InfiniteEnum.None;
	}
	
	public int getRows() {
		return rows;
	}
	
	public int getCols() {
		return cols;
	}
	
	public String getBackgroundFilePath() {
		return backgroundFilePath;
	}
	
	public InfiniteEnum getInfiniteEnum() {
		return ie;
	}
	
	public void addRow() {
		rows++;
	}
	
	public void addCol() {
		cols++;
	}
	
	public void setBackgroundFilePath(String fileP) {
		backgroundFilePath = fileP;
	}
	
	public void setInfiniteEnum(InfiniteEnum ie2) {
		ie = ie2;
	}
}
