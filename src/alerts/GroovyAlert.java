package alerts;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class GroovyAlert extends VoogaError {
	private Alert alert;
public GroovyAlert(String content, String text){
	super(content,text);
	alert.setTitle("Custom Groovy Expression Error");
	
}
}
