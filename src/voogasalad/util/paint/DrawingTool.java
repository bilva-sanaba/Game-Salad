package voogasalad.util.paint;

import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

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

	public abstract void mouseHandeler(MouseEvent e);

}
