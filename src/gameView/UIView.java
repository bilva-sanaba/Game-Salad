
package gameView;

import java.awt.Dimension;
import java.sql.Timestamp;
import java.util.Set;
import entity.SplashData;
import gameView.endScreen.EndScreen;
import gameView.gameDataManagement.GameDataManager;
import gameView.gameScreen.GameScreen;
import gameView.gameScreen.SpecificGameSplashView;
import gameView.splashScreen.SplashView;
import gameView.userInput.IUserInputData;
import gameView.userManagement.IUserManager;
import gameView.userManagement.UserManager;
import controller_interfaces.ControllerInterface;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class UIView implements UIViewInterface, IUIView {
	
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
		mySplash = new SplashView(this, s, myUserInputData);
		myGameScene = new GameScreen(this, myStage, myUserInputData);
		getSplashScreen();
	}
	
	public void runGame(){
		setStage(myGameScene.getScene());
	}
	
	public void loadGame(String file) {
		if (myCurrentGame != null) {
			updateUserStats();
		}
		myCurrentGame = file;
		myData = new GameDataManager(this, myController.loadNewGame(file));
		mySplashData = myController.getSplashData(myCurrentGame);
		mySpecificSplash = new SpecificGameSplashView(this, myStage, myUserInputData, mySplashData);
		myGameScene.addData(myData);
		myGameScene.addBackground(mySplashData.getBackgroundFilePath());
		runSpecificSplash();
		
	}
	
	public void authorGame() {
		myController.makeGame();
	}
		
	public void saveGame() {
		String save = myCurrentGame + System.currentTimeMillis();
		if (myUserManager.getCurrentUser() != null) {
			save = myUserManager.getCurrentUser().getName() + save;
			myUserManager.getCurrentUser().addGame(myCurrentGame, save);
			updateUserStats();
		}
		myController.save(save);
	}
	
	public void restart() {
		updateUserStats();
		try {
			loadGame(myCurrentGame);
		} catch (Exception e) {
		}
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

	public void ending(String end) {
		myGameScene.checkWorldAnimator();
		AbstractViewer ending = new EndScreen(this, getStage(), myUserInputData, end, myData.getData().getPoints().doubleValue());
		ending.addBackground(mySplashData.getBackgroundFilePath());
		setStage(ending.getScene());
	}
	
	private void getSplashScreen() {
		setStage(mySplash.getScene());
	}
	
	private void runSpecificSplash() {
		setStage(mySpecificSplash.getScene());
		
	}
	
	private void setStage(Scene s) {
		myStage.setScene(s);
		myStage.show();
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
			myUserManager.getCurrentUser().addPoints(myCurrentGame, myData.getData().getPoints().doubleValue());
			myUserManager.getCurrentUser().addAchievement(myData.getData().getAchievement());
		} catch (Exception e) {
			System.out.println("NO USER OR GAMEDATA INTIALIZED");
		}
	}
}
