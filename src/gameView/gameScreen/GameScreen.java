package gameView.gameScreen;

import entity.restricted.IRestrictedEntityManager;
import gameView.ImageManager;
import gameView.UIView;
import gameView.commands.AbstractCommand;
import gameView.tools.ButtonFactory;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class GameScreen {
	
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
	
	
	public GameScreen(UIView view) {
		myView = view;
		myButtonFactory = new ButtonFactory(view, view.DEFAULT_BUTTONS);
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
		
	}

	private void initializeBoxes() {
		myTopBox = setHBox("top", UIView.DEFAULT_SIZE.width, 50);
		//myBottomBox = setHBox("bottom", UIView.DEFAULT_SIZE.width, 100);
		//myLeftBox = setSides("left", 100, UIView.DEFAULT_SIZE.height);
		//myRightBox = setSides("right", 100, UIView.DEFAULT_SIZE.height);
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
		myBP = new BorderPane(null, myTopBox, myRightBox, myBottomBox, myLeftBox);
		myBP.setId("main");
		myScene = new Scene(myBP, UIView.DEFAULT_SIZE.width, UIView.DEFAULT_SIZE.height);
		myScene.getStylesheets().add(this.getClass().getResource("/resources/GameView.css").toExternalForm());
	}  
	
	private Button makeButton(AbstractCommand command) {
		return myButtonFactory.makeButton(command);
	}

	
	

}
