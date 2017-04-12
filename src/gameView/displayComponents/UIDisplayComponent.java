package gameView.displayComponents;

import java.awt.Dimension;

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
	
	public UIDisplayComponent(String name) {
		myDisplayName = name;
		setID();
	}
	
	public String getName() {
		return myDisplayName;
	}
	
	public Dimension2D getSize() {
		return new Dimension2D(getDisplay().getPrefWidth(), getDisplay().getPrefHeight());
	}
	
	public abstract Region getDisplay();
	public abstract DisplayEnum getPos();
	protected abstract void setID();
	
}
