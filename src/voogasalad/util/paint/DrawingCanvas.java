package voogasalad.util.paint;

import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;


public class DrawingCanvas implements ICanvas {
	private Canvas c;
	private DrawingTool myPen;
	private GraphicsContext gc;

	public DrawingCanvas(DrawingGet pen, BorderPane root) {
		c = new Canvas(600, 600);
		myPen = pen.getDrawingTool();
		gc = c.getGraphicsContext2D();
		gc.setFill(Color.WHITE);
		gc.fillRect(0, 0, c.getWidth(), c.getHeight());
		if (myPen.getShape() != null) {
			root.getChildren().add(myPen.getShape());
		}
		c.setOnMouseEntered(e -> checkPen(pen));
		c.addEventHandler(MouseEvent.ANY, e -> myPen.mouseHandeler(e, gc));
	}

	private void checkPen(DrawingGet pen) {
		myPen = pen.getDrawingTool();
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