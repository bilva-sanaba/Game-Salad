package gameView.commands;

import javafx.stage.Stage;
import gameView.ICommandView;

public class RegisterCommand extends AbstractCommand {

	public RegisterCommand(ICommandView m) {
		super(m);
	}

	@Override
	public void execute(Stage s) {
	
	
	}

	@Override
	public String getName() {
		return "Register";
	}

}
