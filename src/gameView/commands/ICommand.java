package gameView.commands;

import javafx.stage.Stage;
import gameView.ICommandView;

public interface ICommand {

	/**
	 * Execute the command 
	 * @param s - stage
	 */
	public abstract void execute(Stage s);
	
	/**
	 * Get name of command -- used for reflection
	 * @return
	 */
	public abstract String getName();
}