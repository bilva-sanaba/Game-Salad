
package gameView;

import java.awt.Dimension;

import data_interfaces.XMLException;
import entity.SplashEntity;
import entity.restricted.IRestrictedEntityManager;
import gameView.gameScreen.GameScreen;
//import gameView.gameScreen.SpecificGameSplashView;
import gameView.gameScreen.SpecificGameSplashView;

import com.sun.jmx.snmp.Timestamp;

import gameView.splashScreen.SplashView;
import controller.WorldAnimator;
import controller_interfaces.ControllerInterface;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
     	Image i = new Image(getClass().getClassLoader().getResourceAsStream("background1.png"));
     	myGameScene = new GameScreen(this, myAnimation);
		getSplashScreen();
		//TODO UNCOMMENT TO USE
		//getSplashScreen();
     	//SplashEntity test = new SplashEntity(1, "Splash", "instructions", "background1.png");
		//setStage(new SpecificGameSplashView(this, test).getScene());
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
		setStage(myGameScene.getScene());//mySplash
		
	}
	
	public void loadGame(String file) {
		//addEntities(myController.loadNewGame(file));
		myController.loadNewGame(file);
		myGameScene.addGameEngine(myController.getEngine());
		
		//TODO COMMENT OUT TO USE SPECIFIC GAME SPLASH
		runGame();
		
		//TODO UNCOMMENT WHEN YOU WANT TO USE THE SPECIFIC GAME SPLASHSCREEN
		//setStage(new SpecificGameSplashView(this, myController.getEngine().getSplashEntity()).getScene());
		
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
