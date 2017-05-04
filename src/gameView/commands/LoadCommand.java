package gameView.commands;

import javafx.stage.Stage;
import gameView.ICommandView;
import gameView.tools.GameChooser;

public class LoadCommand extends AbstractCommand {

	public LoadCommand(ICommandView m) {
		super(m);
	}

	@Override
	public boolean execute(Stage s) {
		getView().pauseGame();
		GameChooser gameChoice = new GameChooser(s);
		Stage newStage = fileChoice(gameChoice);
		newStage.showAndWait();
		getView().loadGame(gameChoice.getFile());
		return true;
	}

	@Override
	public String getName() {
		return "Load";
	}
	
	protected Stage fileChoice(GameChooser picker) {
		return picker.selectFile();
	}

}
