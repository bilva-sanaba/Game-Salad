package gameView.tools;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class FrontEndException extends RuntimeException {

	public FrontEndException(String message) {
		Alert a = new Alert(AlertType.ERROR);
        a.setContentText(String.format("ERROR: %s", message));
        a.show();
	}
	
}
