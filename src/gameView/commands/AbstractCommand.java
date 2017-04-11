package gameView.commands;

import javafx.stage.Stage;
import gameView.ICommandView;
import gameView.UIView;

public abstract class AbstractCommand {

	private ICommandView myView;

	public AbstractCommand(ICommandView m) {
		super();
		myView = m;
	}

	protected ICommandView getView() {
		return myView;
	}

	public abstract void execute(Stage s);

	public abstract String getName();
}