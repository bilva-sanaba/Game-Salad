package controller;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.sun.org.apache.regexp.internal.recompile;

import components.LocationComponent;
import components.entityComponents.ComponentType;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.MoveTo;
import javafx.stage.Stage;
import javafx.util.Duration;
import entity.restricted.IRestrictedEntity;
import entity.restricted.IRestrictedEntityManager;
import gameEngine_interface.GameEngine;
import gameView.Coordinate;
import gameView.gameScreen.IGameScreenEntity;
import gameView.observers.ObserverManager;
import gameView_interfaces.UIViewInterface;
import gamedata.GameData;

/**
 *
 * @author Jacob
 *
 */
public class WorldAnimator{
    // private Stage myStage;
    public static final int FRAMES_PER_SECOND = 45;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final int KEY_INPUT_SPEED = 3;
    public static final int WIDTH = 5000;
    public static final int LENGTH = 1000;
    
    private Set<KeyCode> keysPressed = new HashSet<KeyCode>();
    private Set<KeyCode> keysReleased = new HashSet<KeyCode>();


    private Scene myScene;
    private Timeline animation;
    private Group root;

//    private GameBuilder myGameBuilder;

    private Camera myCamera;
    private UIViewInterface myView;
    private	ObserverManager myObservers;
    //private IGameScreenEntity myGameScreen;
    
    //FOR TESTING WITH RUNNER - CAN DELETE FOR NORMAL
    private GameEngine myEngine;
    
    private Map<Integer, ImageView> imageMap= new HashMap<Integer, ImageView>();

    private boolean pause = false;

    public WorldAnimator(UIViewInterface view){
    	myView = view;
    }
    public Group getGroup(){
    	return root;
    }
    public void start (GameData myData, IGameScreenEntity screen){
        root = new Group();
        IRestrictedEntityManager restrictedEntityManager = myData.getRestrictedEntityManager();
        myObservers = new ObserverManager(this, restrictedEntityManager);
//        myGameBuilder = new GameBuilder();
//        myScene = myGameBuilder.setUpGame(root, restrictedEntityManager, 500,500);


//BELALS SHIT

        //myScene = myGameBuilder.setUpGame(root, restrictedEntityManager, 500,500);
        myScene = new Scene(root,LENGTH,WIDTH);
        LocationComponent lc = myData.mainLocation();
        myCamera = new Camera(LENGTH ,myScene, lc);

        fillMapAndDisplay();

        /*for (Integer id : imageMap.keySet()) {
            root.getChildren().add(imageMap.get(id));
        }*/        
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
                e-> step(SECOND_DELAY));
        this.animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        //animation.play();
    }
    
    public Scene getScene() {
        return myScene;
    }

    private void step(double elapsedTime){
    	//myView.step(keysPressed);
    	myEngine.handleUpdates(keysPressed);
        fillMapAndDisplay();
        /*VelocityComponent velocityComponent = (VelocityComponent) myGameEngine.getMainCharacter().getComponent(ComponentType.Velocity);
        updateScrolling(locationComponent, velocityComponent);*/
        myCamera.updateCamera();
    }

    
    private void handleKeyReleased(KeyCode keyCode) {
        keysReleased.add(keyCode);
        keysPressed.remove(keyCode);
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

    private void fillMapAndDisplay(){
    	
    	//MYOBSERVERS RETURNS COLLECTION OF IMAGEVIEW THAT ARE CORRECTLY UPDATED
    	Map<Integer, ImageView> entities = myObservers.getEntityMap();
        //HashMap<Integer, ImageView> map = new HashMap<Integer, ImageView>();
        for(Integer entity : entities.keySet()){

		  //SequentialTransition trans = new SequentialTransition();
		  //removeEntity(entity,entities);
		  updateEntity(entity,entities);
		  createEntity(entity,entities);
		}

    }
    

    public void removeEntity(Integer entity){
    	System.out.println("CHAHCHAHCHA" + entity);
    	imageMap.get(entity).setImage(null);

        root.getChildren().remove(imageMap.get(entity));
         //myGameScreen.removeEntity(imageMap.get(entity));
        imageMap.remove(entity);
    }


	private void createEntity(Integer entity, Map<Integer, ImageView> entities){
	        if (!imageMap.containsKey(entity) && entities.get(entity)!=null){
	            ImageView imageView = new ImageView();
	            ImageView old = entities.get(entity);
	            imageView = updateImage(imageView, old);
	            imageMap.put(entity, imageView);
	            
	            root.getChildren().add(imageView);
//	            myGameScreen.addEntity(imageView);

	            //root.getChildren().add(imageMap.get(entity));
	        }
	  }


	private void updateEntity(Integer entity, Map<Integer, ImageView> entities){
        if (imageMap.containsKey(entity)) {
        	ImageView currentImage = imageMap.get(entity);
    		ImageView updatedImage = entities.get(entity);
    		updateImage(currentImage, updatedImage);
//    		PathTransition pt = moveToLocation(currentImage, entity.getLocation());
//    		trans.getChildren().add(pt);
//    		root.getChildren().add(imageMap.get(entity.getID()));
        }

    }
    private ImageView updateImage(ImageView currentImage, ImageView re){
        currentImage.setImage(re.getImage());
        
        //UNCOMMENT TO TEST RUNNER
        currentImage.setTranslateX(re.getTranslateX());

        currentImage.setTranslateY(re.getTranslateY()); 
        
        return currentImage;
        //COMMENT OUT TO TEST RUNNER

        //currentImage.setTranslateX(re.getLocation().getX()*50-475);
		//currentImage.setTranslateY(re.getLocation().getY()*50-175);
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
    
    public void clearRoot(){
    	imageMap.clear();
    	root.getChildren().clear();
    }
    
    
    //FOR RUNNER TESTING
    public void giveEngine(GameEngine f) {
    	myEngine = f;
    }
    
}
