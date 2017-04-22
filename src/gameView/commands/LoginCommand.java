package gameView.commands;

import javafx.stage.Stage;
import gameView.ICommandView;
import gameView.loginScreen.LoginScreen;

public class LoginCommand extends AbstractCommand{

	public LoginCommand(ICommandView m) {
		super(m);
	}

	@Override
	public void execute(Stage s) {
		Stage newStage = new Stage();
		LoginScreen login = new LoginScreen(getView().getView());
		newStage.setScene(login.getScene());
		newStage.showAndWait();
		
	}

	@Override
	public String getName() {
		return "Login";
	}

}
