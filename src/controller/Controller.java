package controller;

import gameView.UIImageModel;
import gameView.UIImageProperty;
import gameView.UIView;
import gameView.WorldAnimator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Observable;
import java.util.Observer;

import view_interfaces.UIViewInterface;
import javafx.stage.Stage;
import controller_interfaces.ControllerInterface;
import entitiy.restricted.RestrictedEntity;
import entitiy.restricted.RestrictedEntityManager;
import gameEngine_interface.GameEngine;

/**
 * 
 * @author Jacob
 *
 */

public class Controller implements ControllerInterface, Observer {
	
	UIViewInterface myGameView;
	private GameEngine myGameEngine;
	private RestrictedEntityManager myRestrictedEntityManager;
	private WorldAnimator myWorldAnimator;
	private Stage myStage;
	
	public Controller(Stage s) {
		myStage = s;
		myGameView = new UIView(s, this);
		myGameEngine = new GameEngine();
		myWorldAnimator = new WorldAnimator();
		myRestrictedEntityManager = new RestrictedEntityManager();
	}
	
	public Controller() {
	}

	@Override
	public UIImageProperty handleCollision(
			UIImageProperty coll1, UIImageProperty coll2,
			Collection<UIImageProperty> allActive) {
		Collection<Object> collidingObjects = new ArrayList<Object>();
		collidingObjects.addAll(Arrays.asList(coll1, coll2));
		myGameEngine.handleInteraction(collidingObjects, allActive);
		return null;
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		//loop through and save all write all items to XML
		
	}

	@Override
	public void loadNewGame(String filePath) {
		// TODO Auto-generated method stub
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
		Collection<RestrictedEntity> animatableEntities = (Collection<RestrictedEntity>) arg;
		myWorldAnimator.start(myStage, animatableEntities);
	}

}
