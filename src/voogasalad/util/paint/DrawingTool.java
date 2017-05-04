package voogasalad.util.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public abstract class DrawingTool {
	private Color color;
	
	private static final Color DEFAULTCOLOR = Color.BLACK;
	
	public DrawingTool() {
		color = DEFAULTCOLOR;
	}
	
	public DrawingTool(Color c) {
		color = c;
	}
	
	public Color getColor() {
		return color;
	}
	

	public void setColor(Color c) {
		color = c;
	}
	
	public abstract DrawingToolType getDrawingToolType();

	public abstract void mouseHandeler(MouseEvent e, GraphicsContext gc);

	public abstract Shape getShape();

}
