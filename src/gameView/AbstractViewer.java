package gameView;

import gameView.commands.AbstractCommand;
import gameView.commands.LoginCommand;
import gameView.commands.ProfileCommand;
import gameView.loginScreen.LoginScreen;
import gameView.profileScreen.ProfileScreen;
import gameView.tools.ButtonFactory;
import gameView.tools.CommandFactory;
import gameView.tools.DisplayManager;
import gameView.userInput.IUserInputData;
import gameView.userManagement.IUserManager;

import java.util.Collection;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public abstract class AbstractViewer implements ICommandView {

	private UIView myView;
	private ButtonFactory myButtonFactory;
	private Stage myStage;
	private IUserInputData myUserInput;
	private Button myLogIn;
	private Button myProfile;
	
	public AbstractViewer(UIView view, Stage s, IUserInputData userInput) {
		myStage = s;
		myView = view;
		myUserInput = userInput;
		myButtonFactory = new ButtonFactory(UIView.DEFAULT_BUTTONS, getStage());
	}
	
	public abstract Scene getScene();
	
	public IUserInputData getUserInput() {
		return myUserInput;
	}
	
	public void addBackground(String s) {
		setBackground(s);
	}
	
	protected abstract void setBackground(String s);
	
	protected UIView getView() {
		return myView;
	}
	
	protected Stage getStage() {
		return myStage;
	}
	
	protected Collection<AbstractCommand> getCommands(String name) {
		CommandFactory commands = new CommandFactory(name, UIView.DEFAULT_LOCATION+UIView.DEFAULT_BUTTONS);
		return commands.getCommands(this);
	}
	
	protected Button makeButton(AbstractCommand command) {
		return myButtonFactory.makeButton(command);
	}

	protected Label makeLabel(String label, String id) {
		Label newLabel = new Label(label);
		newLabel.setId(id);
		return newLabel;
	}
	
	public void loadGame(String filepath) {
		myView.loadGame(filepath);
	}

	public void restart() {
		myView.restart();
	}
	
	public void saveGame() {
		getView().saveGame();
	}
	
	public void makeGame() {
		getView().authorGame();
	}
	
	public void loginScreen() {
		Stage s = new Stage();
		getView().newStage(new LoginScreen(getView(), s, getUserInput()), s);
	}
	
	public void profileScreen() {
		Stage s = new Stage();
		getView().newStage(new ProfileScreen(getView(), s, getUserInput()), s);
	}
	
	public IUserManager getUserManager() {
		return getView().getUserManager();
	}

	//DOES NOTHING FOR SPLASHSCREEN
	public void runGame() {
		//getView().runGame();
	}
	public DisplayManager getComponents() {
		return null;
	}
	public void pauseGame() {
	}
	
	public String getInstructions(){
		return null;
	}
	
	protected void setUserCommand() {
		myLogIn = makeButton(new LoginCommand(this));
		myProfile = makeButton(new ProfileCommand(this));
		setUserBinding();
		makeUserButton(getView().getUserManager().hasCurrentUser().get());
	}
	
	private void setUserBinding() {
		getView().getUserManager().hasCurrentUser().addListener(new ChangeListener<Boolean>(){
	        public void changed(ObservableValue<? extends Boolean> o,Boolean oldVal, 
	        		Boolean newVal){
	             makeUserButton(newVal);
	        }
	      });
	}
	
	private void makeUserButton(boolean bool) {
		if (bool) {
			switchButton(myProfile, myLogIn);
		} else {
			switchButton(myLogIn, myProfile);
		}
	}
	
	private void switchButton(Button toAdd, Button toRemove) {
		try{
			getButtonContainer().getChildren().remove(toRemove);
			getButtonContainer().getChildren().add(toAdd);
		} catch (Exception e) {
			getButtonContainer().getChildren().add(toAdd);
		}
	}
	protected abstract Pane getButtonContainer();
	
}
