package voogasalad.util.paint;

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
		if (dtt.equals(DrawingToolType.Rectangle)) {
			dr.setColor(currentTool.getColor());
			currentTool = dr;
		} else if (dtt.equals(DrawingToolType.Cirlce)) {
			dc.setColor(currentTool.getColor());
			currentTool = dc;
		} else {
			p.setColor(currentTool.getColor());
			currentTool = p;
		}
	}

	@Override
	public DrawingTool getDrawingTool() {
		return currentTool;
	}

}
