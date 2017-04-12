package gameView.displayComponents;

import gameObject.GameConfig;
import javafx.geometry.Pos;
import javafx.scene.layout.Region;

public abstract class UIDisplayComponent extends Region {

	private String myDisplayName;
	protected GameConfig gameConfig;
	
	public UIDisplayComponent(String name) {
		myDisplayName = name;
		gameConfig = new GameConfig(1, 2);
		setID();
	}
	
	public String getName() {
		return myDisplayName;
	}
	
	public GameConfig getConfig(){
		return gameConfig;
	}
	
	public abstract Region getDisplay();
	public abstract Pos getPos();
	protected abstract void setID();
	
}
