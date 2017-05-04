package gameView.tools;

import gameView.UIView;

import java.awt.Dimension;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameChooser {
	

	private Stage myStage;
	private static final String FILE_EXTENSION = ".xml";
	private static final String GAME_PATH = "games" + File.separator;
	private ArrayList<String> myFiles;
	private VBox myBox;
	private ArrayList<HBox> mySubBoxes;
	private String myChosenFile;
	private static final Dimension SCENE_DIMENSIONS = new Dimension(UIView.DEFAULT_SIZE.width/2, UIView.DEFAULT_SIZE.height/2);
	
	public GameChooser(Stage s) {
		myStage = new Stage();
		myStage.setTitle("Pick A Game");
		myFiles = new ArrayList<String>();
		mySubBoxes = new ArrayList<HBox>();
		myBox = new VBox();
		myBox.setId("main");
		myChosenFile = null;
	} 

	/**
	 * Creates a pop-up using the ScrollablePopup to allow the user to select a file from a specific file path (GAME_PATH).
	 * Creates a new Stage and returns the new stage to allow the user to show the stage at their preference. 
	 * @return The pop-up file chooser stage
	 */
	public Stage selectFile() {
		File directory = new File(GAME_PATH);
		String[] allFiles = directory.list();
		for (String each: allFiles) {
			if(each.endsWith(FILE_EXTENSION)) { 
				myFiles.add(each); 
			} 
		} 
		return makeStage();
	}
	
	public Stage selectFile(Collection<String> files) {
		myFiles.addAll(files);
		return makeStage();
	}
	
	private Stage makeStage() {
		makeVBox();
		ScrollablePopup myPopup = new ScrollablePopup("File Chooser", new ResourceRetriever().
				getStyleSheets(this, GameChooser.class.getSimpleName()),
				myBox, null, new Dimension(SCENE_DIMENSIONS.width, SCENE_DIMENSIONS.height));
		myStage.setScene(myPopup.getScene());
		return myStage; 
	}
	/**
	 * @return the string of the chosen file in its full path
	 */
	public String getFile() {
		try {
			String[] test = myChosenFile.split("[\\\\/]");
			return test[test.length-1];
		} catch (Exception e) {
			throw new FrontEndException("No File Chosen");
		}
	}
	
	private void makeVBox() {
		int count = 0;
		Iterator<String> it = myFiles.iterator();
		while (count <= myFiles.size()/3) {
			HBox newHBox = new HBox();
			for (int k : new int[]{0, 1, 2}) {
				if (it.hasNext()) {
					String current = it.next();
					String[] split = current.split("\\."); 
					newHBox.getChildren().add(makeButton(split[0]));
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
		newButton.setPrefWidth((SCENE_DIMENSIONS.width/3)-20);
		newButton.setPrefHeight(newButton.getPrefWidth()*1.2);
		return newButton;
	} 
}
