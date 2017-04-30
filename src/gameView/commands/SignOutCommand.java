package gameView.commands;

import javafx.stage.Stage;
import gameView.ICommandView;

public class SignOutCommand extends AbstractCommand {

	public SignOutCommand(ICommandView m) {
		super(m);
	}

	@Override
	public boolean execute(Stage s) {
		getView().getUserManager().signOut();
		s.close();
		
		return true;
	}
	
	@Override
	public String getName() {
		return "SignOut";
	}

}
