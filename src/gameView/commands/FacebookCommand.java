package gameView.commands;

import javafx.stage.Stage;
import gameView.ICommandView;
import gameView.loginScreen.FaceBookLogin;
import gameView.userManagement.UserData;

public class FacebookCommand extends AbstractCommand{
	
	public FacebookCommand(ICommandView view) {
		super(view);
	}

	@Override
	public void execute(Stage s) {
		FaceBookLogin facebook = new FaceBookLogin();
		UserData user = facebook.login();
		getView().addUser(user);
	}

	@Override
	public String getName() {
		return "Facebook";
	}
}
