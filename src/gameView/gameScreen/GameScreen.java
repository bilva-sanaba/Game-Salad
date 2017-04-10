package gameView.gameScreen;

import java.util.ArrayList;
import java.util.Collection;

import controller.WorldAnimator;
import entity.restricted.IRestrictedEntityManager;
import gameView.ICommandGameView;
import gameView.ICommandView;
import gameView.ImageManager;
import gameView.UIDisplayComponent;
import gameView.UIView;
import gameView.commands.AbstractCommand;
import gameView.commands.LoadCommand;
import gameView.commands.MakeCommand;
import gameView.commands.PauseCommand;
import gameView.commands.PlayCommand;
import gameView.commands.PreferencesCommand;
import gameView.commands.SaveCommand;
import gameView.tools.ButtonFactory;
import gameView.tools.DisplayManager;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class GameScreen implements ICommandGameView {

	private Scene myScene;
	private BorderPane myBP;
	private UIView myView;
	private IRestrictedEntityManager myEntities;
	private ImageManager myManager;
	private ButtonFactory myButtonFactory;
	private HBox myTopBox;
	private HBox myBottomBox;
	private VBox myLeftBox;
	private VBox myRightBox;
	private WorldAnimator myAnimation;
	private DisplayManager myDisplays;

	public GameScreen(UIView view) {
		myView = view;
		myDisplays = new DisplayManager(this);
		myButtonFactory = new ButtonFactory(view, view.DEFAULT_BUTTONS);
		myAnimation = new WorldAnimator();
		initializeBoxes();
		buildScene();
	}

	public Scene getScene() {
		return myScene;
	}

	public void addEntity(IRestrictedEntityManager entity) {
		myEntities = entity;
		myManager = new ImageManager(entity);
	}

	public void runGame() {
		//call play in worldanimator
	}
	
	public void pauseGame() {
		//call pause in world animator
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

	private void buildScene() {
		myBP = new BorderPane(null, myTopBox, myRightBox, myBottomBox,
				myLeftBox);
		myBP.setId("main");
		myScene = new Scene(myBP, UIView.DEFAULT_SIZE.width, UIView.DEFAULT_SIZE.height);
		myScene.getStylesheets().add(this.getClass().getResource("/resources/GameView.css").toExternalForm());
		getCommands().stream()
			.forEach(c -> {
				myTopBox.getChildren().add(makeButton(c));
			});
		
	}

	private Button makeButton(AbstractCommand command) {
		return myButtonFactory.makeButton(command);
	}
	

	private Collection<AbstractCommand> getCommands() {
		Collection<AbstractCommand> list = new ArrayList<AbstractCommand>();
		list.add(new PlayCommand(this));
		list.add(new PauseCommand(this));
		list.add(new SaveCommand(this));
		list.add(new PreferencesCommand(this));
		return list;
	}

	@Override
	public void saveGame() {
		myView.saveGame();
	}

	@Override
	public DisplayManager getComponents() {
		return myDisplays;
	}
	
	public void removeComponent(UIDisplayComponent toRemove) {
		//my game view remove toRemove
	}
	
	public void addComponent(UIDisplayComponent toAdd) {
		//my game view add toAdd
	}

}
