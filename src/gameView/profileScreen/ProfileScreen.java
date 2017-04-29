	package gameView.profileScreen;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import gameView.AbstractViewer;
import gameView.UIView;
import gameView.commands.FacebookPostCommand;
import gameView.commands.SignOutCommand;
import gameView.tools.ImageButton;
import gameView.tools.ResourceRetriever;
import gameView.userInput.IUserInputData;
import gameView.userManagement.IUserManager;

public class ProfileScreen extends AbstractViewer {

	private static final String myName = "ProfileScreen";
	
	private Stage myStage;
	private Scene myScene;
	private IUserManager myData;
	private VBox myBox;
	
	public ProfileScreen(UIView view, Stage s, IUserInputData input, IUserManager data) {
		super(view, s, input);
		myData = data;
		myStage = s;
		setStageReaction();
		makeScene();
	}

	@Override
	public Scene getScene() {
		return myScene;
	}
	
	@Override
	public IUserManager getUserManager() {
		return myData;
	}
	
	private void setStageReaction() {
		myStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
	          public void handle(WindowEvent we) {
	              //SAVE UPDATED PREFS
	          }
	      });    
	}
	
	private void makeScene() {
		myBox = makeVBox();
		myScene = new Scene(myBox, UIView.DEFAULT_SIZE.width/2.5, UIView.DEFAULT_SIZE.height/1.25);
		myScene.getStylesheets().add(new ResourceRetriever().getStyleSheets(this,myName));
		
	}
	
	private VBox makeVBox() {
		ImageButton image = new ImageButton(myStage, myData.getCurrentUser().getProfilePicture().getPath());
		Label name = new Label(myData.getCurrentUser().getName());
		HBox options = makeOptionBox();
		VBox toReturn = new VBox(20, image, name, options);  
		toReturn.setAlignment(Pos.CENTER);
		return toReturn;
	}
	
	private HBox makeOptionBox() {
		Button signOut = makeButton(new SignOutCommand(this)); 
		Button facebookPost = makeButton(new FacebookPostCommand(this));
		System.out.println(facebookPost.getId());
		HBox optionsBox = new HBox(20, signOut, facebookPost);
		optionsBox.setAlignment(Pos.CENTER);
		return optionsBox;
	}
}
