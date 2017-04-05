package gameView.observers;

import gameView.UIView;

import java.util.Observable;
import java.util.Observer;

public class ImageObserver implements Observer{

	private UIView myView;
	
	public ImageObserver(UIView view) {
		super();
		myView = view;
	}
	
	
	@Override
	public void update(Observable o, Object arg) {
		
	}

}
