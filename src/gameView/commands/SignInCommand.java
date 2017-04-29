package gameView.commands;

import javafx.stage.Stage;
import gameView.ICommandView;
import gameView.userManagement.UserData;

public class SignInCommand extends AbstractCommand {

	public SignInCommand(ICommandView m) {
		super(m);
	}

	@Override
	public boolean execute(Stage s) {
		return true;
	}
	
	public boolean execute(Stage s, String username, String password) {
		UserData existingUser = new UserData(username, password, null);
		return getView().getUserManager().selectUser(existingUser);
	}

	@Override
	public String getName() {
		return "SignIn";
	}

}
