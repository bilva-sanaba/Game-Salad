package gameView.commands;

import java.io.File;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import gameView.ICommandUIView;
import gameView.ICommandView;
import gameView.UIView;
import gameView.tools.GameChooser;

public class LoadCommand extends AbstractCommand {

	public LoadCommand(UIView m) {
		super(m);
	}
	
	@Override
	public void execute(Stage s) {
		GameChooser gameChoice = new GameChooser(s);
		File fileToLoad = gameChoice.getFile();
		((ICommandUIView) getView()).loadGame(fileToLoad.getPath());
	}

	@Override
	public String getName() {
		return "Load";
	}
	

}
