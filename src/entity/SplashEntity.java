package entity;

public class SplashEntity extends Entity {

	private String displayName;
	private String instructions;
	private String backgroundFilePath;
	
	public SplashEntity(int id, String dn, String inst, String bfp) {
		super(id);
		displayName = dn;
		instructions = inst;
		backgroundFilePath = bfp;
	}
	
	public String getDisplayName() {
		return displayName;
	}
	public String getInstructions() {
		return instructions;
	}
	public String getBackgroundFilePath() {
		return backgroundFilePath;
	}
	public void setDisplayName(String dn) {
		displayName = dn;
	}
	public void setInstructions(String inst) {
		instructions = inst;
	}
	public void setBackgroundFilePath(String bfp) {
		backgroundFilePath = bfp;
	}
}
