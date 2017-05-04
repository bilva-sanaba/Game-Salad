package gameView;

import gameView.commands.AbstractCommand;
import gameView.commands.LoginCommand;
import gameView.commands.ProfileCommand;
import gameView.gameDataManagement.GameDataManager;
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

//THIS ENTIRE FILE IS A PART OF MY MASTERPIECE
//hjt8

/**
 * This following class is the class of AbstractViewer. The purpose of this class is to provide a general framework for making 
 * new viewers easily, and with many methods that are incredibly helpful, such as getCommand(). I think this class is well designed because
 * it allows for easily extensibility to many different viewers and provides the necessary framework for making one. In addition, it is 
 * encapsulated by ICommandView, which allows it to be distributed to commands for an informative, yet extensive explanation of what methods the c
 * commands have. For my masterpiece, I was trying to decide between the viewers or userData, but I think that viewers are such a big part of the 
 * entire software, that it would be better to demonstrate its utility.
 * @author Henry
 *
 */
public abstract class AbstractViewer implements ICommandView {

	private UIViewInterface myView;
	private ButtonFactory myButtonFactory;
	private Stage myStage;
	private IUserInputData myUserInput;
	private Button myLogIn;
	private Button myProfile;
	private GameDataManager myData;
	
	public AbstractViewer(UIViewInterface view, Stage s, IUserInputData userInput) {
		myStage = s;
		myView = view;
		myUserInput = userInput;
		myButtonFactory = new ButtonFactory(UIView.DEFAULT_BUTTONS, getStage());
	}
	
	/**
	 * Returns the class name of the viewer -- used for command creation
	 * @return
	 */
	protected abstract String getName();
	
	/**
	 * @return Viewer Scene to be displayed on stage
	 */
	public abstract Scene getScene();
	
	/**
	 * Get user input data that needs to be communicated with backend (such as rewind)
	 * @return
	 */
	public IUserInputData getUserInput() {
		return myUserInput;
	}
	
	/**
	 * Get user manager to view current user, sign out, etc. 
	 * @return
	 */
	public IUserManager getUserManager() {
		return getView().getUserManager();
	}
	
	/**
	 * Adds a background to the scene
	 * @param s - background file
	 */
	public void addBackground(String s) {
		setBackground(s);
	}
	
	/**
	 * Adds Game data to viewer
	 * @param data - gameData
	 */
	public void addData(GameDataManager data) {
		myData = data;
	}
	
	/**
	 * Gives access to GameData to subclasses
	 * @return
	 */
	protected GameDataManager getGameData() {
		return myData;
	}
	
	/**
	 * Requires viewer-specific way to implement the background, or allows them not to
	 * @param s
	 */
	protected abstract void setBackground(String s);
	
	/**
	 * Returns the UIView
	 * @return
	 */
	protected UIViewInterface getView() {
		return myView;
	}
	
	protected Stage getStage() {
		return myStage;
	}
	
	/**
	 * Given the class name, reads from a resource file to make a collection of commands to be used for buttons
	 * @param name - viewer name
	 * @return
	 */
	protected Collection<AbstractCommand> getCommands(String name) {
		CommandFactory commands = new CommandFactory(name, UIView.DEFAULT_LOCATION+UIView.DEFAULT_BUTTONS);
		return commands.getCommands(this);
	}
	
	/**
	 * Makes a button given an abstractCommand
	 * @param command
	 * @return
	 */
	protected Button makeButton(AbstractCommand command) {
		return myButtonFactory.makeButton(command);
	}

	/**
	 * Makes a label given a string and id (for CSS styling)
	 * @param label
	 * @param id
	 * @return
	 */
	protected Label makeLabel(String label, String id) {
		Label newLabel = new Label(label);
		newLabel.setId(id);
		return newLabel;
	}
	
	/**
	 * Creates the User management buttons -- allows for binding to allow buttons to switch depending on current state of user
	 */
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
	
	/**
	 * The Pane which each viewer would like the UserButton to be displayed
	 * @return
	 */
	protected abstract Pane getButtonContainer();
	
	
	/**
	 * THE FOLLOWING LINES ARE USED BY COMMANDS TO PERFORM A VARIETY OF ACTIONS
	 */
	public void loadGame(String filepath) {
		getView().loadGame(filepath);
	}

	public void restart() {
		getView().restart();
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

	//DOES NOTHING FOR SPLASHSCREEN
	public void runGame() {
	}
	public DisplayManager getComponents() {
		return null;
	}
	public void pauseGame() {
	}
	
}
