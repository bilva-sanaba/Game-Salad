package voogasalad.util.paint;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class PaintUtilityAlert {
	
	Alert a;
	
	public PaintUtilityAlert(String title, String content) {
		a = new Alert(AlertType.ERROR);
		a.setTitle(title);
		a.setContentText(content);
	}
	
	public void showAlert() {
		a.showAndWait();
	}
}
