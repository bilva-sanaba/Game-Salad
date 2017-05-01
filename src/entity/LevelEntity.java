package entity;

import data_interfaces.InfiniteEnum;

public class LevelEntity extends Entity {
	
	private int rows;
	private int cols;
	private String backgroundFilePath;
	private String openingMusic;
	private int lives;
	private InfiniteEnum ie;

	public LevelEntity(int id) {
		super(id);
	}

	public LevelEntity(int id, int r, int c, String bfp, String music, int l) {
		super(id);
		rows = r;
		cols = c;
		backgroundFilePath = bfp;
		openingMusic = music;
		lives=l;
		ie = InfiniteEnum.None;
	}
	
	public String getMusic(){
		return openingMusic;
	}
	
	public int getLives(){
		return lives; 
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
