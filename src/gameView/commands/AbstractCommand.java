package gameView.commands;

import javafx.stage.Stage;
import gameView.ICommandView;

public abstract class AbstractCommand implements ICommand {

	private ICommandView myView;

	/**
	 * Commands used to create buttons and are actions to be performed when a user clicks on the button. Commands work by primarly
	 * calling methods through their specific ICommandViews to allow for a centralized locatio of customization and control flow
	 * @param m
	 */
	public AbstractCommand(ICommandView m) {
		myView = m;
	}
 
	protected ICommandView getView() { 
		return myView;
	} 

	public abstract boolean execute(Stage s);

	public abstract String getName();
}
  