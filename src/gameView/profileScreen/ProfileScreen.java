	package gameView.profileScreen;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import gameView.AbstractViewer;
import gameView.UIView;
import gameView.UIViewInterface;
import gameView.commands.AbstractCommand;
import gameView.commands.SignOutCommand;
import gameView.commands.UserLoadCommand;
import gameView.tools.GameEntry;
import gameView.tools.ImageButton;
import gameView.tools.ImageViewContainer;
import gameView.tools.ResourceRetriever;
import gameView.tools.TableFactory;
import gameView.userInput.IUserInputData;
import gameView.userManagement.IUserManager;

public class ProfileScreen extends AbstractViewer {

	private static final String myName = "ProfileScreen";
	private static final Double WIDTH = UIView.DEFAULT_SIZE.width/2.5;
	private static final Double HEIGHT = UIView.DEFAULT_SIZE.height/1.25;
	
	private Scene myScene;
	private IUserManager myData;
	private VBox myBox;
	private ImageButton myImageButton;
	
	public ProfileScreen(UIViewInterface view, Stage s, IUserInputData input) {
		super(view, s, input);
		myData = getUserManager();
		setStageReaction();
		makeScene();
	}

	@Override
	public Scene getScene() {
		return myScene;
	}
	
	private void setStageReaction() {
		getStage().setOnCloseRequest(new EventHandler<WindowEvent>() {
	          public void handle(WindowEvent we) {
	        	  try {
	        		  myData.getCurrentUser().changePicture(((ImageViewContainer) myImageButton.getGraphic()).getPath());
	        	  } catch (Exception e) {
	        	  } 
	          }
	      });    
	}
	
	private void makeScene() {
		myBox = makeVBox();
		myScene = new Scene(myBox, WIDTH, HEIGHT);
		myScene.getStylesheets().add(new ResourceRetriever().getStyleSheets(this,myName));
	}
	
	private VBox makeVBox() {
		myImageButton = new ImageButton(getStage(), myData.getCurrentUser().getProfilePicture() == null? 
				null: myData.getCurrentUser().getProfilePicture().getPath());
		Label name = new Label(myData.getCurrentUser().getName());
		HBox gameStats = makeStats();
		HBox loadGames = makeCommandBox(new UserLoadCommand(this));
		HBox options = makeCommandBox(new SignOutCommand(this));
		VBox toReturn = new VBox(20, myImageButton, name, gameStats, loadGames, options);  
		toReturn.setAlignment(Pos.CENTER);
		return toReturn;
	}
	
	private HBox makeCommandBox(AbstractCommand... command) {
		HBox optionsBox = new HBox(20);
		for (AbstractCommand each: command) {
			Button signOut = makeButton(each);
			optionsBox.getChildren().add(signOut);
		}
		optionsBox.setAlignment(Pos.CENTER);
		return optionsBox;
	}
	
	private HBox makeStats() {
		TableView<GameEntry> gamesBox = makeTable(getPointEntry(myData.getCurrentUser().getGameScores()), "Game", "Points");
		TableView<GameEntry> achievementBox= makeTable(getAchievementsEntry(myData.getCurrentUser().getAchievements()), "Achievements");
		HBox statsBox = new HBox(0, gamesBox, achievementBox);
		statsBox.setPrefWidth(WIDTH);
		statsBox.setAlignment(Pos.CENTER_RIGHT);
		return statsBox;
	}
	
	private ArrayList<GameEntry> getPointEntry(Iterator<String> dataIt) {
		ArrayList<GameEntry> data = new ArrayList<GameEntry>();
		while (dataIt.hasNext()) {
			String game = dataIt.next();
			data.add(new GameEntry(game, myData.getCurrentUser().getPointValue(game)));
		}
		return data;
	}
	
	private ArrayList<GameEntry> getAchievementsEntry(Iterator<String> dataIt) {
		ArrayList<GameEntry> data = new ArrayList<GameEntry>();
		while (dataIt.hasNext()) {
			String achievement = dataIt.next();
			data.add(new GameEntry(achievement, null));
		}
		return data;
	}
	
	private TableView<GameEntry> makeTable(ArrayList<GameEntry> data, String... args) {
		TableFactory factory = new TableFactory(WIDTH/2, HEIGHT/4); 
		return factory.getTable(data, args);
	}

	@Override
	protected void setBackground(String s) {
	}

	@Override
	protected Pane getButtonContainer() {
		return myBox;
	}
}
