package components.entityComponents;

import components.IComponent;

public class SplashComponent implements IComponent {

	private String title;
	private String rgb;
	
	public SplashComponent(String t, String rgbVals) {
		title = t;
		rgb = rgbVals;
	}
	
	@Override
	public ComponentType getComponentType() {
		return ComponentType.Splash;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getRGB() {
		return rgb;
	}
	
	public void setTitle(String t) {
		title = t;
	}
	
	public void setRGB(String rgbVals) {
		rgb = rgbVals;
	}

	@Override
	public IComponent newCopy() {
		return new SplashComponent(getTitle(), getRGB());
	}

}
