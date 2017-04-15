package gameView.gameScreen;

import java.util.Collection;

import controller.WorldAnimator;
import entity.restricted.IRestrictedEntityManager;
import gameEngine_interface.GameEngine;
import gameEngine_interface.RunnerTest;
import gameView.AbstractViewer;
import gameView.ImageManager;
import gameView.UIView;
import gameView.commands.AbstractCommand;
import gameView.displayComponents.UIDisplayComponent;
import gameView.tools.DisplayManager;
import gameView.tools.ResourceRetriever;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class GameScreen extends AbstractViewer {

	private static final String myName = GameScreen.class.getSimpleName();
	private Scene myScene;
	private BorderPane myBP;
	private IRestrictedEntityManager myEntities;
	private ImageManager myManager;
	private HBox myTopBox;
	private VBox myLeftBox;
	private VBox myRightBox;
	private StackPane myPane;
	private WorldAnimator myAnimation;
	private DisplayManager myDisplays;
	private Collection<AbstractCommand> myCommands;

	public GameScreen(UIView view, WorldAnimator animation) {
		super(view);
		myCommands = getCommands(myName);
		//myAnimation = animation;
		initializeBoxes();
		buildMainScene();
		myBP.applyCss();
		myBP.layout();
		myDisplays = new DisplayManager(this, UIView.DEFAULT_LOCATION+UIView.DEFAULT_BUTTONS,
				myPane.widthProperty(), myPane.heightProperty());
	}

	public Scene getScene() {
//		myPane.getChildren().addAll(myAnimation.getScene().getRoot().getChildrenUnmodifiable());
//		System.out.println(myPane.getChildren());
//		System.out.println(myPane.getChildren().get(2).getTranslateX());
//		System.out.println(myPane.getChildren().get(2).getTranslateY());
		
//
		myAnimation = new RunnerTest(getView().getStage()).getAnimator();
		myAnimation.setKeys(myScene);
		Scene test = myAnimation.getScene();
		myPane.getChildren().add(test.getRoot());
		
		//USED FOR CHECKING CHILDREN
//		for (Node each:myPane.getChildren()) {
//			if (each == test.getRoot()) {
//				for (Node f: ((Group) each).getChildren()) {
//					System.out.println(f);
//					System.out.println(((ImageView) f).getX());
//					System.out.println(((ImageView) f).getY());
//				}
//			}
//			System.out.println(each.getTranslateX());
//			System.out.println(each.getTranslateY());
//			
//		}
		//myBP.setCenter(test.getRoot());
		
		
		return myScene;
	}

	public void addEntity(IRestrictedEntityManager entity) {
		myEntities = entity;
		myManager = new ImageManager(entity);
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
		myBP.setCenter(myPane);
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
	
	public void addGameEngine(GameEngine game) {
		myAnimation.start(game);
	}
	
	@Override
	public void loadGame(String filePath) {
		//myPane.getChildren().clear();
		//myDisplays.addAllActive();
		//myAnimation.clearRoot();
		super.loadGame(filePath);
	}

}
