	package gameView.profileScreen;

import java.util.Iterator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import gameView.AbstractViewer;
import gameView.UIView;
import gameView.commands.FacebookPostCommand;
import gameView.commands.SignOutCommand;
import gameView.tools.GameEntry;
import gameView.tools.ImageButton;
import gameView.tools.ResourceRetriever;
import gameView.userInput.IUserInputData;
import gameView.userManagement.IUserManager;
import gameView.userManagement.UserData;

public class ProfileScreen extends AbstractViewer {

	private static final String myName = "ProfileScreen";
	
	private Scene myScene;
	private IUserManager myData;
	private VBox myBox;
	private HBox myGameStats;
	
	public ProfileScreen(UIView view, Stage s, IUserInputData input, IUserManager data) {
		super(view, s, input);
		myData = data;
		setStageReaction();
		makeScene();
	}

	@Override
	public Scene getScene() {
		return myScene;
	}
	
	@Override
	public IUserManager getUserManager() {
		return myData;
	}
	
	private void setStageReaction() {
		getStage().setOnCloseRequest(new EventHandler<WindowEvent>() {
	          public void handle(WindowEvent we) {
	              //SAVE UPDATED PREFS
	          }
	      });    
	}
	
	private void makeScene() {
		myBox = makeVBox();
		myScene = new Scene(myBox, UIView.DEFAULT_SIZE.width/2.5, UIView.DEFAULT_SIZE.height/1.25);
		myScene.getStylesheets().add(new ResourceRetriever().getStyleSheets(this,myName));
		
	}
	
	private VBox makeVBox() {
		ImageButton image = new ImageButton(getStage(), myData.getCurrentUser().getProfilePicture() == null? 
				null: myData.getCurrentUser().getProfilePicture().getPath());
		Label name = new Label(myData.getCurrentUser().getName());
		HBox gameStats = makeStats();
		VBox.setMargin(gameStats, new Insets(0, 10, 0, 0));
		HBox options = makeOptionBox();
		VBox toReturn = new VBox(20, image, name, gameStats, options);  
		toReturn.setAlignment(Pos.CENTER);
		return toReturn;
	}
	
	private HBox makeOptionBox() {
		Button signOut = makeButton(new SignOutCommand(this)); 
		Button facebookPost = makeButton(new FacebookPostCommand(this));
		System.out.println(facebookPost.getId());
		HBox optionsBox = new HBox(20, signOut, facebookPost);
		optionsBox.setAlignment(Pos.CENTER);
		return optionsBox;
	}
	
	private HBox makeStats() {
		TableView<GameEntry> gamesBox = makeGameBox();
		//VBox achievement = makeAchievements();
		HBox statsBox = new HBox(10, gamesBox);
		return statsBox;
	}
	
	private TableView<GameEntry> makeGameBox() {
		ObservableList<GameEntry> data =
	            FXCollections.observableArrayList();
		TableView<GameEntry> gameBox = new TableView<GameEntry>();
		gameBox.setPrefWidth(200);
		gameBox.setEditable(true);
		TableColumn<GameEntry, String> first = new TableColumn<GameEntry, String>("Game");
        first.setMinWidth(100);
		first.setCellValueFactory(
                new PropertyValueFactory<GameEntry, String>("myFirstValue"));
		TableColumn<GameEntry, String> second = new TableColumn<GameEntry, String>("Points");
		second.setMinWidth(100);
		second.setCellValueFactory(
                new PropertyValueFactory<GameEntry, String>("mySecondValue"));

		gameBox.setItems(data);
		gameBox.getColumns().setAll(first, second);
		Iterator<String> dataIt = myData.getCurrentUser().getGames();
		while (dataIt.hasNext()) {
			String game = dataIt.next();
			System.out.println(game);
			data.add(new GameEntry(game, myData.getCurrentUser().getPointValue(game)));
		}
		gameBox.setPrefHeight(200);
		return gameBox; 
	}
}
