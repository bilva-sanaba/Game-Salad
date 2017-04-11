package gameView.displayComponents;

import javafx.geometry.Pos;
import javafx.scene.layout.Region;

public abstract class UIDisplayComponent extends Region {

	private String myDisplayName;
	
	public UIDisplayComponent(String name) {
		myDisplayName = name;
		setID();
	}
	
	public String getName() {
		return myDisplayName;
	}
	
	public abstract Region getDisplay();
	public abstract Pos getPos();
	protected abstract void setID();
	
}
