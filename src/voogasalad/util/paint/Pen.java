package voogasalad.util.paint;

import javafx.scene.paint.Color;

public class Pen extends DrawingTool{
	private double width;
	
	private static final double DEFAULTWIDTH = 5;
	
	public Pen() {
		super();
		width = DEFAULTWIDTH;
	}
	
	public Pen(double w, Color c) {
		super(c);
		width = w;
	}
	
	public double getWidth() {
		return width;
	}
	
	public void setWidth(double w) {
		width = w;
	}
	
	public DrawingToolType getDrawingToolType() {
		return DrawingToolType.Pen;
	}
}
