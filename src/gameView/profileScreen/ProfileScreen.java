package gameView.profileScreen;

import javafx.scene.Scene;
import gameView.AbstractViewer;
import gameView.UIView;
import gameView.userManagement.IUserManager;
import gameView.userManagement.UserData;

public class ProfileScreen extends AbstractViewer {

	private Scene myScene;
	private IUserManager myData;
	
	public ProfileScreen(UIView view, IUserManager data) {
		super(view);
		myData = data;
		makeScene();
	}

	@Override
	public Scene getScene() {
		return myScene;
	}
	
	private void makeScene() {
		
	}
	
	
	
}
