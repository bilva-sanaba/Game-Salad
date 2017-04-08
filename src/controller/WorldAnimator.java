package controller;

import java.util.ArrayList;
import java.util.Collection;

import org.w3c.dom.Entity;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.util.Duration;
import entitiy.restricted.RestrictedEntity;
import entitiy.restricted.RestrictedEntityManager;
import gameEngine_interface.GameEngine;
import gameView.EntityFactory;
/**
 * 
 * @author Jacob
 *
 */
public class WorldAnimator {
	private Stage myStage;
	public static final int FRAMES_PER_SECOND = 60;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	public static final int KEY_INPUT_SPEED = 3;
	
	private CollisionTracker collisionTracker;
	private MovementTracker movementTracker;
	private ArrayList<KeyCode> keysPressed = new ArrayList<KeyCode>();
	
	private Scene myScene;
	private GameEngine myGameEngine;
	
	private boolean pause = false;
	
	public WorldAnimator(){
	}
	
	public void start (Stage s, GameEngine myGameEngine){
		
		myScene = //create scene
		s.setScene(myScene);//FILL
		s.show();
		Collection<RestrictedEntity> entities = restrictedEntities.getEntities();
		
		this.myGameEngine = myGameEngine;
		collisionTracker = new CollisionTracker("No", entities);
		movementTracker = new MovementTracker("Go", entities);
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
									  e-> step(SECOND_DELAY));
		Timeline animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}
	
	private void step(double elapsedTime){	
		myGameEngine.handleUpdates();
		
		myScene.setOnKeyPressed(e -> handleKeyPressed(e.getCode()));
		myScene.setOnKeyReleased(e -> handleKeyReleased());
		
		if(collisionTracker.getMessage().equals("Yes")){
			collisionTracker.changeMessage("No");
		}
	}

	private void handleKeyReleased() {
		// TODO Auto-generated method stub
		
	}

	private void handleKeyPressed(KeyCode code) {
		if(code ==  KeyCode.P && !pause){
			movementTracker.changeMessage("Pause");
			pause = true;
		}
		if(code == KeyCode.P && pause){
			movementTracker.changeMessage("Go");
			pause = false;
		}
		//ADD more
	}
}
