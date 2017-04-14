package gameView.commands;

import javafx.stage.Stage;
import controller.VoogaAlert;
import gameView.ICommandView;

public class PauseCommand extends AbstractCommand {

	public PauseCommand(ICommandView m) {
		super(m);
	}

	@Override
	public void execute(Stage s) {
		((ICommandView) getView()).pauseGame();
		VoogaAlert v = new VoogaAlert("Vooga", "Pause");
		//v.showAlert();
	}

	@Override
	public String getName() {
		return "Pause";
	}

}
