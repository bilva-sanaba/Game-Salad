package gameView;

import gameView.commands.AbstractCommand;
import gameView.tools.ButtonFactory;
import gameView.tools.CommandFactory;
import gameView.tools.DisplayManager;

import java.util.Collection;
import java.util.ResourceBundle;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public abstract class AbstractViewer implements ICommandView {

	private UIView myView;
	private ButtonFactory myButtonFactory;
	
	public AbstractViewer(UIView view) {
		myView = view;
		myButtonFactory = new ButtonFactory(view, view.DEFAULT_BUTTONS);
	}
	
	public abstract Scene getScene();
	
	
	protected UIView getView() {
		return myView;
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
		System.out.println("ABSTRACT");
		myView.loadGame(filepath);
	}

	public void restart() {
		System.out.println("ABSTRACT");
		myView.restart();
	}
	
	public void saveGame() {
		System.out.println("ABSTRACT");
		getView().saveGame();
	}
	
	public void makeGame() {
		System.out.println("ABSTRACT");
		getView().authorGame();
	}

	//DOES NOTHING FOR SPLASHSCREEN
	public void runGame() {
		System.out.println("ABSTRACT");
		getView().runGame();
	}
	public DisplayManager getComponents() {
		System.out.println("ABSTRACT");
		return null;
	}
	public void pauseGame() {
		System.out.println("ABSTRACT");
	}
	
}
