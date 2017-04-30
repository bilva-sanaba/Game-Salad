
package gameView;

import java.awt.Dimension;
import java.util.Set;

import data_interfaces.XMLException;
import gameView.gameScreen.GameScreen;
import gameView.gameScreen.SpecificGameSplashView;

import com.sun.jmx.snmp.Timestamp;

import gameView.splashScreen.SplashView;
import gameView.userInput.IUserInputData;
import gameView.userManagement.IUserManager;
import gameView.userManagement.UserManager;
import gamedata.GameData;
import gamedata.IRestrictedGameData;
import controller.WorldAnimator;
import controller_interfaces.ControllerInterface;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class UIView implements UIViewInterface {
	
	public static final Dimension DEFAULT_SIZE = new Dimension(1000, 650);
	public static final String DEFAULT_BUTTONS =  "EnglishCommands";
	public static final String DEFAULT_LOCATION = "resources/";
	public static final String DEFAULT_STYLING = "UI";
	public static final String STAGE_TITLE = "RainDrop Laptop";
	
	private Stage myStage;
	private ControllerInterface myController;
	private SplashView mySplash;
	private SpecificGameSplashView mySpecificSplash;
	private GameScreen myGameScene;
	private IRestrictedGameData myData; 
	private WorldAnimator myAnimation;
	private IUserManager myUserManager;
	private IUserInputData myUserInputData; 
	private String myCurrentGame;
	
	public UIView(Stage s, ControllerInterface controller, IUserInputData userInput) {
		myStage = s;
		setStageClose();
		s.setTitle(STAGE_TITLE);
		myUserInputData = userInput;
		myUserManager = new UserManager();
		myController = controller;
		myAnimation = new WorldAnimator(this);
		mySplash = new SplashView(this, s, myUserInputData);
		myGameScene = new GameScreen(this, myStage, myUserInputData, myAnimation);
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
	public void runGame() {
		setStage(mySpecificSplash.getScene());//myGameScene
		
	}
	
	public void loadGame(String file) {
		if (myCurrentGame != null) {
			savePoints();
		}
		myCurrentGame = file;
		//myData = myController.loadNewGame(file); //FOR SPLASH
		mySpecificSplash = myController.loadSpecificSplash();
		
		//COMMENT OUT TO TEST WITH RUNNER
		//myGameScene.addData(myData); //FOR SPLASH
		
		//TODO COMMENT OUT TO USE SPECIFIC GAME SPLASH
		runGame();
		
		//TODO UNCOMMENT WHEN YOU WANT TO USE THE SPECIFIC GAME SPLASHSCREEN
		//setStage(new SpecificGameSplashView(this, getStage(), myUserInputData, myController.getEngine().getSplashEntity()).getScene());
		
	}
	
	public void authorGame() {
		myController.makeGame(); //makeGame();
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
	
	public void addData(GameData data) {
		myData = data;
		myGameScene.addData(data);
	}
	
	public void step(Set<KeyCode> keysPressed) {
		myController.step(keysPressed);
	}
	
	public IUserManager getUserManager() {
		return myUserManager;
	}
	
	public void newStage(AbstractViewer view, Stage s) {
		s.setScene(view.getScene());//login.getScene());
		s.showAndWait();
	}
	
	private void setStageClose() {
		myStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
	          public void handle(WindowEvent we) {
	        	  savePoints();
	              myUserManager.saveAllUsers();
	          }
	      });    
	}
	
	private void savePoints() {
		System.out.println(myCurrentGame);
		try {
			myUserManager.getCurrentUser().addPoints(myCurrentGame, new Double(500));
			//myUserManager.getCurrentUser().addPoints(myCurrentGame, myData.getPoints().doubleValue());
		} catch (Exception e) {
			System.out.println("NO USER OR GAMEDATA INTIALIZED");
		}
//		if (myUserManager.getCurrentUser() != null && myData.getPoints() != null) {
//			System.out.println("TRYING TO SAVE");
//			myUserManager.getCurrentUser().addPoints(myCurrentGame, myData.getPoints().doubleValue());
//		}
		
	}
}
