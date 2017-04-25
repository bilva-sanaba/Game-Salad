
package gameView;

import java.awt.Dimension;
import java.util.Set;

import data_interfaces.XMLException;
import gameView.gameScreen.GameScreen;
//import gameView.gameScreen.SpecificGameSplashView;



import com.sun.jmx.snmp.Timestamp;

import gameView.splashScreen.SplashView;
import gameView.userManagement.UserData;
import gameView.userManagement.UserDatabase;
import gameView_interfaces.UIViewInterface;
import gamedata.GameData;
import controller.WorldAnimator;
import controller_interfaces.ControllerInterface;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;


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
	private GameData myData; 
	private WorldAnimator myAnimation;
	private UserData myUser;
	private UserDatabase myDatabase;
	
	public UIView(Stage s, ControllerInterface controller) {
		myStage = s;
		s.setTitle(STAGE_TITLE);
		myDatabase = new UserDatabase();
		myController = controller;
		myAnimation = new WorldAnimator(this);
		mySplash = new SplashView(this);
		myGameScene = new GameScreen(this, myAnimation);
		//runGame();//getSplashScreen();
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
		
		//COMMENT OUT TO TEST WITH RUNNER
		//myGameScene.addData(myController.loadNewGame(file));
		
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
	
	public void addData(GameData data) {
		myData = data;
		myGameScene.addData(data);
	}
	
	public void step(Set<KeyCode> keysPressed) {
		myController.step(keysPressed);
	}
	
	public void selectUser(UserData user, boolean newUser) {
		myUser = myDatabase.selectUser(user, newUser);
	}

	public UserData getUser() {
		return myUser;
	}
	
	public void newStage(AbstractViewer view) {
		Stage newStage = new Stage();
		//LoginScreen login = new LoginScreen(getView().getView(), newStage);
		newStage.setScene(view.getScene());//login.getScene());
		newStage.showAndWait();
	}
}
