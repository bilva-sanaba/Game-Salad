package alerts;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class GroovyAlert {
	private Alert alert;
public GroovyAlert(String title,String content){
	alert=new Alert(AlertType.ERROR);
	alert.setTitle(title);
	alert.setContentText(content);
	
}
/**
 * displays alert
 */
public void showAlert(){
	alert.showAndWait();
}
}
