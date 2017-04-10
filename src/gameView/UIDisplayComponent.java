package gameView;

import javafx.scene.layout.Region;

public abstract class UIDisplayComponent {

	private String myDisplayName;
	
	public UIDisplayComponent(String name) {
		myDisplayName = name;
	}
	
	public String getName() {
		return myDisplayName;
	}
	
	public abstract Region getDisplay();
	
}
