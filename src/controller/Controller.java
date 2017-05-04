package controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import data_interfaces.*;
import gameView.IUIView;
import gameView.UIView;
import gameView.UIViewInterface;
import gameView.gameScreen.SpecificGameSplashView;
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
import entity.IEntity;
import entity.IEntityManager;
import entity.LevelEntity;
import entity.SplashData;
import entity.restricted.IRestrictedEntityManager;
import gameEngine_interface.GameEngine;

/**
 * 
 * @author Jacob
 *
 */

public class Controller implements ControllerInterface {

	private IUIView myGameView;
	private GameEngine myGameEngine;
	private String filePath;
	private GUIBuilder myGUIBuilder;
	private IRestrictedGameData gd;
	private Communicator c;
	private UserInputData uiData;

	public Controller(Stage s) {
		myGUIBuilder = new GUIBuilder(new UtilityFactory("English"));
		uiData = new UserInputData();
		myGameEngine = new GameEngine((IRestrictedUserInputData) uiData);
		myGameView = new UIView(s, this, (IUserInputData) uiData);
	}

	public void save(String fileName) {
		XMLWriter xw = new XMLWriter();
		List <Map> saveList = new ArrayList<Map>();
		saveList.add(convertEntityManagers(myGameEngine.save()));
		saveList.add(convertLevelEntities(c.getLevelEntities()));
		saveList.add(convertSplashData(c.getSplashEntity()));
		xw.writeFile(fileName, saveList);
	}
	
	private Map<Integer, Map<Integer, IEntity>> convertEntityManagers(List<IEntityManager> lem) {
		Map<Integer, Map<Integer, IEntity>> ret = new HashMap<Integer, Map<Integer, IEntity>>();
		for (int i = 0; i < lem.size(); i++) {
			Map<Integer, IEntity> toAdd = new HashMap<Integer, IEntity>();
			for (IEntity ie: lem.get(i).getEntities()) {
				toAdd.put(ie.getID(), ie);
			}
			ret.put(i+1, toAdd);
		}
		return ret;
	}
	
	private Map<Integer, LevelEntity> convertLevelEntities(List <LevelEntity> le) {
		Map<Integer, LevelEntity> ret = new HashMap<Integer, LevelEntity>();
		int i = 0;
		for (LevelEntity item: le) {
			ret.put(i+1, item);
			i++;
		}
		return ret;
	}
	
	private Map<Integer, SplashData> convertSplashData(SplashData se) {
		Map<Integer, SplashData> ret = new HashMap<Integer, SplashData>();
		ret.put(GameSavingDataTool.SPLASHCONSTANT, se);
		return ret;
	}

	@Override
	public IRestrictedGameData loadNewGame(String gameName) {
		filePath = gameName;
		c = new Communicator(gameName);
		IRestrictedGameData gameData = myGameEngine.loadData(c); 
		gd=gameData;
		return gameData;
	}

//	@Override
//	public void resetCurrentGame() throws XMLException {
//		if(!filePath.equals(null)){
//			loadNewGame(filePath);
//		}
//		else{
//			throw new XMLException(String.format("No current game"));
//		}
//	}

	
	public SplashData getSplashData(String gameName){
		Communicator c = new Communicator(gameName);
		return c.getSplashEntity();
	}
	
	public void makeGame() {
		Stage authorStage = new Stage();
		authorStage.setScene(myGUIBuilder.buildScene());
		authorStage.showAndWait();
		authorStage.getScene().setRoot(new Region());
	}
	
	public void step(Set<KeyCode> keysPressed){
        myGameEngine.handleUpdates(keysPressed);
    }
}
