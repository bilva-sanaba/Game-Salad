package gameView.tools;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class ImageButton extends Button {
	
	private static final String LABEL = "Choose an Image";
	
	private Stage myStage;
	
	public ImageButton(Stage s, String graphicURL) {
		super(LABEL);
		myStage = s;
		setPrefSize(150, 150);
		makeImageBox();
		if (graphicURL != null) {
			setButtonGraphic(graphicURL);
		}
	}
	
	private void makeImageBox() {
		setWrapText(true);
		setId("imagebutton");
		setPrefSize(150, 150);
		this.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				File userImage = getUserImage();
				if (userImage != null) {
					setButtonGraphic(userImage.toURI().toString());
				}
			}
		});
	}
	
	private void setButtonGraphic(String filePath) {
		setText("");  
		ImageViewContainer toAdd = new ImageViewContainer(new Image(filePath), filePath);
		toAdd.setFitWidth(getPrefWidth());
		toAdd.setFitHeight(getPrefHeight());
		setGraphic(toAdd);
	}
	
	
	private File getUserImage() {
		 FileChooser fileChooser = new FileChooser();
		 fileChooser.setTitle("Choose User Image");
		 fileChooser.getExtensionFilters().addAll(
		         new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
		 File selectedFile = fileChooser.showOpenDialog(myStage);
		 return selectedFile;
	}
}
