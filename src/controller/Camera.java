package controller;

import components.LocationComponent;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;

/*
 * @author Belal Taher
 */

public class Camera {
	
	public static final int RIGHT_BOUND = 400;
	public static final int LEFT_BOUND = 20;
	
	private int myLevelLength;
	private Scene myFrame;
	private Parent root;
	private LocationComponent followerLoc;
	
	public Camera(int levelLength, Scene frame, LocationComponent loc){
		myLevelLength = levelLength;
		myFrame = frame;
		root = myFrame.getRoot();
		followerLoc = loc;
	}
	
	public void updateCamera(){
		if(( (myFrame.getWidth() - root.getTranslateX()) - (followerLoc.getX() ) <= RIGHT_BOUND) && myFrame.getWidth()-root.getTranslateX() < myLevelLength){
			root.setTranslateX(root.getTranslateX() - 2 );
		}
		else if( followerLoc.getX() + root.getTranslateX() < LEFT_BOUND && root.getTranslateX() < 0){
			root.setTranslateX(root.getTranslateX() + 2);
		}
		else{
			root.setTranslateX(root.getTranslateX());
		}
	}
	
	
	
}
