package alerts;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class GroovyAlert extends VoogaAlert {
	private Alert alert;
public GroovyAlert(String content, String text){
	super(text);
	alert.setTitle("Custom Groovy Expression Error");
	
}
}
