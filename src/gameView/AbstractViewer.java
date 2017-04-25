package gameView;

import gameView.commands.AbstractCommand;
import gameView.loginScreen.LoginScreen;
import gameView.tools.ButtonFactory;
import gameView.tools.CommandFactory;
import gameView.tools.DisplayManager;
import gameView.userManagement.IUserManager;
import gameView.userManagement.UserData;

import java.util.Collection;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public abstract class AbstractViewer implements ICommandView {

	private UIView myView;
	private ButtonFactory myButtonFactory;
	private Stage myStage;

	
	public AbstractViewer(UIView view, Stage s) {
		myStage = s;
		myView = view;
		myButtonFactory = new ButtonFactory(UIView.DEFAULT_BUTTONS, getStage());
	}
	
	public abstract Scene getScene();
	
	
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
		getView().newStage(new LoginScreen(getView(), s), s);
	}
	
	public IUserManager getUserManager() {
		return getView().getUserManager();
	}

	//DOES NOTHING FOR SPLASHSCREEN
	public void runGame() {
		System.out.println("ABSTRACT");
		//getView().runGame();
	}
	public DisplayManager getComponents() {
		System.out.println("ABSTRACT");
		return null;
	}
	public void pauseGame() {
		System.out.println("ABSTRACT");
	}
	public String getInstructions(){
		return null;
	}
	
}
