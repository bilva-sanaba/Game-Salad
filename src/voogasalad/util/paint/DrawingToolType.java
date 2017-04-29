package voogasalad.util.paint;

import javafx.scene.input.MouseEvent;

public enum DrawingToolType {

	Pen(),
	Rectangle(),
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
