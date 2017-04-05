package gameView;

import java.awt.Dimension;

import gameView.splashScreen.SplashView;
import controller_interfaces.ControllerInterface;
import javafx.stage.Stage;
import view_interfaces.UIViewInterface;


public class UIView implements UIViewInterface {
	
	public static final Dimension DEFAULT_SIZE = new Dimension(800, 600);
	private Stage myStage;
	private ControllerInterface myController;
	private SplashView mySplash;
	
	public UIView(Stage s, ControllerInterface controller) {
		myStage = s;
		myController = controller;
		mySplash = new SplashView(this);
		getSplashScreen();
	}

	public void getSplashScreen() {
		myStage.setScene(mySplash.getSplashScene());
		myStage.show();
	}
	
	@Override
	public void addUIImage(String picturePath, double x, double y,
			double width, double height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void runGame() {
		// TODO Auto-generated method stub
		
	}
	
	public void authorGame() {
		
	}

}
