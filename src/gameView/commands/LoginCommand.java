package gameView.commands;

import javafx.stage.Stage;
import gameView.ICommandView;

public class LoginCommand extends AbstractCommand{

	public LoginCommand(ICommandView m) {
		super(m);
	}

	@Override
	public void execute(Stage s) {
		getView().loginScreen();	
	}

	@Override
	public String getName() {
		return "Login";
	}

}
