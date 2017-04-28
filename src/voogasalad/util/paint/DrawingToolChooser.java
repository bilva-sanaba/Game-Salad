package voogasalad.util.paint;

import javafx.scene.paint.Color;

public class DrawingToolChooser implements DrawingGet, IDrawingToolChooser {
	
	private DrawRectangle dr;
	private DrawCircle dc;
	private Pen p;
	
	private DrawingTool currentTool;
	
	public DrawingToolChooser() {
		dr = new DrawRectangle();
		dc = new DrawCircle();
		p = new Pen();
		currentTool = p;
	}

	@Override
	public void setDrawingTool(DrawingToolType dtt) {
		setColors(currentTool.getColor());
		if (dtt.equals(DrawingToolType.Rectangle)) {
			currentTool = dr;
		} else if (dtt.equals(DrawingToolType.Cirlce)) {
			currentTool = dc;
		} else {
			currentTool = p;
		}
	}
	
	private void setColors(Color c){
		dr.setColor(c);
		dc.setColor(c);
		p.setColor(c);
	}

	@Override
	public DrawingTool getDrawingTool() {
		return currentTool;
	}

	@Override
	public void setColor(Color value) {
		setColors(value);
	}

}
