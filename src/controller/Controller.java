package controller;

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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resetCurrentGame() {
		// TODO Auto-generated method stub
		
	}
	

}
