package gameView.commands;

import javafx.stage.Stage;
import gameView.ICommandView;
import gameView.UIView;

public class MakeCommand extends AbstractCommand {

	public MakeCommand(ICommandView m) {
		super(m);
	}

	@Override
	public boolean execute(Stage s) {
		((ICommandView) getView()).makeGame(); 
		return true;
	}

	@Override
	public String getName() {
		return "Make";
	}

}
