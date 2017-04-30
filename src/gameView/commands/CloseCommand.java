package gameView.commands;

import javafx.stage.Stage;
import gameView.ICommandView;

public class CloseCommand extends AbstractCommand {

	public CloseCommand(ICommandView m) {
		super(m);
	}

	@Override
	public boolean execute(Stage s) {
		s.close();
		return false;
	}

	@Override
	public String getName() {
		return "Close";
	}

}
