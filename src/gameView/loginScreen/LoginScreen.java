package gameView.loginScreen;


import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import gameView.AbstractViewer;
import gameView.ICommandView;
import gameView.UIView;
import gameView.commands.RegisterCommand;

public class LoginScreen extends AbstractViewer {
	
	private VBox myLeft;
	private VBox myRight;
	private Scene myScene;
	
	public LoginScreen(UIView view) {
		super(view);
		myLeft = new VBox(8);
		myRight = new VBox(8);
	}

	@Override
	public Scene getScene() {
		return myScene;
	}
	
	
	private void makeLeft() {
		Label lab = makeLabel("Register", "register");
		TextField username = makeInput("User Name", "username");
		TextField password = makeInput("Password", "passworrd");
		TextField confirmPass = makeInput("Re-enter Password", "passwordcheck");
		Button register = makeButton(new RegisterCommand((ICommandView) getView()));
		
	}
	
	private void makeRight() {
		Label lab = makeLabel("Sign In", "signin");
		TextField username = makeInput("User Name", "username");
		TextField password = makeInput("Password", "password");
		
		
	}
	
	private TextField makeInput(String description, String id) {
		TextField text = new TextField(description);
		text.setId(id);
		return text;
	}
	
}
