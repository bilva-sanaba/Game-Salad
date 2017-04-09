package gameView.commands;

import javafx.stage.Stage;
import gameView.ICommandUIView;
import gameView.UIView;

public class SaveCommand extends AbstractCommand {

	public SaveCommand(UIView m) {
		super(m);
	}

	@Override
	public void execute(Stage s) {
		((ICommandUIView) getView()).saveGame();
	}

	@Override
	public String getName() {
		return "Save";
	}

}
