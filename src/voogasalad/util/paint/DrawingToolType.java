package voogasalad.util.paint;

import components.entityComponents.IKeyExpression;
import javafx.scene.input.MouseEvent;

public enum DrawingToolType {

	Pen(/*(e) -> {
		if (e.getEventType() == MouseEvent.MOUSE_PRESSED) {
			gc.setStroke(myPen.getColor());
			gc.setLineWidth(1);
			gc.beginPath();
			gc.moveTo(e.getX(), e.getY());
			gc.stroke();
		}
		if (e.getEventType() == MouseEvent.MOUSE_DRAGGED) {
			gc.lineTo(e.getX(), e.getY());
			gc.stroke();
		}
	}*/)
	,Rectangle(),
	 Cirlce();

	private MouseEvent mouseEvent;

	DrawingToolType() {
		
	}
	
	DrawingToolType(MouseEvent e) {
		this.mouseEvent = e;
	}

	public MouseEvent getMouseEvent() {
		return mouseEvent;
	}
}
