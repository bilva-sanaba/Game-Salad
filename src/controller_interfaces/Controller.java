package controller_interfaces;

import java.util.Collection;
import java.util.Map;

import gameView.UIImageModel;
import gameView.UIImageProperty;

public class Controller implements ControllerInterface{

	@Override
	public Map<UIImageProperty, UIImageProperty> handleCollision(UIImageProperty coll1, UIImageProperty coll2,
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

	@Override
	public void checkCollision(UIImageModel u) {
		// TODO Auto-generated method stub
		
	}

}
