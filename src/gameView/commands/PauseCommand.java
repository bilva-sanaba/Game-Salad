package gameView.commands;

import javafx.stage.Stage;
import gameView.ICommandGameView;
import gameView.ICommandView;

public class PauseCommand extends AbstractCommand {

	public PauseCommand(ICommandView m) {
		super(m);
	}

	@Override
	public void execute(Stage s) {
		((ICommandGameView) getView()).pauseGame();
	}

	@Override
	public String getName() {
		return "Pause";
	}

}
