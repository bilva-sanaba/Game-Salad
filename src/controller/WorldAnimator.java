package controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.w3c.dom.Entity;

import com.sun.javafx.collections.MappingChange.Map;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.util.Duration;
import entitiy.restricted.RestrictedEntity;
import entitiy.restricted.RestrictedEntityManager;
import entity.EntityManager;
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
	private Timeline animation;
	private GameBuilder myGameBuilder;
	private Group root;
	
	private HashMap<Integer, ImageView> imageMap = new HashMap<Integer, ImageView>();
	
	private boolean pause = false;
	
	public WorldAnimator(){
	}
	
	public void start (Stage s, GameEngine myGameEngine){
		
		root = new Group();
		
		this.myGameEngine = myGameEngine;
		RestrictedEntityManager restrictedEntityManager = myGameEngine.getRestrictedEntityManager();
		
		myGameBuilder = new GameBuilder();
		myScene = myGameBuilder.setUpGame(root, restrictedEntityManager, 500, 500);
		createMap(restrictedEntityManager);
		for(Integer id : imageMap.keySet()){
			root.getChildren().add(imageMap.get(id));
		}
		s.setScene(myScene);//FILL
		s.show();
		myScene.setOnKeyPressed(e -> handleKeyPressed(e.getCode()));
		myScene.setOnKeyReleased(e -> handleKeyReleased(e.getCode()));
		
		collisionTracker = new CollisionTracker("No", restrictedEntityManager.getEntities());
		movementTracker = new MovementTracker("Go", restrictedEntityManager.getEntities());
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
									  e-> step(SECOND_DELAY));
		this.animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}
	
	private void createMap(RestrictedEntityManager manager) {
		
		Collection<RestrictedEntity> entities = manager.getEntities();
		
		for(RestrictedEntity entity : entities){
			Image image = new Image(getClass().getClassLoader().getResourceAsStream(entity.getImagePath()));
			ImageView imageView = new ImageView(image);
			imageView.setX(entity.getLocation().getX());
			imageView.setY(entity.getLocation().getY());
			imageMap.put(entity.getID(), imageView);
		}
	}

	private void step(double elapsedTime){	
		
		myGameEngine.handleUpdates(keysPressed);
		//MAKE CHANGES TO ROOT based on Updates
		
	}

	private void handleKeyReleased(KeyCode keyCode) {
		keysPressed.remove(keyCode) ;
		
	}

	private void handleKeyPressed(KeyCode keyCode) {
		externalKeyHandler(keyCode);
		keysPressed.add(keyCode);
	}
	
	private void externalKeyHandler(KeyCode code){
		if(code == KeyCode.P && !pause){
			//movementTracker.changeMessage("Pause");
			pause = true;
			animation.pause();
		}
		if(code == KeyCode.P && pause){
			//movementTracker.changeMessage("Go");
			pause = false;
			animation.play();
		}
	}
}
