package gameView.commands;

import javafx.stage.Stage;
import gameView.ICommandView;
import gameView.UIView;

public class MakeCommand extends AbstractCommand {

	public MakeCommand(ICommandView m) {
		super(m);
	}

	@Override
	public void execute(Stage s) {
		((ICommandView) getView()).makeGame();
	}

	@Override
	public String getName() {
		return "Make";
	}

}
