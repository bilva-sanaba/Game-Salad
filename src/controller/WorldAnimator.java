package controller;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.sun.org.apache.regexp.internal.recompile;

import achievements.Achievement;
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
import gameView.UIView;
import gameView.gameScreen.IGameScreenEntity;
import gameView.gameScreen.SpecificGameSplashView;
import gameView.observers.ImageConfig;
import gameView.observers.ObserverManager;
import gameView_interfaces.UIViewInterface;
import gamedata.GameData;

/**
 *
 * @author Jacob
 *
 */
public class WorldAnimator{
    public static final int FRAMES_PER_SECOND = 30;
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
    private SequentialTransition st;

    private GameData myData;


    private Camera myCamera;
    private Group myAchievement;
    private UIViewInterface myView;
    private	ObserverManager myObservers;

    private GameEngine myEngine;
    
    private Map<Integer, ImageConfig> imageMap= new HashMap<Integer, ImageConfig>();

    private boolean pause = false;

    public WorldAnimator(UIViewInterface view){
    	myView = view;
    }
    public Group getGroup(){
    	return root;
    }
    public void start (GameData myData, IGameScreenEntity screen){
    	this.myData=myData;
        root = new Group();
        
       
        IRestrictedEntityManager restrictedEntityManager = myData.getRestrictedEntityManager();
        myObservers = new ObserverManager(this, restrictedEntityManager);



//BELALS SHIT

        //myScene = myGameBuilder.setUpGame(root, restrictedEntityManager, 500,500);
        myScene = new Scene(root,LENGTH,WIDTH);
        //st = new SequentialTransition();
        LocationComponent lc = myData.getMainLocation();
        //Change Length
        myCamera = new Camera(LENGTH*5 ,myScene, lc, -1);
        
        Achievement a = new Achievement("YOOOO");
        myAchievement = a.execute();
        root.getChildren().add(myAchievement);
        

        fillMapAndDisplay(myObservers.getEntityMap().keySet());

        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
                e-> step(SECOND_DELAY));
        this.animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
    }
    
    
    //for testing
    
    
    public Scene getScene() {
        return myScene;
    }
    private void step(double elapsedTime){
    	//myView.step(keysPressed);
    	myEngine.handleUpdates(keysPressed,myData);

        fillMapAndDisplay(myObservers.getUpdatedSet());
       
        //myAchievement.setLayoutX(myCamera.getX()+100);
        myCamera.updateCamera();
        myObservers.clearSet();
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

    private void fillMapAndDisplay(Set<Integer> entities){

    	Map<Integer, ImageConfig> map = myObservers.getEntityMap();
        for(Integer entity : entities){
        //This if statement should not be needed and observers shouldn't have nulls in their map imo - Bilva
        	if (map.get(entity)!=null){
        		updateEntity(entity,map);
        		createEntity(entity,map);
        	}
		}

    }
    

    public void removeEntity(Integer entity){
    	System.out.println("CHAHCHAHCHA" + entity);
    	if (imageMap.containsKey(entity)){
    		//st.getChildren().add(makeFade(imageMap.get(entity).getImageView()));
    		//st.play();
    		imageMap.get(entity).getImageView().setImage(null);
    	
    		imageMap.remove(entity);
    	}
    }


	private void createEntity(Integer entity, Map<Integer, ImageConfig> map){
	        if (!imageMap.containsKey(entity) && map.get(entity)!=null){
	            ImageView imageView = new ImageView();
	            imageView = updateImage(imageView, "", map.get(entity).getImageView(), map.get(entity).getPath());
	            imageMap.put(entity, new ImageConfig(imageView, map.get(entity).getPath()));
	            
	            root.getChildren().add(imageView);
	            //st.getChildren().add(makeAppear(imageView));
	            //st.play();
	        }
	  }


	private void updateEntity(Integer entity, Map<Integer, ImageConfig> map){
        if (imageMap.containsKey(entity)) {
        	ImageConfig currentImage = imageMap.get(entity);
    		ImageConfig updatedImage = map.get(entity);
    		updateImage(currentImage.getImageView(), currentImage.getPath(), updatedImage.getImageView(), updatedImage.getPath());
        }

    }
    private ImageView updateImage(ImageView currentImage, String currentPath, ImageView re, String rePath){
    	
    	if(!rePath.equals(currentPath)){
    		currentImage.setImage(re.getImage());
    	}
    	
        currentImage.setTranslateX(re.getTranslateX());
        currentImage.setTranslateY(re.getTranslateY()); 
        currentImage.setFitHeight(re.getFitHeight());
        currentImage.setFitWidth(re.getFitWidth());

        return currentImage;
        //COMMENT OUT TO TEST RUNNER

        //currentImage.setTranslateX(re.getLocation().getX()*50-475);
		//currentImage.setTranslateY(re.getLocation().getY()*50-175);
        //currentImage.setFitHeight(re.getFitHeight());
        //currentImage.setFitWidth(re.getFitWidth());
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
