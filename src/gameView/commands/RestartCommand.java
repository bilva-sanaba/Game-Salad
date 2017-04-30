package gameView.commands;

import javafx.stage.Stage;
import gameView.ICommandView;

public class RestartCommand extends AbstractCommand {

	public RestartCommand(ICommandView m) {
		super(m);
	}

	@Override
	public boolean execute(Stage s) {
		((ICommandView) getView()).restart();
		return true;
		
	}

	@Override
	public String getName() {
		return "Restart";
	}

}
