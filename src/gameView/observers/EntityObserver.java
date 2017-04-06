package gameView.observers;

import gameView.UIView;
import gameView.gameScreen.GameScreen;

import java.util.Observable;
import java.util.Observer;

public class EntityObserver implements Observer {
	
	private GameScreen myGameView;

	public EntityObserver(GameScreen view) {
		myGameView = view;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
