package gameView.commands;

import javafx.stage.Stage;
import gameView.ICommandView;
import gameView.tools.GameChooser;
//THIS CLASS IS A PART OF MY MASTERPEICE
//hjt8

/**
 * I wanted to include this class as a part of my masterpeice because it demonstrates the type of functionality that commands can have. 
 * Commands can do such a large array of things, which makes them so powerful to take advantage of. Because they are abstract, they 
 * are also highly exntendible and flexible. 
 * @author Henry
 *
 */
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
