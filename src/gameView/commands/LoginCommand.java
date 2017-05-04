package gameView.commands;

import javafx.stage.Stage;
import gameView.ICommandView;

public class LoginCommand extends AbstractCommand{

	public LoginCommand(ICommandView m) {
		super(m);
	}

	@Override
	public boolean execute(Stage s) {
		getView().pauseGame();
		getView().loginScreen();
		return true;
	}

	@Override
	public String getName() {
		return "Login";
	}

}
