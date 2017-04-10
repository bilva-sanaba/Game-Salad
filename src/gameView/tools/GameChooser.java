package gameView.tools;

import gameView.UIView;

import java.awt.Dimension;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class GameChooser {

	private Stage myStage;
	private static final String FILE_EXTENSION = "*.xlm";
	private static final String GAME_PATH = "/Users/Henry/Documents/workspace-CS308/voogasalad_raindrop/games";
	private ArrayList<String> myFiles;
	private VBox myBox;
	private ArrayList<HBox> mySubBoxes;
	private String myChosenFile;
	private static final Dimension SCENE_DIMENSIONS = new Dimension(UIView.DEFAULT_SIZE.width/2, UIView.DEFAULT_SIZE.height/2);
	
	public GameChooser(Stage s) {
		//myStage = s;
		myStage = new Stage();
		myFiles = new ArrayList<String>();
		mySubBoxes = new ArrayList<HBox>();
		myBox = new VBox();
		myBox.setId("main");
		myChosenFile = null;
	} 

	/**
	 * 
	 * @param extension
	 * @return The pop-up file chooser
	 */
	public Stage selectFile() {
		String game = null;
//		FileChooser chooser = new FileChooser(); 
//		chooser.setTitle("Open Data File");
//		chooser.setInitialDirectory(new File(System.getProperty("user.dir")));
//		chooser.getExtensionFilters().setAll(
//				new ExtensionFilter("Text Files", FILE_EXTENSION));
//		File dataFile = chooser.showOpenDialog(myStage);
		File directory = new File(GAME_PATH);
		String[] allFiles = directory.list();
		for (String each: allFiles) {
			if(each.endsWith(".xml")||each.endsWith(".XML")) { 
				myFiles.add(each); 
			} 
		} 
		makeVBox();
		ScrollablePopup myPopup = new ScrollablePopup("File Chooser", "/resources/GameChooser.css", myBox, null, 
				new Dimension(SCENE_DIMENSIONS.width, SCENE_DIMENSIONS.height));
		myStage.setScene(myPopup.getScene());
		return myStage; 
	}
	
	public String getFile() {
		String[] split = myChosenFile.split("\\.");
		return split[0]; 
	}
	
	private void makeVBox() {
		int count = 0;
		Iterator<String> it = myFiles.iterator();
		while (count <= myFiles.size()/3) {
			HBox newHBox = new HBox();
			for (int k : new int[]{0, 1, 2}) {
				if (it.hasNext()) {
					String current = it.next();
					newHBox.getChildren().add(makeButton(current));
				}
			}
			count += 1;
			newHBox.setId("subbox");
			mySubBoxes.add(newHBox);
		}
		mySubBoxes.stream()
			.forEach(c -> myBox.getChildren().add(c));
	}
	 
	private Button makeButton(String name) {
		Button newButton = new Button(name);
		newButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				myChosenFile = name;
				myStage.close();
			}
		});
		newButton.setContentDisplay(ContentDisplay.TOP);
		newButton.setPrefWidth((SCENE_DIMENSIONS.width/3)-40);
		newButton.setPrefHeight(newButton.getPrefWidth()*1.2);
		return newButton;
	} 
}
