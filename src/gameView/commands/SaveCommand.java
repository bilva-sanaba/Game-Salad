package gameView.commands;

import javafx.stage.Stage;
import gameView.ICommandUIView;
import gameView.ICommandView;
import gameView.UIView;

public class SaveCommand extends AbstractCommand {

	public SaveCommand(ICommandView m) {
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
