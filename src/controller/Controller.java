package controller;

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
	private WorldAnimator myWorldAnimator;
	private Stage myStage;
	
	private GUIBuilder myGUIBuilder;
	
	
	public Controller(Stage s) {
		myStage = s;
		myGUIBuilder = new GUIBuilder(new UtilityFactory("English"));
		myGameView = new UIView(s, this);
		myGameEngine = new GameEngine();
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
	public void save(String filename) {
		// TODO Auto-generated method stub
		//loop through and save all write all items to XML
		XMLWriter xw = new XMLWriter();
		xw.writeFile(filename, myGameEngine.save());
	}

	@Override
	public void loadNewGame(String gameName) {
		Communicator c = new Communicator(gameName);
		myGameEngine.loadData(c);
		RestrictedEntityManager restrictedEntityManager = myGameEngine.getRestrictedEntityManager();
		
	}

	@Override
	public void resetCurrentGame() {
		// TODO Auto-generated method stub
		//load starting game file
		
	}

	@Override
	public void checkCollision(UIImageModel u) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Observable obs, Object arg) {
		// TODO Auto-generated method stub
		//call world animator
		myWorldAnimator.start(myStage, myGameEngine);
	}

}
