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

	public DrawingCanvas(DrawingTool pen) {
		c = new Canvas(1000, 1000);
		myPen = pen;
		gc = c.getGraphicsContext2D();
		gc.setFill(Color.WHITE);
		gc.fillRect(0, 0, c.getWidth(), c.getHeight());
		c.addEventHandler(MouseEvent.ANY, e -> myPen.mouseHandeler(e));
    }


	private void mouseHandeler(MouseEvent e) {
		if (myPen.getDrawingToolType().equals(DrawingToolType.Pen))
			if(e.getEventType() == MouseEvent.MOUSE_PRESSED){
				gc.setStroke(myPen.getColor());
		        gc.setLineWidth(1);
		        gc.beginPath();
		        gc.moveTo(e.getX(), e.getY());
		        gc.stroke();
			}
			if(e.getEventType() == MouseEvent.MOUSE_DRAGGED){
				gc.lineTo(e.getX(), e.getY());
		        gc.stroke();
			}
		}
	}
	
	private Rectangle createDragBox() {
		Rectangle dragBox = new Rectangle(0, 0, 0, 0);
		dragBox.setVisible(false);
		scene.addEventFilter(MouseEvent.ANY, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				if (mouseEvent.getEventType() == MouseEvent.MOUSE_CLICKED) {
					dragBox.setVisible(true);
					dragBox.setTranslateX(mouseEvent.getX());
					dragBox.setTranslateY(mouseEvent.getY());
				}
				if (mouseEvent.getEventType() == MouseEvent.MOUSE_MOVED && dragBox.isVisible()) {
					dragBox.setWidth(mouseEvent.getX() - dragBox.getTranslateX());
					dragBox.setHeight(mouseEvent.getY() - dragBox.getTranslateY());
				}
				if (mouseEvent.getEventType() == MouseEvent.MOUSE_RELEASED)
					dragBox.setVisible(false);
			}
		});
		return dragBox;
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