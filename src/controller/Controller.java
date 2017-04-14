package controller;

import data_interfaces.*;
import gameView.UIView;
import view_interfaces.UIViewInterface;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import data_interfaces.XMLException;
import view.GUIBuilder;
import view.UtilityFactory;
import controller_interfaces.ControllerInterface;
import entity.restricted.IRestrictedEntityManager;
import entity.restricted.RestrictedEntityManager;
import gameEngine_interface.GameEngine;

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
		myGameEngine.loadData(c); //c
		//myGameEngine.dummyLoad();
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
		Stage authorStage = new Stage();
		authorStage.setScene(myGUIBuilder.buildScene());
		authorStage.showAndWait();
		authorStage.getScene().setRoot(new Region());
	}
	
	public GameEngine getEngine() {
		return myGameEngine;
	}
}
