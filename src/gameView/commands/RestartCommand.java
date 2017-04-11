package gameView.commands;

import javafx.stage.Stage;
import gameView.ICommandView;

public class RestartCommand extends AbstractCommand {

	public RestartCommand(ICommandView m) {
		super(m);
	}

	@Override
	public void execute(Stage s) {
		((ICommandView) getView()).restart();
		
	}

	@Override
	public String getName() {
		return "Restart";
	}

}
