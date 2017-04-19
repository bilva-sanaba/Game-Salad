package utility;


import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import voogasalad.util.paint.ImageRefiner;

public class DrawingCanvas {
	protected static final double MOUSE_CORRECTION_FACTOR = 25;
	private Canvas c;
	private Scene scene;
	private Color currentColor= Color.BLACK;
	private GraphicsContext gc;
	private Button currentEvent; 
	private EventHandler<MouseEvent> t;

	public DrawingCanvas(Stage m){
	
		BorderPane root = new BorderPane();
		HBox test = new HBox();
		ColorPicker t = new ColorPicker(Color.BLACK);
		test.getChildren().add(t);
		root.setTop(test);
		currentEvent= new Button("Switch");
		test.getChildren().add(currentEvent);
		c = new Canvas(1000,1000);
		gc = c.getGraphicsContext2D();
		gc.setFill(Color.BLACK);
		t.setOnAction(e -> currentColor = t.getValue());
		gc.setFill(currentColor);
		gc.fillRect(110, 110, 500, 500);
		root.setCenter(c);
		scene  = new Scene(root,1000,1000);
		root.getChildren().add(createDragBox());
		Image image = new Image(getClass().getClassLoader().getResourceAsStream("platform_tile_053.png"),500,500,false,false);
		//Image image = new Image(getClass().getClassLoader().getResourceAsStream("dirt.jpg"),500,500,false,false);
		ImageRefiner r = new ImageRefiner();
		
		Shape s = r.getBoundedShape(image);
		//s.setTranslateX(r.getXTransparentOffset(image));
		//gc.drawImage(image, 110, 110);
		Image  i2 = r.turnAllWhiteTransparent(image);
		ImageView i = new ImageView(i2);
		
		s.setFill(Color.BLUE);
		root.getChildren().add(s);
		root.getChildren().add(i);
		m.setScene(scene);
		m.show();
	}

	private Shape createDragBox(){
		Rectangle dragBox = new Rectangle(0,0,0,0);
		scene.addEventFilter(MouseEvent.ANY, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				if(mouseEvent.getEventType() == MouseEvent.DRAG_DETECTED){
					dragBox.setFill(currentColor);
					dragBox.setVisible(true);
					dragBox.setTranslateX(mouseEvent.getX());
					dragBox.setTranslateY(mouseEvent.getY());
				}
				if(mouseEvent.getEventType() == MouseEvent.MOUSE_DRAGGED && dragBox.isVisible()){
					dragBox.setWidth(mouseEvent.getX() - dragBox.getTranslateX());
					dragBox.setHeight(mouseEvent.getY() - dragBox.getTranslateY());	
				}
				if(mouseEvent.getEventType() == MouseEvent.MOUSE_RELEASED){
					gc.setFill(currentColor);
					gc.fillRect(dragBox.getTranslateX(),dragBox.getTranslateY()-MOUSE_CORRECTION_FACTOR,dragBox.getWidth(),dragBox.getHeight());
					dragBox.setVisible(false);
				}
			}
		});
		return dragBox;
	}
}
