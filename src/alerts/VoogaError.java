package alerts;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class VoogaError {
	private Alert alert;
public VoogaError(String content, String text ){
	alert=new Alert(AlertType.ERROR);
	alert.setTitle(content);
	alert.setContentText(text);
	
}
/**
 * displays alert
 */
public void showAlert(){
	alert.showAndWait();
}
}
