package components;

public class LevelComponent implements IComponent {

	private int level;
	
	public LevelComponent(int l) {
		level = l;
	}
	
	@Override
	public ComponentType getComponentType() {
		return ComponentType.Level;
	}

	@Override
	public IComponent newCopy() {
		return new LevelComponent(getLevel());
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int l) {
		level = l;
	}

}
