package gameView.commands;

import com.restfb.Parameter;
import com.restfb.types.FacebookType;

import javafx.stage.Stage;
import gameView.ICommandView;

public class FacebookPostCommand extends AbstractCommand {

	public FacebookPostCommand(ICommandView m) {
		super(m);
	}

	@Override
	public boolean execute(Stage s) {
		//getView().getUserManager().getCurrentUser().getClient().publish("me/feed", FacebookType.class, Parameter.with("message", "I ROCK!"));
		return false;
	}

	@Override
	public String getName() {
		return "FacebookPost";
	}

}
