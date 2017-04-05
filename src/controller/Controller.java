package controller;

import gameView.UIImageModel;
import gameView.UIImageProperty;
import gameView.UIView;

import java.util.Collection;

import view_interfaces.UIViewInterface;
import javafx.stage.Stage;
import controller_interfaces.ControllerInterface;



public class Controller implements ControllerInterface {
	
	UIViewInterface myGameView;
	
	public Controller(Stage s) {
		myGameView = new UIView(s, this);
	}
	
	public Controller() {
	}

	@Override
	public UIImageProperty handleCollision(
			UIImageProperty coll1, UIImageProperty coll2,
			Collection<UIImageProperty> allActive) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadNewGame(String filePath) {
		
	}

	@Override
	public void resetCurrentGame() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkCollision(UIImageModel u) {
		// TODO Auto-generated method stub
		
	}
	

}
