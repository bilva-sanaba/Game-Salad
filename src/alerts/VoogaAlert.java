package alerts;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
/**
 * Generic "Vooga Alert." Winning the war on stack trace.
 * Alert may be added and used to appear if there is an exception at any point in the program
 * @author Jacob
 *
 */
public class VoogaAlert {
	private Alert alert;
	private final String VOOGAISSUE = "VoogaIssue";
	
	public VoogaAlert(String content){
		alert = new Alert(AlertType.ERROR);
		alert.setTitle(VOOGAISSUE);
		alert.setContentText(content);
	}
	
	public void showAlert(){
		alert.showAndWait();
	}
	
}
