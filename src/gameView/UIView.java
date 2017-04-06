package gameView;

import java.awt.Dimension;

import entitiy.restricted.IRestrictedEntityManager;
import gameView.commands.AbstractCommand;
import gameView.commands.LoadCommand;
import gameView.commands.MakeCommand;
import gameView.gameScreen.GameScreen;

import java.util.ArrayList;
import java.util.Collection;

import gameView.splashScreen.SplashView;
import controller_interfaces.ControllerInterface;
import javafx.stage.Stage;
import view_interfaces.UIViewInterface;


public class UIView implements UIViewInterface {
	
	public static final Dimension DEFAULT_SIZE = new Dimension(800, 600);
	private Stage myStage;
	private ControllerInterface myController;
	private SplashView mySplash;
	private GameScreen myGameScene;
	private IRestrictedEntityManager myEntities; 
	
	public UIView(Stage s, ControllerInterface controller) {
		myStage = s;
		myController = controller;
		mySplash = new SplashView(this, getCommands());
		myGameScene = new GameScreen(this);
		getSplashScreen();
	}

	public void getSplashScreen() {
		myStage.setScene(mySplash.getSplashScene());
		myStage.show();
	}
	
	@Override
	public void addUIImage(String picturePath, double x, double y,
			double width, double height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void runGame() {
		myStage.setScene(myGameScene.getScene());
		myStage.show();
		
	}
	
	public void loadGame(String file) {
		myController.loadNewGame(file);
	}
	
	public void authorGame() {
		
	}
	
	public void saveGame() {
		
	}
	
	private Collection<AbstractCommand> getCommands() {
		Collection<AbstractCommand> list = new ArrayList<AbstractCommand>();
		list.add(new LoadCommand(this));
		list.add(new MakeCommand(this));
		return list;
	}
	
	public Stage getStage() {
		return myStage;
	}
	
	public void addEntities(IRestrictedEntityManager entity) {
		myEntities = entity;
		myGameScene.addEntity(entity);
	}
	
	

}
