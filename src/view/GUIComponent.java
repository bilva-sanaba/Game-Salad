package view;

import javafx.scene.layout.Region;

public abstract class GUIComponent {
	private ViewData myData = new ViewData();
	
	public abstract Region buildComponent();
	
	protected ViewData getData() {
		return myData;
	}
}