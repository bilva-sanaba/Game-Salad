package controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import com.sun.org.apache.regexp.internal.recompile;


import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.scene.Group;
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
	
	private HashSet<KeyCode> keysPressed = new HashSet<KeyCode>();

	private Scene myScene;
	private Timeline animation;
	private Group root;

	private Map<Integer, ImageView> imageMap= new HashMap<Integer, ImageView>();

	private boolean pause = false;

	public WorldAnimator(){
	}

	public void start (Stage s, GameEngine myGameEngine){
		root = new Group();
		RestrictedEntityManager restrictedEntityManager = myGameEngine.getRestrictedEntityManager();
		//myGameBuilder = new GameBuilder();

		//myScene = myGameBuilder.setUpGame(root, restrictedEntityManager, 500,500);
		myScene = new Scene(root,LENGTH,WIDTH);
		createMap(restrictedEntityManager);
		for (Integer id : imageMap.keySet()) {
			root.getChildren().add(imageMap.get(id));
		}
		s.setScene(myScene);
		s.show();
		myScene.setOnKeyPressed(e -> handleKeyPressed(e.getCode()));
		myScene.setOnKeyReleased(e -> handleKeyReleased(e.getCode()));
		
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
				e-> step(SECOND_DELAY, myGameEngine));
		this.animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		//animation.play();
	}
	
	public Scene getScene() {
		return myScene;
	}

	private void createMap(RestrictedEntityManager manager) {
		Collection<RestrictedEntity> entities = manager.getEntities();
		for (RestrictedEntity e: entities){
			imageMap.put(e.getID(), new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(e.getImagePath()))));
			imageMap.get(e.getID()).setX(e.getLocation().getX());
			imageMap.get(e.getID()).setY(e.getLocation().getY());
		}
	}

	private void step(double elapsedTime, GameEngine myGameEngine){
		Collection<RestrictedEntity> updatedEntities = myGameEngine.handleUpdates(keysPressed);
		HashMap<Integer, ImageView> updatedMap = fillMapAndDisplay(updatedEntities);
	}
	private void handleKeyReleased(KeyCode keyCode) {
		keysPressed.remove(keyCode);
	}

	private void handleKeyPressed(KeyCode keyCode) {
		externalKeyHandler(keyCode);
		keysPressed.add(keyCode);
		System.out.println(keysPressed);
	}
	
	private void externalKeyHandler(KeyCode code){
		if(code == KeyCode.S){
			animation.play();
		}
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
//			for (Integer id : imageMap.keySet()) {
//				imageMap.get(id).setX(imageMap.get(id).getX()+5);
//			}
//			if (trans.getChildren().size()>0){
//				trans.play();
//			}
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
			updateImage(currentImage,entity);
		}
	}
	private void updateImage(ImageView currentImage, RestrictedEntity re){
		currentImage.setImage(new Image(getClass().getClassLoader().getResourceAsStream(re.getImagePath())));
		currentImage.setX(re.getLocation().getX());
		currentImage.setY(re.getLocation().getY());		
	}
	
	private PathTransition moveToLocation(ImageView imageView, Coordinate c){
		Path path = new Path();
		double xLoc = c.getX();
		double yLoc = c.getY();
		path.getElements().add(new MoveTo(xLoc, yLoc));
		PathTransition pathTransition = new PathTransition(Duration.millis(KEY_INPUT_SPEED), path, imageView);
		return pathTransition;
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
