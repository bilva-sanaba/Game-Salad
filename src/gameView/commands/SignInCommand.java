package gameView.commands;

import javafx.stage.Stage;
import gameView.ICommandView;
import gameView.userManagement.UserData;

public class SignInCommand extends AbstractCommand {

	public SignInCommand(ICommandView m) {
		super(m);
	}

	@Override
	public void execute(Stage s) {
	}
	
	public void execute(Stage s, String username, String password) {
		UserData existingUser = new UserData(username, password, null);
		getView().selectUser(existingUser, false);
	}

	@Override
	public String getName() {
		return "SignIn";
	}

}
