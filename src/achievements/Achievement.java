package achievements;

import components.entityComponents.LocationComponent;
import javafx.beans.binding.DoubleBinding;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class Achievement implements IAchievement {
	private final String ACHIEVEMENTUNLOCKED = "Achievement Unlocked: ";
	private final int PADDING = 20;
	private final int WIDTH = 2;
	private final int HEIGHT = 4;
	private double xCorrection = 50;
	private final double yCORRECTION = 350;
	private LocationComponent myLocation;
	private Scene myFrame;
	private String message;
	
	public Achievement(String message){
		this.message = message;
	}
	
	public Group execute() {
		return createEllipse();
	}
	
	private Group createEllipse(){
		Group group = new Group();
		
		Label t = new Label(ACHIEVEMENTUNLOCKED+message);
		Ellipse ellipse = new Ellipse();
		
		DoubleBinding halfWidth = t.widthProperty().divide(WIDTH).add(PADDING);
		DoubleBinding halfHeight = t.heightProperty().divide(HEIGHT).add(PADDING);
		DoubleBinding xLoc = halfWidth.add(xCorrection);
		DoubleBinding yLoc = halfHeight.add(yCORRECTION);
		
		ellipse.radiusXProperty().bind(halfWidth);
		ellipse.radiusYProperty().bind(halfHeight);
		ellipse.centerXProperty().bind(xLoc);
		ellipse.centerYProperty().bind(yLoc);
		ellipse.setFill(Color.LIGHTGREEN);
		
		t.layoutXProperty().bind(xLoc.subtract(t.widthProperty().divide(2)));
		t.layoutYProperty().bind(yLoc.subtract(t.heightProperty().divide(2)));
		
		group.getChildren().addAll(ellipse, t);
		return group;
		
	}
	
}
