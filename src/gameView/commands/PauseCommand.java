package gameView.commands;

import javafx.stage.Stage;
import gameView.ICommandView;
import gameView.pauseScreen.PauseScreen;

public class PauseCommand extends AbstractCommand {

	public PauseCommand(ICommandView m) {
		super(m);
	}

	@Override
	public boolean execute(Stage s) {
		getView().pauseGame();
		Stage newStage = new Stage();
		PauseScreen pauseScreen = new PauseScreen(null, newStage, getView().getUserInput());
		newStage.setScene(pauseScreen.getScene());
		newStage.show();
		return true;
	}

	@Override
	public String getName() {
		return "Pause";
	}
}
