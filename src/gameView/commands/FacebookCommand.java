package gameView.commands;

import javafx.stage.Stage;
import gameView.ICommandView;
import gameView.facebook.FaceBook;
import gameView.userManagement.UserData;

public class FacebookCommand extends AbstractCommand{
	
	public FacebookCommand(ICommandView view) {
		super(view);
	}

	@Override
	public boolean execute(Stage s) {
		FaceBook facebook = new FaceBook();
		UserData user = facebook.login();
		return getView().getUserManager().facebookUser(user);
		
	}

	@Override
	public String getName() {
		return "Facebook";
	}
}
