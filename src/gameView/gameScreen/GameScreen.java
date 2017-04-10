package gameView.gameScreen;

import java.util.Collection;

import controller.WorldAnimator;
import entity.restricted.IRestrictedEntityManager;
import gameView.AbstractViewer;
import gameView.ICommandView;
import gameView.ImageManager;
import gameView.UIDisplayComponent;
import gameView.UIView;
import gameView.commands.AbstractCommand;
import gameView.tools.DisplayManager;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class GameScreen extends AbstractViewer {

	private static final String myName = GameScreen.class.getSimpleName();
	private Scene myScene;
	private BorderPane myBP;
	private IRestrictedEntityManager myEntities;
	private ImageManager myManager;
	private HBox myTopBox;
	private HBox myBottomBox;
	private VBox myLeftBox;
	private VBox myRightBox;
	private WorldAnimator myAnimation;
	private DisplayManager myDisplays;
	private Collection<AbstractCommand> myCommands;

	public GameScreen(UIView view, WorldAnimator animation) {
		super(view);
		myCommands = getCommands(myName);
		myDisplays = new DisplayManager(this, UIView.DEFAULT_LOCATION+UIView.DEFAULT_BUTTONS);
		myAnimation = animation;
		initializeBoxes();
		buildMainScene();
	}

	public Scene getScene() {
		//myBP.setCenter(myAnimation.getScene().getRoot());
		return myScene;
	}

	public void addEntity(IRestrictedEntityManager entity) {
		myEntities = entity;
		myManager = new ImageManager(entity);
	}

	public void runGame() {
		System.out.println("GAMESCREEN");
		myAnimation.start();
	}
	
	public void pauseGame() {
		System.out.println("GAMESCREEN");
		myAnimation.pause();
	}

	private void initializeBoxes() {
		myTopBox = setHBox("top", UIView.DEFAULT_SIZE.width, 50);
		// myBottomBox = setHBox("bottom", UIView.DEFAULT_SIZE.width, 100);
		// myLeftBox = setSides("left", 100, UIView.DEFAULT_SIZE.height);
		// myRightBox = setSides("right", 100, UIView.DEFAULT_SIZE.height);
	}

	private void setSize(Pane box, String id, double width, double height) {
		box.setId(id);
		box.setPrefSize(width, height);
	}

	private HBox setHBox(String id, double width, double height) {
		HBox box = new HBox(8);
		setSize(box, id, width, height);
		return box;
	}

	private VBox setSides(String id, double width, double height) {
		VBox box = new VBox(8);
		setSize(box, id, width, height);
		return box;
	}

	private void buildMainScene() {
		myBP = new BorderPane(null, myTopBox, myRightBox, myBottomBox,
				myLeftBox);
		myBP.setId("main");
		myScene = new Scene(myBP, UIView.DEFAULT_SIZE.width, UIView.DEFAULT_SIZE.height);
		myScene.getStylesheets().add(getStyleSheets(this, myName));
		myCommands.stream()
			.forEach(c -> {
				myTopBox.getChildren().add(makeButton(c));
			});
		
	}

	@Override
	public DisplayManager getComponents() {
		System.out.println("GAMESCREEN");
		return myDisplays;
	}
	
	public void removeComponent(UIDisplayComponent toRemove) {
		myBP.getChildren().remove(toRemove);
	}
	
	public void addComponent(UIDisplayComponent toAdd) {
		myBP.getChildren().add(toAdd);
	}

}
