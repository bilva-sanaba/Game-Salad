package controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class VoogaAlert {
	private Alert alert;
	
	public VoogaAlert(String title, String content){
		alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setContentText(content);
	}
	
	public void showAlert(){
		alert.showAndWait();
	}
}
