package gameView.commands;

import javafx.stage.Stage;
import gameView.ICommandView;

public class ProfileCommand extends AbstractCommand {

	public ProfileCommand(ICommandView m) {
		super(m);
	}

	@Override
	public boolean execute(Stage s) {
		getView().pauseGame();
		getView().profileScreen();
		return true;
	}

	@Override
	public String getName() {
		return "Profile";
	}

}
