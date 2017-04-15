package view;

import entity.Entity;
import entity.SplashEntity;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SplashScreenBuilderWindow {	
	private UtilityFactory utilF;
	private Text myFilePathDisplay;
	private String splashScreenImagePath;
	private String gameTitle;
	private String instructions;
	private TextField gameTitleInput;
	private TextField instructionsInput;
	
	public SplashScreenBuilderWindow() {
		myFilePathDisplay = new Text("");
	}
	
//	This shit needs to be refactored
	public SplashEntity openWindow() {
		Stage stage = new Stage();
		GridPane root = new GridPane();
		root.setPadding(new Insets(10));
		root.setHgap(20);
		root.setVgap(20);
//		pickColor(root);
		selectText(root);
		myFilePathDisplay = new Text("");
		Button chooseImageButton = new Button("Choose Image");
		chooseImageButton.setOnAction(e -> {
			ImageChooser ic = new ImageChooser();
			splashScreenImagePath = ic.chooseFile();
			myFilePathDisplay.setText(splashScreenImagePath);
		});
		Button okayButton = new Button("OkayButtonLabel");
		okayButton.setOnAction(e -> {
			gameTitle = gameTitleInput.getText();
			instructions = instructionsInput.getText();
			stage.close();
		});
		root.getChildren().add(okayButton);
		GridPane.setConstraints(okayButton, 0, 8);
		GridPane.setColumnSpan(okayButton, 2);
		root.getChildren().add(myFilePathDisplay);
		GridPane.setConstraints(myFilePathDisplay, 0, 2);
		GridPane.setColumnSpan(myFilePathDisplay, 2);
		root.getChildren().add(chooseImageButton);
		GridPane.setConstraints(chooseImageButton, 0, 0);
		GridPane.setColumnSpan(chooseImageButton, 2);
		Scene scene = new Scene(root, 350, 300);
		stage.setScene(scene);
		stage.setTitle("Customize Splash Screen");
		stage.showAndWait();
		
//		beneath here is a splash entity which you instantiate with all the values you just found at the x's
		SplashEntity s = new SplashEntity(1, gameTitle, instructions, splashScreenImagePath);
		return s;
	}
	
//	private void pickColor(Pane root){
//		Label backgroundColorTitle = new Label("Background Color");
//		GridPane.setConstraints(backgroundColorTitle, 0, 0);
//		ColorPicker colorPicker = new ColorPicker();
//		GridPane.setConstraints(colorPicker, 1, 1);
//		Circle circle = new Circle(50);
//		GridPane.setConstraints(circle, 0, 1);
//		circle.setFill(colorPicker.getValue());
//		colorPicker.setOnAction(e -> circle.setFill(colorPicker.getValue()));
//		root.getChildren().addAll(backgroundColorTitle, circle, colorPicker);	
//	}
	
	private void selectText(Pane root){
		Label getGameTitle = new Label("Game Title:");
		GridPane.setConstraints(getGameTitle, 0, 4);
		gameTitleInput = new TextField();
		GridPane.setConstraints(gameTitleInput, 3, 4);
		root.getChildren().addAll(getGameTitle, gameTitleInput);
		
		Label getInstructions = new Label("Instructions:");
		GridPane.setConstraints(getInstructions, 0, 6);
		instructionsInput = new TextField();
		GridPane.setConstraints(instructionsInput, 3, 6);
		root.getChildren().addAll(getInstructions, instructionsInput);
	}
}
