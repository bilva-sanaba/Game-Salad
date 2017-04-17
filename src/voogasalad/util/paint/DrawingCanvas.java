package voogasalad.util.paint;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;


public class DrawingCanvas implements ICanvas {
	private Canvas c;
	private DrawingTool myPen;
	private GraphicsContext gc;

	public DrawingCanvas(GetDrawingTool pen, Group root) {
		c = new Canvas(1000, 1000);
		myPen = pen.getDrawingTool();
		gc = c.getGraphicsContext2D();
		gc.setFill(Color.WHITE);
		gc.fillRect(0, 0, c.getWidth(), c.getHeight());
		if (myPen.getShape() != null) {
			root.getChildren().add(myPen.getShape());
		}
		c.addEventHandler(MouseEvent.ANY, e -> myPen.mouseHandeler(e, gc));
	}

	@Override
	public GraphicsContext getGraphicsContext2D() {
		return c.getGraphicsContext2D();
	}

	@Override
	public double getHeight() {
		return c.getHeight();
	}

	@Override
	public double getWidth() {
		return c.getWidth();
	}

	@Override
	public Node getRegion() {
		return c;
	}

	@Override
	public void snapshot(WritableImage wi) {
		c.snapshot(null, wi);
	}

}