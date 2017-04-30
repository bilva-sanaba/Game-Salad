package controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

import data_interfaces.*;
import gameView.UIView;
import gameView.UIViewInterface;
import gameView.userInput.IRestrictedUserInputData;
import gameView.userInput.IUserInputData;
import gameView.userInput.UserInputData;
import gamedata.GameData;
import gamedata.IRestrictedGameData;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import data_interfaces.XMLException;
import view.GUIBuilder;
import view.UtilityFactory;
import controller_interfaces.ControllerInterface;
import entity.restricted.IRestrictedEntityManager;
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
	private IRestrictedGameData gd;

	public Controller(Stage s) {
		myStage = s;
		myGUIBuilder = new GUIBuilder(new UtilityFactory("English"));
		UserInputData userInput = new UserInputData();
		myGameEngine = new GameEngine((IRestrictedUserInputData) userInput);
		myGameView = new UIView(s, this, (IUserInputData) userInput);
	}

	public void save(String fileName) {
		// TODO Auto-generated method stub
		// loop through and save all write all items to XML
		XMLWriter xw = new XMLWriter();
		xw.writeFile(fileName, myGameEngine.save());
	}

	@Override
	public IRestrictedGameData loadNewGame(String gameName) { //IRestrictedEntityManager
		Communicator c = new Communicator(gameName);
		return myGameEngine.loadData(c);
//		IRestrictedGameData gameData = myGameEngine.loadData(null); 
//		gd=gameData;
//		return gameData;
	}

	@Override
	public void resetCurrentGame() throws XMLException {
		if(!filePath.equals(null)){
			Communicator c = new Communicator(filePath);
			myGameEngine.loadData(new Communicator(filePath));;
		}
		else{
			throw new XMLException(String.format("No current game"));
		}
	}

//	public void runGameAnimation() {
//		myWorldAnimator.start(myGameEngine);
//	}
	
	public void makeGame() {
		Stage authorStage = new Stage();
		authorStage.setScene(myGUIBuilder.buildScene());
		authorStage.showAndWait();
		authorStage.getScene().setRoot(new Region());
	}
	
	public GameEngine getEngine() {
		return myGameEngine;
	}
	
	public void step(Set<KeyCode> keysPressed){
        myGameEngine.handleUpdates(keysPressed);
    }
}
