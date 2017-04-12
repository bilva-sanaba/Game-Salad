package entity;

public class LevelEntity extends Entity {
	
	private int rows;
	private int cols;
	private String backgroundFilePath;

	public LevelEntity(int id) {
		super(id);
	}

	public LevelEntity(int id, int r, int c, String bfp) {
		super(id);
		rows = r;
		cols = c;
		backgroundFilePath = bfp;
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
	
	public void addRow() {
		rows++;
	}
	
	public void addCol() {
		cols++;
	}
	
	public void setBackgroundFilePath(String fileP) {
		backgroundFilePath = fileP;
	}
}
