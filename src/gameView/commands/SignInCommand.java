package gameView.commands;

import javafx.stage.Stage;
import gameView.ICommandView;

public class SignInCommand extends AbstractCommand {

	public SignInCommand(ICommandView m) {
		super(m);
	}

	@Override
	public void execute(Stage s) {
	}
	
	public void execute(Stage s, String username, String password) {
		//go in and check password in database
	}

	@Override
	public String getName() {
		return "SignIn";
	}

}
