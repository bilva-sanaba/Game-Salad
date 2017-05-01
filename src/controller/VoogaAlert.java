package controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

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
