package controller;

import gameView.UIImageModel;
import gameView.UIImageProperty;
import gameView.UIView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Observable;
import java.util.Observer;

import org.w3c.dom.Element;

import data_interfaces.*;

import view_interfaces.UIViewInterface;
import javafx.stage.Stage;
import data_interfaces.XMLException;
import view.GUIBuilder;
import view.UtilityFactory;
import controller_interfaces.ControllerInterface;
import entity.EntityManager;
import entity.restricted.RestrictedEntity;
import entity.restricted.RestrictedEntityManager;
import gameEngine_interface.GameEngine;

/**
 * 
 * @author Jacob
 *
 */

public class Controller implements ControllerInterface {
	
	UIViewInterface myGameView;
	private GameEngine myGameEngine;
	private EntityManager myEntityManager;
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
	

	/*@Override
	public UIImageProperty handleCollision(
			UIImageProperty coll1, UIImageProperty coll2,
			Collection<UIImageProperty> allActive) {
		Collection<Object> collidingObjects = new ArrayList<Object>();
		collidingObjects.addAll(Arrays.asList(coll1, coll2));
		myGameEngine.handleInteraction(collidingObjects, allActive);
		return null;
	}*/

	@Override
	public void save(String fileName) {
		// TODO Auto-generated method stub
		//loop through and save all write all items to XML
		XMLWriter xw = new XMLWriter();
		xw.writeFile(fileName, myEntityManager.getEntities());
	}

	@Override
	public void loadNewGame(String filePath) {
		// TODO Auto-generated method stub
		this.filePath = filePath;
		myGameEngine = new GameEngine(filePath);
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

	public void update(Observable obs, Object arg) {
		// TODO Auto-generated method stub
		//call world animator
		myWorldAnimator.start(myStage, myGameEngine);
	}

}
