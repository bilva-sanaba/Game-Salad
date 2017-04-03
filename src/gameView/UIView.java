package gameView;

import controller_interfaces.ControllerInterface;
import javafx.stage.Stage;
import view_interfaces.UIViewInterface;


public class UIView implements UIViewInterface {
	
	private Stage myStage;
	private ControllerInterface myController;
	
	public UIView(Stage s, ControllerInterface controller) {
		myStage = s;
		myController = controller;
		
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
