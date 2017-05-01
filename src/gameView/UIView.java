
package gameView;

import java.awt.Dimension;
import java.sql.Timestamp;
import java.util.Set;

import data_interfaces.XMLException;
import entity.SplashData;
import gameView.endScreen.EndScreen;
import gameView.gameDataManagement.GameDataManager;
import gameView.gameScreen.GameScreen;
import gameView.gameScreen.SpecificGameSplashView;
import gameView.splashScreen.SplashView;
import gameView.userInput.IUserInputData;
import gameView.userManagement.IUserManager;
import gameView.userManagement.UserManager;
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
	private GameScreen myGameScene; 
	private GameDataManager myData;  
	private WorldAnimator myAnimation;  
	private IUserManager myUserManager; 
	private IUserInputData myUserInputData; 
	private String myCurrentGame;  
	private SpecificGameSplashView mySpecificSplash;
	private SplashData mySplashData;
	
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
	}

	public void getSplashScreen() {
		setStage(mySplash.getScene());
	}
	
	@Override
	public void runSpecificSplash() {
		setStage(mySpecificSplash.getScene());
		
	}
	
	public void runGame(){
		setStage(myGameScene.getScene());
	}
	
	public void loadGame(String file) {
		if (myCurrentGame != null) {
			updateUserStats();
		}
		myCurrentGame = file;
		myData = new GameDataManager(this, myController.loadNewGame(file)); //FOR SPLASH
		//mySpecificSplash = myController.loadSpecificSplash(myCurrentGame);
		mySplashData = myController.getSplashData(myCurrentGame);
		mySpecificSplash = new SpecificGameSplashView(this, myStage, myUserInputData, mySplashData);
		
		//COMMENT OUT TO TEST WITH RUNNER
		myGameScene.addData(myData); //FOR SPLASH
		myGameScene.addBackground(mySplashData.getBackgroundFilePath());
		
		//TODO COMMENT OUT TO USE SPECIFIC GAME SPLASH
		runSpecificSplash();
		
	}
	
	public void authorGame() {
		myController.makeGame(); //makeGame();
	}
	
	public SplashData getSplashData(){
		return mySplashData;
	}
	
	public void saveGame() {
		String save = myCurrentGame + new Timestamp(System.currentTimeMillis()).toLocalDateTime();
		if (myUserManager.getCurrentUser() != null) {
			save = myUserManager.getCurrentUser().getName() + save;
			myUserManager.getCurrentUser().addGame(save);
			updateUserStats();
		}
		System.out.println(save);
		myController.save(save);
	}
	
	public void restart() {
		try {
			updateUserStats();
			myController.resetCurrentGame();
		} catch (XMLException e) {
			//TODO: make exception
		}
	}
	
	public void wonGame() {
		String won = "YOU WON!";
		ending(won);
	}
	
	public void lostGame() {
		String lost = "GAME OVER";
		ending(lost);
	}
	
	private void ending(String end) {
		AbstractViewer ending = new EndScreen(this, getStage(), myUserInputData, end, myData.getData().getPoints().doubleValue());
		ending.addBackground(mySplashData.getRestrictedImagePath());
		setStage(ending.getScene());
	}
	
	private void setStage(Scene s) {
		myStage.setScene(s);
		myStage.show();
	}
	
	public Stage getStage() {
		return myStage;
	}
	
	public void step(Set<KeyCode> keysPressed) {
		myController.step(keysPressed);
	}
	
	public IUserManager getUserManager() {
		return myUserManager;
	}
	
	public void newStage(AbstractViewer view, Stage s) {
		s.setScene(view.getScene());
		s.showAndWait();
	}
	
	private void setStageClose() {
		myStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
	          public void handle(WindowEvent we) {
	        	  updateUserStats();
	              myUserManager.saveAllUsers();
	          }
	      });    
	}
	
	private void updateUserStats() {
		try {
			//myUserManager.getCurrentUser().addPoints(myCurrentGame, new Double(500));
			myUserManager.getCurrentUser().addPoints(myCurrentGame, myData.getData().getPoints().doubleValue());
			myUserManager.getCurrentUser().addAchievement(myData.getData().getAchievement());
		} catch (Exception e) {
			System.out.println("NO USER OR GAMEDATA INTIALIZED");
		}
		
	}
}
