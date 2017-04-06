package gameView.gameScreen;

import entitiy.restricted.IRestrictedEntityManager;
import gameView.ImageManager;
import gameView.UIView;
import gameView.commands.AbstractCommand;
import gameView.tools.ButtonFactory;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class GameScreen {
	
	private Scene myScene;
	private BorderPane myBP;
	private UIView myView;
	private IRestrictedEntityManager myEntities;
	private ImageManager myManager;
	private ButtonFactory myButtonFactory;
	
	public GameScreen(UIView view) {
		myView = view;
		myButtonFactory = new ButtonFactory(view);
		buildScene();
	}
	
	public Scene getScene() {
		return myScene;
	}
	
	public void addEntity(IRestrictedEntityManager entity) {
		myEntities = entity;
		myManager = new ImageManager(entity);
	}

	
	private void buildScene() {
		myBP = new BorderPane();
	}
	
	private Button makeButton(AbstractCommand command) {
		return myButtonFactory.makeButton(command);
	}

	
	

}
