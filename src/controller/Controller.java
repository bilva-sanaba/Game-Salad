package controller;

import data_interfaces.*;

import view_interfaces.UIViewInterface;
import javafx.stage.Stage;
import data_interfaces.XMLException;
import view.GUIBuilder;
import view.UtilityFactory;
import controller_interfaces.ControllerInterface;
import entity.EntityManager;
import entity.restricted.RestrictedEntityManager;
import gameEngine_interface.GameEngine;
import gameView.UIView;

/**
 * 
 * @author Jacob
 *
 */

public class Controller implements ControllerInterface {
	
	UIViewInterface myGameView;
	private GameEngine myGameEngine;
	private WorldAnimator myWorldAnimator;
	private Stage myStage;
	private String filePath;
	
	private GUIBuilder myGUIBuilder;
	
	
	public Controller(Stage s) {
		myStage = s;
		myGUIBuilder = new GUIBuilder(new UtilityFactory("English"));
		myGameView = new UIView(s, this);
		myWorldAnimator = new WorldAnimator();
	}

	@Override
	public void save(String fileName) {
		// TODO Auto-generated method stub
		//loop through and save all write all items to XML
		XMLWriter xw = new XMLWriter();
		xw.writeFile(fileName, myGameEngine.save());
	}

	@Override
	public void loadNewGame(String gameName) {
		Communicator c = new Communicator(gameName);
		myGameEngine.loadData(c);
		RestrictedEntityManager restrictedEntityManager = myGameEngine.getRestrictedEntityManager();
	}

	@Override
	public void resetCurrentGame() throws XMLException {
		if(!filePath.equals(null)){
			myGameEngine = new GameEngine(filePath);
		}
		else{
			throw new XMLException(String.format("No current game"));
		}
	}

	public void run() {
		// TODO Auto-generated method stub
		//call world animator
		myWorldAnimator.start(myStage, myGameEngine);
	}

}
