package controller;

import gameView.UIImageModel;
import gameView.UIImageProperty;
import gameView.UIView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import view_interfaces.UIViewInterface;
import javafx.stage.Stage;
import controller_interfaces.ControllerInterface;
import gameEngine_interface.GameEngine;



public class Controller implements ControllerInterface {
	
	UIViewInterface myGameView;
	private GameEngine myGameEngine;
	
	public Controller(Stage s) {
		myGameView = new UIView(s, this);
		myGameEngine = new GameEngine();
	}
	
	public Controller() {
	}

	@Override
	public UIImageProperty handleCollision(
			UIImageProperty coll1, UIImageProperty coll2,
			Collection<UIImageProperty> allActive) {
		Collection<Object> collidingObjects = new ArrayList<Object>();
		collidingObjects.addAll(Arrays.asList(coll1, coll2));
		myGameEngine.handleInteraction(collidingObjects, allActive);
		return null;
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		//loop through and save all write all items to XML
		
	}

	@Override
	public void loadNewGame(String filePath) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resetCurrentGame() {
		// TODO Auto-generated method stub
		//load starting game file
		
	}

	@Override
	public void checkCollision(UIImageModel u) {
		// TODO Auto-generated method stub
		
	}
	

}
