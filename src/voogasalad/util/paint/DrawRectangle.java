package voogasalad.util.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class DrawRectangle extends DrawingTool {
	Rectangle dragBox = new Rectangle(0,0,0,0);
	
	@Override
	public DrawingToolType getDrawingToolType() {
		return DrawingToolType.Rectangle;
	}

	@Override
	public void mouseHandeler(MouseEvent e, GraphicsContext gc) {
		if(e.getEventType() == e.DRAG_DETECTED){
			dragBox.setFill(super.getColor());
			dragBox.setVisible(true);
			dragBox.setTranslateX(e.getX());
			dragBox.setTranslateY(e.getY());
		}

		if(e.getEventType() == e.MOUSE_DRAGGED && dragBox.isVisible()){
			dragBox.setWidth(e.getX() - dragBox.getTranslateX());
			dragBox.setHeight(e.getY() - dragBox.getTranslateY());
			
		}
		if(e.getEventType() == e.MOUSE_RELEASED){
			gc.setFill(super.getColor());
			gc.fillRect(dragBox.getTranslateX(),dragBox.getTranslateY(),dragBox.getWidth(),dragBox.getHeight());
			dragBox.setVisible(false);
		}		
	}

	public Shape getShape() {
		return dragBox;
	}

}
