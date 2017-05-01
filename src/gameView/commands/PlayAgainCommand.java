package gameView.commands;

import javafx.stage.Stage;
import gameView.ICommandView;

public class PlayAgainCommand extends AbstractCommand {

	public PlayAgainCommand(ICommandView m) {
		super(m);
	}

	@Override
	public boolean execute(Stage s) {
		getView().restart();
		return false;
	}

	@Override
	public String getName() {
		return "PlayAgain";
	}

}
