package controller;


import java.util.ArrayList;
import java.util.Collection;

import components.entityComponents.ComponentType;
import components.entityComponents.EntityType;
import components.entityComponents.LocationComponent;
import components.entityComponents.TypeComponent;
import entity.Entity;
import entity.IEntity;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

/*
 * @author Belal Taher
 */

public class Camera extends Entity {
	
	public static final int RIGHT_BOUND_FROM_EDGE = 500; //lower is closer to right edge
	public static final int LEFT_BOUND_FROM_EDGE = 20; //lower is closer to left edge
	
	private int myLevelLength;
	private Scene myFrame;
	private Group root;
	private LocationComponent followerLoc;
	private Rectangle background;
	
	
	
	
	public Camera (int length, Scene myScene, LocationComponent component, int id) {
		super(id);
		myLevelLength = length;
		myFrame = myScene;
		root = (Group) myFrame.getRoot();
		followerLoc = component;
		if (getComponent(ComponentType.Type) == null) {
			addComponent(new TypeComponent(EntityType.Camera));
		}

		background = new Rectangle();
		background.setFill(new ImagePattern(new Image(getClass().getClassLoader().getResource("background1.png").toString())));
		System.out.println(root.getLayoutX() + " is layout x");

		background.setLayoutX(-root.getTranslateX() -1000);
		

		background.setLayoutY(-root.getTranslateY() -200);
		background.setWidth(myFrame.getWidth());
		background.setHeight(myFrame.getHeight()/8);
		
		root.getChildren().add(background);
		System.out.println(root.getLayoutX() + " is layout x");
		root.setLayoutX(0.0);

		
	}

	public void updateCamera() {
		root.setLayoutX(0.0);
		
		double d = -followerLoc.getX()+myFrame.getWidth()/2;
		
	
		//System.out.println(d);
		
//		root.getChildren().remove(root.getChildren().size()-1);
		//System.out.println(myFrame.getWidth());
		root.setTranslateX(d);
		//root.setTranslateX(d);
		System.out.println(root.getLayoutX() + " " + d + " " + root.getTranslateX());
		//
		background.setX(followerLoc.getX());
		
		
		//background.setY(-1*root.getTranslateY() -200);
		//Collection<Node> child = new ArrayList<Node>();
		
		
		
//		if(( (myFrame.getWidth() - root.getTranslateX()) - (followerLoc.getX() ) <= RIGHT_BOUND_FROM_EDGE) && myFrame.getWidth()-root.getTranslateX() < myLevelLength){
//
//			root.setTranslateX(-followerLoc.getX()+myFrame.getWidth()/2);
//		}
//		else if( followerLoc.getX() + root.getTranslateX() < LEFT_BOUND_FROM_EDGE && root.getTranslateX() < 0){
//			root.setTranslateX(-followerLoc.getX()+myFrame.getWidth()/2);
//
//		}
//		else{
//			root.setTranslateX(root.getTranslateX());
//		}
	}
	
	
	public boolean withinCameraBounds(IEntity e) {
		LocationComponent loc = (LocationComponent) e.getComponent(ComponentType.Location);
		return loc != null && loc.getX() >= getX() && loc.getX()<getX()+getWidth();
	}
	
	public double getX() {
		return root.getTranslateX();
	}
	
	public double getWidth() {
		return 480;
	}
	
	public double getY() {
		return 0.0;
	}
	
	public double getHeight() {
		return 0.0;
	}
}
