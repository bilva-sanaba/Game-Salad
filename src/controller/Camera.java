package controller;

import components.LocationComponent;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;

/*
 * @author Belal Taher
 */

public class Camera {
	
	public static final int rightBoundInFrame = 200;
	
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
		if( ((myFrame.getWidth() - root.getTranslateX()) - (followerLoc.getX() - root.getTranslateX())) <= 200){
			System.out.println((myFrame.getWidth() - root.getTranslateX()) - (followerLoc.getX() - root.getTranslateX()));
			root.setTranslateX(root.getTranslateX() +2 );
		}
	}
	
	
	
}
