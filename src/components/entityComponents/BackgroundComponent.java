package components.entityComponents;

import components.IComponent;

public class BackgroundComponent implements IComponent {

	private String filePath;
	
	public BackgroundComponent(String fileP) {
		filePath = fileP;
	}

	@Override
	public ComponentType getComponentType() {
		return ComponentType.Background;
	}
	
	public String getFilePath() { 
		return filePath;
	}
	
	public void setFilePath(String fileP) {
		filePath = fileP;
	}

	@Override
	public IComponent newCopy() {
		return new BackgroundComponent(getFilePath());
	}
}
