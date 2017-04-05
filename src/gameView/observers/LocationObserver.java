package gameView.observers;

import gameView.UIView;

import java.util.Observable;
import java.util.Observer;

public class LocationObserver implements Observer {
	private UIView myView;
	
	public LocationObserver(UIView view) {
		super();
		myView = view;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		
	}

}
