package gameView.commands;

import javafx.stage.Stage;
import gameView.ICommandUIView;
import gameView.ICommandView;

public class RestartCommand extends AbstractCommand {

	public RestartCommand(ICommandView m) {
		super(m);
	}

	@Override
	public void execute(Stage s) {
		((ICommandUIView) getView()).restart();
		
	}

	@Override
	public String getName() {
		return "Restart";
	}

}
