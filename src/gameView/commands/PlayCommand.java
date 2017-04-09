package gameView.commands;

import javafx.stage.Stage;
import gameView.ICommandView;

public class PlayCommand extends AbstractCommand {

	public PlayCommand(ICommandView m) {
		super(m);
	}

	@Override
	public void execute(Stage s) {
		getView().runGame();
	}

	@Override
	public String getName() {
		return "Play";
	}

	
}
