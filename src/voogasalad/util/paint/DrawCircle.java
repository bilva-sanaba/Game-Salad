package voogasalad.util.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class DrawCircle extends DrawingTool {
Circle dragBox = new Circle(0, 0, 0);
	
	@Override
	public DrawingToolType getDrawingToolType() {
		return DrawingToolType.Rectangle;
	}

	@Override
	public void mouseHandeler(MouseEvent e, GraphicsContext gc) {
		if(e.getEventType() == MouseEvent.DRAG_DETECTED){
			dragBox.setFill(super.getColor());
			dragBox.setVisible(true);
			dragBox.setTranslateX(e.getX());
			dragBox.setTranslateY(e.getY());
		}

		if(e.getEventType() == MouseEvent.MOUSE_DRAGGED && dragBox.isVisible()){
			dragBox.setRadius(e.getX() - dragBox.getTranslateX());			
		}
		if(e.getEventType() == MouseEvent.MOUSE_RELEASED){
			gc.setFill(super.getColor());
			gc.fillOval(dragBox.getTranslateX()-dragBox.getRadius(),dragBox.getTranslateY()-dragBox.getRadius(),dragBox.getRadius()*2,dragBox.getRadius()*2);
			dragBox.setVisible(false);
		}
		
	}

	public Shape getShape() {
		return dragBox;
	}


}
