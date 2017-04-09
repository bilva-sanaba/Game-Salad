package controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.util.Duration;
import entity.restricted.RestrictedEntity;
import entity.restricted.RestrictedEntityManager;
import gameEngine_interface.GameEngine;
/**
 * 
 * @author Jacob
 *
 */
public class WorldAnimator {
	//private Stage myStage;
	public static final int FRAMES_PER_SECOND = 60;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	public static final int KEY_INPUT_SPEED = 3;
	
	//private CollisionTracker collisionTracker;
	//private MovementTracker movementTracker;
	private ArrayList<KeyCode> keysPressed = new ArrayList<KeyCode>();
	
	private Scene myScene;
	private GameEngine myGameEngine;
	private Timeline animation;
	private GameBuilder myGameBuilder;
	private Group root;
	
	private HashMap<Integer, ImageView> imageMap;
	
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
		
		//collisionTracker = new CollisionTracker("No", restrictedEntityManager.getEntities());
		//movementTracker = new MovementTracker("Go", restrictedEntityManager.getEntities());
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
									  e-> step(SECOND_DELAY));
		this.animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}
	
	private void createMap(RestrictedEntityManager manager) {
		Collection<RestrictedEntity> entities = manager.getEntities();
		imageMap = fillMap(entities);
	}

	private void step(double elapsedTime){	
		
		Collection<RestrictedEntity> updatedEntities = myGameEngine.handleUpdates(keysPressed);
		HashMap<Integer, ImageView> updatedMap = fillMap(updatedEntities);

		//MAKE CHANGES TO ROOT based on Updates
		for(RestrictedEntity e : updatedEntities){
			if(e.getLocation() == null && e.getImagePath().equals(null)){
				imageMap.remove(e.getID());
				root.getChildren().remove(imageMap.get(e.getID()));
			}
			if(!imageMap.containsKey(e.getID())){
				imageMap.put(e.getID(), updatedMap.get(e.getID()));
				root.getChildren().add(imageMap.get(e.getID()));
			}
			else{
				imageMap.put(e.getID(), updatedMap.get(e.getID()));
			}
		}
		
		
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
	
	private HashMap<Integer, ImageView> fillMap(Collection<RestrictedEntity> entities){
		HashMap<Integer, ImageView> map = new HashMap<Integer, ImageView>();
		for(RestrictedEntity entity : entities){
			Image image = new Image(getClass().getClassLoader().getResourceAsStream(entity.getImagePath()));
			ImageView imageView = new ImageView(image);
			imageView.setX(entity.getLocation().getX());
			imageView.setY(entity.getLocation().getY());
			map.put(entity.getID(), imageView);
		}
		return map;
	}
}
