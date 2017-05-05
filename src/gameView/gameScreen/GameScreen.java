package gameView.gameScreen;

import java.util.Collection;
import controller.VoogaAlert;
import controller.WorldAnimator;
import gameView.AbstractViewer;
import gameView.UIView;
import gameView.UIViewInterface;
import gameView.commands.AbstractCommand;
import gameView.displayComponents.UIDisplayComponent;
import gameView.gameDataManagement.GameDataManager;
import gameView.tools.DisplayManager;
import gameView.tools.ResourceRetriever;
import gameView.userInput.IUserInputData;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GameScreen extends AbstractViewer implements IGameScreenDisplays, IGameScreenEntity {

	private static final String myName = GameScreen.class.getSimpleName();
	private Scene myScene;
	private BorderPane myBP;
	private HBox myTopBox;
	private StackPane myPane;
	private WorldAnimator myAnimation;
	private DisplayManager myDisplays;
	private Collection<AbstractCommand> myCommands;
	private VoogaAlert myAlert;
	private final String VOOGAISSUE = "Vooga Issue";
	public GameScreen(UIViewInterface view, Stage s, IUserInputData input) {
		super(view, s, input);
		myCommands = getCommands(myName);
		initializeBoxes();
		buildMainScene();
		myBP.applyCss();
		myBP.layout();
	}

	@Override
	public Scene getScene() {
		myPane.getChildren().add(myAnimation.getGroup());
		myDisplays.addAllActive();
		myAnimation.setKeys(myScene);
		return myScene;
	}

	@Override
	public void addData(GameDataManager data) {
		checkWorldAnimator();
		super.addData(data);
		reset();
		myDisplays = new DisplayManager(this, UIView.DEFAULT_LOCATION+UIView.DEFAULT_BUTTONS,
				myScene.widthProperty(), myScene.heightProperty(), getGameData().getData());
		try {
			myAnimation = new WorldAnimator(getView());
			myAnimation.start(getGameData().getData(), this);
		} catch (ClassNotFoundException e) {
			myAlert = new VoogaAlert(e.getMessage());
			myAlert.showAlert();
		}
	}

	@Override
	public DisplayManager getComponents() {
		return myDisplays;
	}

	@Override
	public void removeComponent(UIDisplayComponent toRemove) {
		myPane.getChildren().remove(toRemove.getDisplay());
	}
	@Override
	public void addComponent(UIDisplayComponent toAdd) {
		myPane.getChildren().add(toAdd.getDisplay());
	}

	public void checkWorldAnimator() {
		try {
			pauseGame();
		} catch (Exception e) {
			System.out.println("NO WORLD ANIMATOR");
		}
	}

	@Override
	protected void setBackground(String background){
		myBP.getCenter().setStyle(String.format(
				"-fx-background-image: url(\"%s\");"
				+ "-fx-background-repeat: stretch;"
				+ "-fx-background-position: center center;"
				+ "-fx-background-size: cover;", background));
	}

	@Override
	protected Pane getButtonContainer() {
		return myTopBox;
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

	private void buildMainScene() {
		myBP = new BorderPane();
		myBP.setId("main");
		myScene = new Scene(myBP, UIView.DEFAULT_SIZE.width, UIView.DEFAULT_SIZE.height);
		myScene.getStylesheets().add(new ResourceRetriever().getStyleSheets(this,myName));
		myCommands.stream()
			.forEach(c -> {
				myTopBox.getChildren().add(makeButton(c));
			});
		setUserCommand();
		myBP.setCenter(myPane);  
		myBP.setTop(myTopBox);

	}

	private void reset() {
		myPane.getChildren().clear();
	}

	/**
	 * THE FOLLOWING METHODS ARE FOR COMMANDS
	 */

	@Override
	public void runGame() {
		getGameData().getMusic().playMusic();
		myAnimation.start();
	}

	@Override
	public void pauseGame() {
		getGameData().getMusic().stopMusic();
		myAnimation.pause();
	}

}