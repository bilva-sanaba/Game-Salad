package controller;

import components.LocationComponent;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;

/*
 * @author Belal Taher
 */

public class Camera {
	
	public static final int RIGHT_BOUND_FROM_EDGE = 500; //lower is closer to right edge
	public static final int LEFT_BOUND_FROM_EDGE = 20; //lower is closer to left edge
	
	private int myLevelLength;
	private Scene myFrame;
	private Parent root;
	private LocationComponent followerLoc;
	
	public Camera(int length, Scene myScene, LocationComponent component) {
		myLevelLength = length;
		myFrame = myScene;
		root = myFrame.getRoot();
		followerLoc = component;
	}

	public void updateCamera(){
		if(( (myFrame.getWidth() - root.getTranslateX()) - (followerLoc.getX() ) <= RIGHT_BOUND_FROM_EDGE) && myFrame.getWidth()-root.getTranslateX() < myLevelLength){
			root.setTranslateX(root.getTranslateX() - 2 );
		}
		else if( followerLoc.getX() + root.getTranslateX() < LEFT_BOUND_FROM_EDGE && root.getTranslateX() < 0){
			root.setTranslateX(root.getTranslateX() + 2);
		}
		else{
			root.setTranslateX(root.getTranslateX());
		}
	}
}
