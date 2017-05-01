package gameView.commands;

import javafx.stage.Stage;
import gameView.ICommandView;
import gameView.profileScreen.ProfileScreen;

public class ProfileCommand extends AbstractCommand {

	public ProfileCommand(ICommandView m) {
		super(m);
	}

	@Override
	public boolean execute(Stage s) {
		getView().pauseGame();
		getView().profileScreen();
		//Stage newStage = new Stage();
		//ProfileScreen profile = new ProfileScreen(getView().getView(), newStage, getView().getUserInput(), getView().getUserManager());
		//newStage.setScene(profile.getScene());
		//newStage.showAndWait();
		getView().runGame();
		return true;
	}

	@Override
	public String getName() {
		return "Profile";
	}

}
