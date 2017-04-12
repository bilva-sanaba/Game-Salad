
package gameView;

import java.awt.Dimension;

import data_interfaces.XMLException;
import entity.restricted.IRestrictedEntityManager;
import gameView.gameScreen.GameScreen;
import com.sun.jmx.snmp.Timestamp;
import gameView.splashScreen.SplashView;
import controller.WorldAnimator;
import controller_interfaces.ControllerInterface;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view_interfaces.UIViewInterface;


public class UIView implements UIViewInterface {
	
	public static final Dimension DEFAULT_SIZE = new Dimension(1000, 650);
	public static final String DEFAULT_BUTTONS =  "EnglishCommands";
	public static final String DEFAULT_LOCATION = "resources/";
	public static final String DEFAULT_STYLING = "UI";
	public static final String STAGE_TITLE = "RainDrop Salad";
	
	private Stage myStage;
	private ControllerInterface myController;
	private SplashView mySplash;
	private GameScreen myGameScene;
	private IRestrictedEntityManager myEntities; 
	private WorldAnimator myAnimation;
	
	public UIView(Stage s, ControllerInterface controller) {
		myStage = s;
		s.setTitle(STAGE_TITLE);
		myController = controller;
		myAnimation = new WorldAnimator();
		mySplash = new SplashView(this);
		myGameScene = new GameScreen(this, myAnimation);
		//runGame();
		getSplashScreen();
	}

	public void getSplashScreen() {
		setStage(mySplash.getScene());
	}
	
	@Override
	public void addUIImage(String picturePath, double x, double y,
			double width, double height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void runGame() {
		setStage(myGameScene.getScene());
		
	}
	
	public void loadGame(String file) {
		System.out.println(file);
		myController.loadNewGame(file);
	}
	
	public void authorGame() {
		myController.makeGame();
	}
	
	public void saveGame() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		myController.save(timestamp.toString());
	}
	
	public void restart() {
		try {
			myController.resetCurrentGame();
		} catch (XMLException e) {
			//TODO: make exception
		}
	}
	
	private void setStage(Scene s) {
		myStage.setScene(s);
		myStage.show();
	}
	
	public Stage getStage() {
		return myStage;
	}
	
	public void addEntities(IRestrictedEntityManager entity) {
		myEntities = entity;
		myGameScene.addEntity(entity);
	}
	
	public Scene getGameScene() {
		return myAnimation.getScene();
	}

}
