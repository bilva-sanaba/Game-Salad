package gameView.commands;

import javafx.stage.Stage;
import gameView.ICommandView;

public class FacebookPostCommand extends AbstractCommand {

	public FacebookPostCommand(ICommandView m) {
		super(m);
	}

	@Override
	public boolean execute(Stage s) {
		//POST TO FACEBOOK
		return false;
	}

	@Override
	public String getName() {
		return "FacebookPost";
	}

}
