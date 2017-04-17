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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class DrawingCanvas implements ICanvas{
    private Canvas c;
    private Scene scene;
    private Color currentColor;
    public DrawingCanvas(DrawingTool pen){
        BorderPane root = new BorderPane();
        ColorPicker t = new ColorPicker(Color.BLACK);
        
        root.setTop(t);
        c = new Canvas(1000,1000);
        GraphicsContext gc = c.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        t.setOnAction(e -> {currentColor = t.getValue();
                            gc.setFill(currentColor);
                            gc.fillRect(110, 110, 500, 500);}
                    );
        root.setCenter(c);
        gc.fillRect(110,110, 500, 500);
        scene  = new Scene(root,1000,1000);
        root.getChildren().add(createDragBox());
        Image image = new Image(getClass().getClassLoader().getResourceAsStream("dirt.jpg"));
        ImageView imageView = new ImageView(image);
        imageView.setX(250);
        imageView.setY(250);
        root.getChildren().add(imageView);
    }
    private Rectangle createDragBox(){
    Rectangle dragBox = new Rectangle(0, 0, 0, 0);
    dragBox.setVisible(false);
    scene.addEventFilter(MouseEvent.ANY, new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            if(mouseEvent.getEventType() == MouseEvent.MOUSE_CLICKED){
                dragBox.setVisible(true);
                dragBox.setTranslateX(mouseEvent.getX());
                dragBox.setTranslateY(mouseEvent.getY());
            }
            if(mouseEvent.getEventType() == MouseEvent.MOUSE_MOVED && dragBox.isVisible()){
                dragBox.setWidth(mouseEvent.getX() - dragBox.getTranslateX());
                dragBox.setHeight(mouseEvent.getY() - dragBox.getTranslateY());
            }
            if(mouseEvent.getEventType() == MouseEvent.MOUSE_RELEASED)
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
	public Image getImage() {
		// TODO Auto-generated method stub
		return null;
	}

}