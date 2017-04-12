package gameView.displayComponents;

import java.awt.Dimension;

import gameObject.GameConfig;
import gameView.tools.DisplayEnum;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.geometry.Dimension2D;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.Region;

public abstract class UIDisplayComponent  {

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
	
	public Dimension2D getSize() {
		return new Dimension2D(getDisplay().getPrefWidth(), getDisplay().getPrefHeight());
	}
	
	protected GameConfig getConfig() {
		return gameConfig;
	}
	
	
	public abstract Region getDisplay();
	public abstract DisplayEnum getPos();
	protected abstract void setID();
	
}
