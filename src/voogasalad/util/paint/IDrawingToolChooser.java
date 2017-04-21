package voogasalad.util.paint;

import javafx.scene.paint.Color;

public interface IDrawingToolChooser {
	public DrawingTool getDrawingTool();
	public void setDrawingTool(DrawingToolType dtt);
	public void setColor(Color value);
}
