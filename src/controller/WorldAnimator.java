package controller;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.sun.org.apache.regexp.internal.recompile;

import components.entityComponents.ComponentType;
import components.entityComponents.LocationComponent;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Path;
import javafx.scene.shape.MoveTo;
import javafx.stage.Stage;
import javafx.util.Duration;
import entity.restricted.RestrictedEntity;
import entity.restricted.RestrictedEntityManager;
import gameEngine_interface.GameEngine;
import gameView.Coordinate;
import gameView.UIView;

/**
 * 
 * @author Jacob
 *
 */
public class WorldAnimator {
	// private Stage myStage;
	public static final int FRAMES_PER_SECOND = 45;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	public static final int KEY_INPUT_SPEED = 3;
	public static final int WIDTH = 500;
	public static final int LENGTH = 1000;
	
	private Set<KeyCode> keysPressed = new HashSet<KeyCode>();
	private Set<KeyCode> keysReleased = new HashSet<KeyCode>();


	private Scene myScene;
	private Timeline animation;
	private Group root;
	private GameBuilder myGameBuilder;
	private Camera myCamera;

	private Map<Integer, ImageView> imageMap= new HashMap<Integer, ImageView>();

	private boolean pause = false;

	public WorldAnimator(){
	}

	public void start (GameEngine myGameEngine){
		root = new Group();
		RestrictedEntityManager restrictedEntityManager = myGameEngine.getRestrictedEntityManager();
		myGameBuilder = new GameBuilder();
//		myScene = myGameBuilder.setUpGame(root, restrictedEntityManager, 500,500);
		
		//BELALS SHIT

		//myScene = myGameBuilder.setUpGame(root, restrictedEntityManager, 500,500);
		//myScene = new Scene(root,LENGTH,WIDTH);
		myScene = new Scene(root,LENGTH - 200,WIDTH);
		LocationComponent lc = (LocationComponent) myGameEngine.getMainCharacter().getComponent(ComponentType.Location);
		myCamera = new Camera(LENGTH ,myScene, lc);
		createMap(restrictedEntityManager);
		for (Integer id : imageMap.keySet()) {
			root.getChildren().add(imageMap.get(id));
		}

		myScene.setOnKeyPressed(e -> handleKeyPressed(e.getCode()));
		myScene.setOnKeyReleased(e -> handleKeyReleased(e.getCode()));
		
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
				e-> step(SECOND_DELAY, myGameEngine));
		this.animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
	}
	
	public Scene getScene() {
		return myScene;
	}

	private void createMap(RestrictedEntityManager manager) {
		Collection<RestrictedEntity> entities = manager.getEntities();
		for (RestrictedEntity e: entities){
			String[] test = e.getImagePath().split("[\\\\/]");
			imageMap.put(e.getID(), new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(test[test.length-1]))));
			//imageMap.get(e.getID()).setX(e.getLocation().getX());
			//imageMap.get(e.getID()).setY(e.getLocation().getY());
			imageMap.get(e.getID()).setFitHeight(e.getImageHeight());
			imageMap.get(e.getID()).setFitWidth(e.getImageWidth());

			imageMap.get(e.getID()).setTranslateX(e.getLocation().getX()*50-475);
			imageMap.get(e.getID()).setTranslateY(e.getLocation().getY()*50-175);
		}
	}

	private void step(double elapsedTime, GameEngine myGameEngine){
		Collection<RestrictedEntity> updatedEntities = myGameEngine.handleUpdates(keysPressed);
		HashMap<Integer, ImageView> updatedMap = fillMapAndDisplay(updatedEntities);
		/*VelocityComponent velocityComponent = (VelocityComponent) myGameEngine.getMainCharacter().getComponent(ComponentType.Velocity);
		updateScrolling(locationComponent, velocityComponent);*/
		myCamera.updateCamera();
	}

	
	private void handleKeyReleased(KeyCode keyCode) {
		keysReleased.add(keyCode);
		keysPressed.remove(keyCode);
		System.out.println(keyCode);
	}

	private void handleKeyPressed(KeyCode keyCode) {
		externalKeyHandler(keyCode);
		keysPressed.add(keyCode);
	}
	
	private void externalKeyHandler(KeyCode code){
		if(code == KeyCode.P && !pause){
			animation.pause();
			pause=true;
		}
		else if (code == KeyCode.P && pause) {
			animation.play();
			pause =false;
		}
	}

	public void setKeys(Scene s) {
		s.setOnKeyPressed(e -> handleKeyPressed(e.getCode()));
		s.setOnKeyReleased(e -> handleKeyReleased(e.getCode()));
	}

	private HashMap<Integer, ImageView> fillMapAndDisplay(Collection<RestrictedEntity> entities){
		HashMap<Integer, ImageView> map = new HashMap<Integer, ImageView>();
		for(RestrictedEntity entity : entities){
			SequentialTransition trans = new SequentialTransition();
			removeEntity(entity,trans);
			updateEntity(entity,trans);
			createEntity(entity,trans);
		}
		return map;
	}
	
	private void removeEntity(RestrictedEntity entity, SequentialTransition trans){
		if(entity.getLocation() == null && entity.getImagePath().equals(null)){
			if (imageMap.containsKey(entity.getID())){
//				FadeTransition ft = makeFade(imageMap.get(entity.getID()));
//				trans.getChildren().add(ft);

				root.getChildren().remove(imageMap.get(entity.getID()));
				imageMap.remove(entity.getID());
			}
		}
	}
	private void createEntity(RestrictedEntity entity, SequentialTransition trans){
		if (!imageMap.containsKey(entity.getID()) && entity.getImagePath()!=null && entity.getLocation()!=null){
			Image image = new Image(getClass().getClassLoader().getResourceAsStream(entity.getImagePath()));
			ImageView imageView = new ImageView(image);
			updateImage(imageView,entity);
//			FadeTransition ft = makeAppear(imageView);
//			trans.getChildren().add(ft);

			imageMap.put(entity.getID(), imageView);
		}
	}
	private void updateEntity(RestrictedEntity entity, SequentialTransition trans){
		if(imageMap.containsKey(entity.getID())){
			ImageView currentImage = imageMap.get(entity.getID());
			updateImage(currentImage, entity);
//			PathTransition pt = moveToLocation(currentImage, entity.getLocation());
//			trans.getChildren().add(pt);
//			root.getChildren().add(imageMap.get(entity.getID()));
		}
	}
	private void updateImage(ImageView currentImage, RestrictedEntity re){
		currentImage.setImage(new Image(getClass().getClassLoader().getResourceAsStream(re.getImagePath())));
		currentImage.setX(re.getLocation().getX()*50-475);
		currentImage.setY(re.getLocation().getY()*50-175);	
	}


	private FadeTransition makeFade(ImageView imageView){
		double newOpacity = 0.0;
		return createFade(newOpacity, imageView);
	}
	
	private FadeTransition makeAppear(ImageView imageView){
		double newOpacity = 1.0;
		return createFade(newOpacity, imageView);
	}
	
	private FadeTransition createFade(double newOpacity, ImageView imageView){
		FadeTransition ft = new FadeTransition(Duration.millis(KEY_INPUT_SPEED), imageView);
		ft.setToValue(newOpacity);
		return ft;
	}
	
	public void start(){
		animation.play();
	}
	
	public void pause(){
		animation.pause();
	}
}
