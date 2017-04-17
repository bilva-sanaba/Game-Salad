package voogasalad.util.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

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

	@Override
	public void mouseHandeler(MouseEvent e, GraphicsContext gc) {
		if(e.getEventType() == MouseEvent.MOUSE_PRESSED){
			gc.setStroke(super.getColor());
	        gc.setLineWidth(width);
	        gc.beginPath();
	        gc.moveTo(e.getX(), e.getY());
	        gc.stroke();
		}
		if(e.getEventType() == MouseEvent.MOUSE_DRAGGED){
			gc.lineTo(e.getX(), e.getY());
	        gc.stroke();
		}
	}

	@Override
	public Shape getShape() {
		return null;
	}

}
