package voogasalad.util.paint;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class DrawingCanvas implements ICanvas {
	private Canvas c;
	private Scene scene;
	private DrawingTool myPen;
	private GraphicsContext gc;

	public DrawingCanvas(DrawingTool pen, Group root) {
		c = new Canvas(1000, 1000);
		myPen = pen;
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
		// TODO Auto-generated method stub

	}

}