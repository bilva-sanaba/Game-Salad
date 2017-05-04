package gameView.commands;

import javafx.stage.Stage;
import gameView.ICommandView;
//THIS IS A PART OF MY MASTERPIECE
//hjt8

/**
 * This code is a part of my masterpeice because it shows the other hugely important size of our software -- the ability to create general
 * commands that can be applied to anything and control the flow of the game. This is an example of the abstractCommand, which contains everything
 * needed to be implemented and used correctly. These commands are created using a classic reflection-using factory in CommandFactory. 
 * @author Henry
 *
 */
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
  