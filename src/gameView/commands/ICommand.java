package gameView.commands;

import javafx.stage.Stage;

public interface ICommand {

	/**
	 * Execute the command 
	 * @param s - stage
	 */
	public abstract boolean execute(Stage s);
	
	/**
	 * Get name of command -- used for reflection
	 * @return
	 */
	public abstract String getName();
}