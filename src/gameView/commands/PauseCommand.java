package gameView.commands;

import javafx.stage.Stage;
import gameView.ICommandView;

public class PauseCommand extends AbstractCommand {

	public PauseCommand(ICommandView m) {
		super(m);
	}

	@Override
	public void execute(Stage s) {
		((ICommandView) getView()).pauseGame();
	}

	@Override
	public String getName() {
		return "Pause";
	}

}
