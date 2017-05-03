package gameView.commands;

import javafx.stage.Stage;
import gameView.ICommandView;

public class MakeCommand extends AbstractCommand {

	public MakeCommand(ICommandView m) {
		super(m);
	}

	@Override
	public boolean execute(Stage s) {
		getView().makeGame(); 
		return true;
	}

	@Override
	public String getName() {
		return "Make";
	}

}
