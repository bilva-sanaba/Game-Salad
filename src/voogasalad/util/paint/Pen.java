package voogasalad.util.paint;

import javafx.scene.paint.Color;

public class Pen {
	private double width;
	private Color color;
	
	private static final double DEFAULTWIDTH = 5;
	private static final Color DEFAULTCOLOR = Color.BLACK;
	
	public Pen() {
		width = DEFAULTWIDTH;
		color = DEFAULTCOLOR;
	}
	
	public Pen(double w, Color c) {
		width = w;
		color = c;
	}
	
	public Color getColor() {
		return color;
	}
	
	public double getWidth() {
		return width;
	}
	
	public void setColor(Color c) {
		color = c;
	}
	
	public void setWidth(double w) {
		width = w;
	}
}
