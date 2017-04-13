package controller;

import data_interfaces.*;
import gameView.UIImageModel;
import gameView.UIImageProperty;
import gameView.UIView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Observable;
import java.util.Observer;

import data_interfaces.*;
import view_interfaces.UIViewInterface;
import javafx.stage.Stage;
import data_interfaces.XMLException;
import view.GUIBuilder;
import view.UtilityFactory;
import controller_interfaces.ControllerInterface;
import entity.EntityManager;

import entity.restricted.IRestrictedEntityManager;
import entity.restricted.RestrictedEntity;

import entity.restricted.RestrictedEntityManager;
import gameEngine_interface.GameEngine;
import gameView.UIView;

/**
 * 
 * @author Jacob
 *
 */

public class Controller implements ControllerInterface {

	private UIViewInterface myGameView;
	private GameEngine myGameEngine;
	private WorldAnimator myWorldAnimator;
	private Stage myStage;
	private String filePath;
	private GUIBuilder myGUIBuilder;

	public Controller(Stage s) {
		myStage = s;
		myGUIBuilder = new GUIBuilder(new UtilityFactory("English"));
		myGameEngine = new GameEngine();
		myWorldAnimator = new WorldAnimator();
		myGameView = new UIView(s, this);
	}

	public void save(String fileName) {
		// TODO Auto-generated method stub
		// loop through and save all write all items to XML
		XMLWriter xw = new XMLWriter();
		xw.writeFile(fileName, myGameEngine.save());
	}

	@Override
	public IRestrictedEntityManager loadNewGame(String gameName) { //IRestrictedEntityManager
		Communicator c = new Communicator(gameName);
		//myGameEngine.loadData(c); //c
		myGameEngine.dummyLoad();
		RestrictedEntityManager restrictedEntityManager = myGameEngine.getRestrictedEntityManager();
		return restrictedEntityManager;
	}

	@Override
	public void resetCurrentGame() throws XMLException {
		if(!filePath.equals(null)){
			myGameEngine.loadData(new Communicator(filePath));;
		}
		else{
			throw new XMLException(String.format("No current game"));
		}
	}

	public void runGameAnimation() {
		myWorldAnimator.start(myGameEngine);
	}
	
	public void makeGame() {
		myStage.setScene(myGUIBuilder.buildScene());
		myStage.show();
	}
	
	public GameEngine getEngine() {
		return myGameEngine;
	}
}
