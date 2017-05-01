package gameView.commands;

import javafx.stage.Stage;
import gameView.ICommandView;
import gameView.tools.GameChooser;

public class UserLoadCommand extends LoadCommand {

	public UserLoadCommand(ICommandView m) {
		super(m);
	}

	@Override
	public String getName() {
		return "UserLoad";
	}
	
	@Override
	protected Stage fileChoice(GameChooser picker) {
		return picker.selectFile(getView().getUserManager().getCurrentUser().getGames());
	}
	
}
