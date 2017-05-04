package gameView.loginScreen;


import java.util.Arrays;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import gameView.AbstractViewer;
import gameView.UIView;
import gameView.UIViewInterface;
import gameView.commands.AbstractCommand;
import gameView.commands.FacebookCommand;
import gameView.commands.RegisterCommand;
import gameView.commands.SignInCommand;
import gameView.tools.ImageButton;
import gameView.tools.ImageViewContainer;
import gameView.tools.ResourceRetriever;
import gameView.userInput.IUserInputData;

public class LoginScreen extends AbstractViewer {
	
	private final String myName = "LoginScreen";
	
	private HBox myMain;
	private VBox myLeft;
	private VBox myRight;
	private Scene myScene;
	
	public LoginScreen(UIViewInterface view, Stage s, IUserInputData input) { 
		super(view, s, input);
		myLeft = new VBox(10);
		myRight = new VBox(10); 
		makeScene();
	}
 
	@Override 
	public Scene getScene() {    
		return myScene;
	}
	
	private void makeScene() {
		makeLeft();
		makeRight();
		myMain = new HBox(myLeft, myRight);
		myMain.setId("main");
		myScene = new Scene(myMain, UIView.DEFAULT_SIZE.width-100, UIView.DEFAULT_SIZE.height-100);
		myScene.getStylesheets().add(new ResourceRetriever().getStyleSheets(this,myName));
	}
	
	private void makeLeft() {
		Label lab = makeLabel("Register", "register");
		setMargin(lab, 0, 0, 20, 0);
		ImageButton imageButton = new ImageButton(getStage(), null);
		setMargin(imageButton, 0, 0, 20, 0);
		TextField username = makeInput("User Name", "username");
		TextField password = makeInput("Password", "passworrd"); 
		TextField confirmPass = makeInput("Re-enter Password", "passwordcheck");
		RegisterCommand command = new RegisterCommand(this);
		Button register = makeButton(command);
		register.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) { 
				String fromPictureButton = ((imageButton.getGraphic() == null) ? null : ((ImageViewContainer) imageButton.getGraphic()).getPath());
				checkForLoginCompletion(command.execute(getStage(), username.getText(), password.getText(), 
						confirmPass.getText(), fromPictureButton));
			}
		});
		setMargin(register, 20, 0, 0, 0);
		setBox(myLeft, "left", lab, imageButton, username, password, confirmPass, register);
	}
	
	private void makeRight() {
		Label lab = makeLabel("Sign In", "signin");
		setMargin(lab, 0, 0, 20, 0);
		TextField username = makeInput("User Name", "username"); 
		TextField password = makeInput("Password", "password");
		SignInCommand signCommand = new SignInCommand(this);
		Button signIn = makeButton(signCommand);
		signIn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				checkForLoginCompletion(signCommand.execute(getStage(), username.getText(), password.getText()));
			}  
		});
		AbstractCommand facebookCommand = new FacebookCommand(this);
		Button facebook = makeButton(facebookCommand);
		facebook.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				checkForLoginCompletion(facebookCommand.execute(getStage()));
			}  
		});
		setMargin(signIn, 20, 0, 0, 0);
		setBox(myRight, "right", lab, username, password, signIn, facebook);
	}
	
	private void setMargin(Node node, int top, int left, int bottom, int right) {
		VBox.setMargin(node, new Insets(top, left, bottom, right));
	}
	
	private void setBox(VBox box, String id, Node... args) { 
		box.setAlignment(Pos.CENTER);
		box.setId(id);
		box.getChildren().addAll(Arrays.asList(args));  
		box.setPrefWidth(UIView.DEFAULT_SIZE.width/2);
		box.setPrefWidth(UIView.DEFAULT_SIZE.height);
		 
	}
	
	private TextField makeInput(String description, String id) {
		TextField text = new TextField(description);
		text.setId(id);
		text.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				text.clear();
			}
		});
		return text;
	}
	
	private void checkForLoginCompletion(boolean bool) {
		if (bool) {
			getStage().close();
		}
	}

	@Override
	protected void setBackground(String s) {
		myMain.setStyle(String.format(
				"-fx-background-image: url(\"%s\");"
				+ "-fx-background-repeat: stretch;"
				+ "-fx-background-position: center center;"
				+ "-fx-background-size: cover;", s));
	}

	@Override
	protected Pane getButtonContainer() {
		return myRight;
	}
}
