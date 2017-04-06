package gameView.observers;

import gameView.UIView;
import gameView.gameScreen.GameScreen;

import java.util.Observable;
import java.util.Observer;

public class EntityManagerObserver implements Observer {

	private GameScreen myGameView;
	
	public EntityManagerObserver(GameScreen view) {
		myGameView = view;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
