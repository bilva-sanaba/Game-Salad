package controller;


import components.entityComponents.ComponentType;
import components.entityComponents.EntityType;
import components.entityComponents.LocationComponent;
import components.entityComponents.TypeComponent;
import components.entityComponents.VelocityComponent;
import entity.Entity;
import entity.IEntity;
import entity.restricted.IRestrictedEntity;
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
	private LocationComponent myLC;
	private double myFrameWidth;
	private double myFrameHeight;
	private boolean myActive;
	
	public Camera (int length, Scene myScene, LocationComponent lc , int id, boolean camera) {
		super(id);
		myLevelLength = length;
		myFrame = myScene;
		root = myFrame.getRoot();
		myLC = lc;
		myFrameWidth= myFrame.getWidth();
		myFrameHeight= myFrame.getHeight();
		myActive = camera;

		if (getComponent(ComponentType.Type) == null) {
			addComponent(new TypeComponent(EntityType.Camera));
		}

	}

	public void updateCamera() {
		if (myActive) {
			root.setTranslateX(-myLC.getX() + myFrameWidth / 2);
			root.setTranslateY(-myLC.getY()+300);
		}
		
		
		/*if(playerLocRelativeToCam <= LEFT_BOUND_FROM_EDGE && followerLoc.getX()>0){
			root.setTranslateX(root.getTranslateX() - followerVel.getX() + 4);
			//System.out.println(followerVel.getX());
		}
		else if (playerLocRelativeToCam >= RIGHT_BOUND_FROM_EDGE) {
			root.setTranslateX(root.getTranslateX() - followerVel.getX() - 4);
			//System.out.println(followerVel.getX());
		}*/

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