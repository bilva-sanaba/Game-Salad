package gameView.gameScreen;

import java.util.Collection;

import controller.VoogaAlert;
import controller.WorldAnimator;
import gameEngine_interface.GameEngine;
import gameEngine_interface.RunnerTest;
import gameView.AbstractViewer;
import gameView.UIView;
import gameView.commands.AbstractCommand;
import gameView.displayComponents.UIDisplayComponent;
import gameView.gameDataManagement.GameDataManager;
import gameView.tools.DisplayManager;
import gameView.tools.ResourceRetriever;
import gameView.userInput.IUserInputData;
import gamedata.GameData;
import gamedata.IRestrictedGameData;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameScreen extends AbstractViewer implements IGameScreenDisplays, IGameScreenEntity {

	private static final String myName = GameScreen.class.getSimpleName();
	private Scene myScene;
	private BorderPane myBP;
	private GameDataManager myData;
	private HBox myTopBox;
	private VBox myLeftBox;
	private VBox myRightBox;
	private StackPane myPane;
	private WorldAnimator myAnimation;
	private DisplayManager myDisplays;
	private Collection<AbstractCommand> myCommands;
	private VoogaAlert myAlert;

	public GameScreen(UIView view, Stage s, IUserInputData input, WorldAnimator animation) {
		super(view, s, input);
		myCommands = getCommands(myName);
		myAnimation = animation;
		initializeBoxes();
		buildMainScene();
		myBP.applyCss();
		myBP.layout();
	}

	public Scene getScene() {
		myPane.getChildren().add(myAnimation.getGroup());
		myAnimation.setKeys(myScene);
		return myScene;
	}

	public void addData(GameDataManager data) {
		myData = data;
		myDisplays = new DisplayManager(this, UIView.DEFAULT_LOCATION+UIView.DEFAULT_BUTTONS,
				myPane.widthProperty(), myPane.heightProperty(), myData.getData());

		try {
			myAnimation.start(myData.getData(), this);
		} catch (ClassNotFoundException e) {
			myAlert = new VoogaAlert(e.getMessage());
			myAlert.showAlert();
		}
	}

	public void runGame() {
		myData.getMusic().playMusic();
		myAnimation.start();
	}
	
	public void pauseGame() {
		myData.getMusic().stopMusic();
		myAnimation.pause();
	}

	private void initializeBoxes() {
		myTopBox = setHBox("top", UIView.DEFAULT_SIZE.width);
		myPane = new StackPane();
	}

	private void setSize(Pane box, String id, double width) {
		box.setId(id);
		box.setPrefWidth(width);
	}

	private HBox setHBox(String id, double width) {
		HBox box = new HBox(8);
		setSize(box, id, width);
		return box;
	}

	private VBox setSides(String id, double width, double height) {
		VBox box = new VBox(8);
		setSize(box, id, width);
		return box;
	}

	private void buildMainScene() {
		myBP = new BorderPane(null, myTopBox, myRightBox, null,
				myLeftBox);
		myBP.setId("main");
		myScene = new Scene(myBP, UIView.DEFAULT_SIZE.width, UIView.DEFAULT_SIZE.height);
		myScene.getStylesheets().add(new ResourceRetriever().getStyleSheets(this,myName));
		myCommands.stream()
			.forEach(c -> {
				myTopBox.getChildren().add(makeButton(c));
			});
		myBP.setCenter(myPane);
	}

	@Override
	public DisplayManager getComponents() {
		System.out.println("GAMESCREEN");
		return myDisplays; 
	}
	
	public void removeComponent(UIDisplayComponent toRemove) {
		myPane.getChildren().remove(toRemove.getDisplay());
	} 
	
	public void addComponent(UIDisplayComponent toAdd) {
		myPane.getChildren().add(toAdd.getDisplay());
	} 
	
	@Override
	public void loadGame(String filePath) {
		myPane.getChildren().clear();
		myDisplays.addAllActive();
		myAnimation.clearRoot();
		super.loadGame(filePath);
	}

	@Override
	public void addEntity(ImageView add) {
		myPane.getChildren().add(add);
		
	}

	@Override
	public void removeEntity(ImageView remove) {
		myPane.getChildren().remove(remove);
	}

}