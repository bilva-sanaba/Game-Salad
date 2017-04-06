package gameView.commands;

import javafx.stage.Stage;
import gameView.UIView;

public class SaveCommand extends AbstractCommand {

	public SaveCommand(UIView m) {
		super(m);
	}

	@Override
	public void execute(Stage s) {
		getView().saveGame();
	}

	@Override
	public String getName() {
		return "Save";
	}

}
