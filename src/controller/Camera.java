package controller;


import components.entityComponents.ComponentType;
import components.entityComponents.EntityType;
import components.entityComponents.LocationComponent;
import components.entityComponents.TypeComponent;
import entity.Entity;
import entity.IEntity;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;

/*
 * @author Belal Taher
 */

public class Camera extends Entity {
	
	public static final int RIGHT_BOUND_FROM_EDGE = 500; //lower is closer to right edge
	public static final int LEFT_BOUND_FROM_EDGE = 20; //lower is closer to left edge
	
	private int myLevelLength;
	private Scene myFrame;
	private Parent root;
	private LocationComponent followerLoc;
	
	
	
	
	public Camera (int length, Scene myScene, LocationComponent component, int id) {
		super(id);
		myLevelLength = length;
		myFrame = myScene;
		root = myFrame.getRoot();
		followerLoc = component;
		if (getComponent(ComponentType.Type) == null) {
			addComponent(new TypeComponent(EntityType.Camera));
		}
		
	}

	public void updateCamera() {
		if(( (myFrame.getWidth() - root.getTranslateX()) - (followerLoc.getX() ) <= RIGHT_BOUND_FROM_EDGE) && myFrame.getWidth()-root.getTranslateX() < myLevelLength){

			root.setTranslateX(-followerLoc.getX()+myFrame.getWidth()/2);
		}
		else if( followerLoc.getX() + root.getTranslateX() < LEFT_BOUND_FROM_EDGE && root.getTranslateX() < 0){
			root.setTranslateX(-followerLoc.getX()+myFrame.getWidth()/2);

		}
		else{
			root.setTranslateX(root.getTranslateX());
		}
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
