package gameView.commands;

import javafx.stage.Stage;
import gameView.ICommandUIView;
import gameView.UIView;

public class MakeCommand extends AbstractCommand {

	public MakeCommand(UIView m) {
		super(m);
	}

	@Override
	public void execute(Stage s) {
		((ICommandUIView) getView()).authorGame();
	}

	@Override
	public String getName() {
		return "Make";
	}

}
