package gameView.commands;

import javafx.stage.Stage;
import gameView.ICommandView;
import gameView.tools.FrontEndException;
import gameView.userManagement.UserData;

public class RegisterCommand extends AbstractCommand {
	
	public RegisterCommand(ICommandView m) {
		super(m);
	}

	@Override
	public boolean execute(Stage s) {
		return true;
	}
	
	public boolean execute(Stage s, String username, String password, String passwordCheck, String userImage) {
		if (!password.equals(passwordCheck)) {
			throw new FrontEndException(String.format("PASSWORDS DO NOT MATCH: %s %s", password, passwordCheck));
		}
		UserData user = new UserData(username, password, userImage);
		return getView().getUserManager().addUser(user);
	}

	@Override
	public String getName() {
		return "Register";
	}

}
