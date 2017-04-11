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
		System.out.println((myFrame.getWidth() - root.getTranslateX()) - (followerLoc.getX()));
		if( (myFrame.getWidth() - root.getTranslateX()) - (followerLoc.getX() ) <= RIGHT_BOUND){
			root.setTranslateX(root.getTranslateX() - 2 );
		}
		else{
			root.setTranslateX(root.getTranslateX());
		}
	}
	
	
	
}
