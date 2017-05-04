package gameView.commands;

import javafx.stage.Stage;
import gameView.ICommandView;

public class SaveCommand extends AbstractCommand {

	public SaveCommand(ICommandView m) {
		super(m);
	}

	@Override
	public boolean execute(Stage s) {
		getView().saveGame();
		return true;
	}

	@Override
	public String getName() {
		return "Save";
	}

}
