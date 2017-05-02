package view.window;

import java.io.File;

import entity.SplashData;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import view.GUIBuilder;
import view.ImageChooser;
import view.UtilityFactory;

public class SplashScreenBuilderWindow implements Window{	
	private UtilityFactory myUtilF;
	private Text myFilePathDisplay;
	private String splashScreenImagePath;
	private String gameTitle;
	private String instructions;
	private TextField gameTitleInput;
	private TextField instructionsInput;
	private Stage myStage = new Stage();
	private VBox myRoot;
	
	public SplashScreenBuilderWindow(UtilityFactory utilF) {
		myUtilF = utilF;
		myFilePathDisplay = new Text("");
		myRoot = new VBox();
	}
	
	public SplashData createEntity() {
		myStage = new Stage();
		myRoot.setPadding(new Insets(10));
//		pickColor(root);
		selectText(myRoot);
		myFilePathDisplay = new Text("");
		Button chooseImageButton = new Button("Choose Image");
		chooseImageButton.setOnAction(e -> {
			ImageChooser ic = new ImageChooser();
			splashScreenImagePath = ic.chooseFile();
			myFilePathDisplay.setText(System.getProperty("user.dir") + File.separator + "images"+ File.separator + splashScreenImagePath);
		});
		Button okayButton = new Button("OkayButtonLabel");
		okayButton.setOnAction(e -> {
			gameTitle = gameTitleInput.getText();
			instructions = instructionsInput.getText();
			myStage.close();
		});
		myRoot.getChildren().add(okayButton);
		GridPane.setConstraints(okayButton, 0, 8);
		GridPane.setColumnSpan(okayButton, 2);
		myRoot.getChildren().add(myFilePathDisplay);
		GridPane.setConstraints(myFilePathDisplay, 0, 2);
		GridPane.setColumnSpan(myFilePathDisplay, 2);
		myRoot.getChildren().add(chooseImageButton);
		GridPane.setConstraints(chooseImageButton, 0, 0);
		GridPane.setColumnSpan(chooseImageButton, 2);
		Scene myScene = new Scene(myRoot, 350, 400);
		myScene.getStylesheets().add(GUIBuilder.RESOURCE_PACKAGE + GUIBuilder.STYLESHEET);
		myStage.setScene(myScene);
		myStage.setTitle("Customize Splash Screen");
		myStage.showAndWait();

//		beneath here is a splash entity which you instantiate with all the values you just found at the x's
		SplashData s = new SplashData(1, gameTitle, instructions, splashScreenImagePath);
		return s;
	}
	
	private void selectText(Pane root){
		Label getGameTitle = new Label("Game Title:");
		gameTitleInput = new TextField();
		root.getChildren().addAll(getGameTitle, gameTitleInput);
		
		Label getInstructions = new Label("Instructions:");
		instructionsInput = new TextField();
		root.getChildren().addAll(getInstructions, instructionsInput);
	}

	@Override
	public void openWindow() {
		myStage.show();
	}
}
