package gameView.gameScreen;

import java.util.Collection;

import controller.WorldAnimator;
import gameEngine_interface.GameEngine;
import gameEngine_interface.RunnerTest;
import gameView.AbstractViewer;
import gameView.UIView;
import gameView.commands.AbstractCommand;
import gameView.displayComponents.UIDisplayComponent;
import gameView.tools.DisplayManager;
import gameView.tools.ResourceRetriever;
import gamedata.GameData;
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
	private GameData myData;
	private HBox myTopBox;
	private VBox myLeftBox;
	private VBox myRightBox;
	private StackPane myPane;
	private WorldAnimator myAnimation;
	private DisplayManager myDisplays;
	private Collection<AbstractCommand> myCommands;

	public GameScreen(UIView view, Stage s, WorldAnimator animation) {
		super(view, s);
		myCommands = getCommands(myName);
		myAnimation = animation;
		initializeBoxes();
		buildMainScene();
		myBP.applyCss();
		myBP.layout();
		myDisplays = new DisplayManager(this, UIView.DEFAULT_LOCATION+UIView.DEFAULT_BUTTONS,
				myPane.widthProperty(), myPane.heightProperty());
	}

	public Scene getScene() {
		
		//UNCOMMENT FOR NORMAL
//		myPane.getChildren().addAll(myAnimation.getScene().getRoot().getChildrenUnmodifiable());
//		System.out.println(myPane.getChildren());
//		System.out.println(myPane.getChildren().get(2).getTranslateX());
//		System.out.println(myPane.getChildren().get(2).getTranslateY());
//		
////
		
		//UNCOMMENT FOR RUNNERS TEST
		RunnerTest s = new RunnerTest(getView().getStage(), getView());
		myAnimation = s.getAnimator();
		myAnimation.start(s.getEngine().dummyLoad(), this);
		myAnimation.setKeys(myScene);
		myAnimation.giveEngine(s.getEngine());
//		Scene test = myAnimation.getScene();
//		myPane.getChildren().addAll(test.getRoot().getChildrenUnmodifiable());
		myBP.setCenter(myAnimation.getGroup());

		return myScene;
	}

	public void addData(GameData data) {
		myData = data;
		myAnimation.start(myData, this);
		//myManager = new ImageManager(myData);
	}

	public void runGame() {
		myAnimation.start();
	}
	
	public void pauseGame() {
		myAnimation.pause();
	}

	private void initializeBoxes() {
		myTopBox = setHBox("top", UIView.DEFAULT_SIZE.width);
		myPane = new StackPane();
		// myBottomBox = setHBox("bottom", UIView.DEFAULT_SIZE.width, 100);
		// myLeftBox = setSides("left", 100, UIView.DEFAULT_SIZE.height);
		// myRightBox = setSides("right", 100, UIView.DEFAULT_SIZE.height);
	}

	private void setSize(Pane box, String id, double width) {
		box.setId(id);
		//box.setPrefSize(width, 200);
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
//		myBP.setCenter(myPane);
		//myAnimation.setKeys(myScene);
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
