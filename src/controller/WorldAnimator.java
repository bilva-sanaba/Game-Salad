package controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import achievements.Achievement;
import components.entityComponents.LocationComponent;
import entity.restricted.IRestrictedEntityManager;
import gameEngine_interface.GameEngine;
import gameView.UIViewInterface;
import gameView.gameScreen.IGameScreenEntity;
import gameView.observers.ImageConfig;
import gameView.observers.ObserverManager;
import gamedata.GameData;
import gamedata.IRestrictedGameData;
import gamedata.VoogaImmutableObservableList;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.collections.ListChangeListener;
import javafx.scene.Group;
import javafx.scene.ParallelCamera;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 *
 * @author Jacob
 *Animation and placement of images from the authoring environment into the game player
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
	private Pane root;


	private Camera myCamera;

	private int counter=0;
	private boolean achievementShowing = false;

	private int achievementSize=0;
	private Achievement myAchievement;
	VoogaImmutableObservableList<String> myAchievements;
	private UIViewInterface myView;
	private	ObserverManager myObservers;

	private GameEngine myEngine;

	private Map<Integer, ImageConfig> imageMap= new HashMap<Integer, ImageConfig>();

	private boolean pause = false;

	public WorldAnimator(UIViewInterface view){
		myView = view;
	}
	public Pane getGroup(){
		return root;
	}

	public void start (IRestrictedGameData myData, IGameScreenEntity screen) throws ClassNotFoundException{ //achievementFactory
		root = new Pane();
		root.setStyle("-fx-background-color: rgba(0,0,0,0)");
		IRestrictedEntityManager restrictedEntityManager = myData.getRestrictedEntityManager();
		myObservers = new ObserverManager(this, restrictedEntityManager);
		myAchievements = myData.getAchievement();
		myAchievements.addListener(new ListChangeListener() {
			@Override
			public void onChanged(ListChangeListener.Change change) {
				try {
					updateAchievement();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					VoogaAlert a = new VoogaAlert(e.getMessage());
				}
			}
		});


			myScene = new Scene(root,LENGTH,WIDTH);

			LocationComponent lc = myData.getMainLocation();
			
			myCamera = new Camera(LENGTH*5 ,myScene, lc, -1, myData.getCamera());
			
			myObservers.getUpdatedSet();
			fillMapAndDisplay(myObservers.getEntityMap().keySet());

			KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
					e-> {
						try {
							step(SECOND_DELAY);
						} catch (ClassNotFoundException e1) {
							VoogaAlert vA = new VoogaAlert(e1.getMessage());
							vA.showAlert();//FIX THIS
						}
					});
			this.animation = new Timeline();
			animation.setCycleCount(Timeline.INDEFINITE);
			animation.getKeyFrames().add(frame);
		}

		public Scene getScene() {
			return myScene;
		}
		private void step(double elapsedTime) throws ClassNotFoundException{
			counter++;
			myView.step(keysPressed);
			fillMapAndDisplay(myObservers.getUpdatedSet());

			updateAchievement();

			if(achievementShowing==true){
			   myAchievement.updateAchievementLoc(-1*myCamera.getX());
			}

			myCamera.updateCamera();

			myObservers.clearSet();
		}

		public void fillMap() {
			fillMapAndDisplay(myObservers.getUpdatedSet());
			myCamera.updateCamera();
		}

		private void updateAchievement() throws ClassNotFoundException {
			String ach="";
			if(achievementSize!=myAchievements.size()-1){ //subtract one for empty string
				ach = myAchievements.get(myAchievements.size()-1);
			}
			addAchievement(ach);
			removeAchievement();
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
			if (imageMap.containsKey(entity)){
				//st.getChildren().add(makeFade(imageMap.get(entity).getImageView()));
				//st.play();
				imageMap.get(entity).getImageView().setImage(null);
				root.getChildren().remove(imageMap.get(entity));
				imageMap.remove(entity);
			}
		}


		private void createEntity(Integer entity, Map<Integer, ImageConfig> map){
			if (!imageMap.containsKey(entity) && map.get(entity)!=null){
				ImageView imageView = new ImageView();
				imageView = updateImage(entity, imageView, "", map.get(entity).getImageView(), map.get(entity).getPath());
				imageMap.put(entity, new ImageConfig(imageView, map.get(entity).getPath()));

				root.getChildren().add(imageView);
			}
		}


		private void updateEntity(Integer entity, Map<Integer, ImageConfig> map){
			if (imageMap.containsKey(entity)) {
				ImageConfig currentImage = imageMap.get(entity);

				ImageConfig updatedImage = map.get(entity);
				updateImage(entity, currentImage.getImageView(), currentImage.getPath(), updatedImage.getImageView(), updatedImage.getPath());
			}

		}
		private ImageView updateImage(int entity, ImageView currentImage, String currentPath, ImageView re, String rePath){

			if(!rePath.equals(currentPath)){
				currentImage.setImage(re.getImage());
				ImageConfig updated  = new ImageConfig(currentImage,rePath);
				imageMap.put(entity,updated);
			}

			currentImage.setTranslateX(re.getTranslateX());
			currentImage.setTranslateY(re.getTranslateY());
			currentImage.setFitHeight(re.getFitHeight());
			currentImage.setFitWidth(re.getFitWidth());


			return currentImage;
		}


		private void addAchievement(String ach) throws ClassNotFoundException{
			if(!achievementShowing && ach!=""){ //observed generate the achievement (myData.getStr)
				myAchievement = new Achievement(ach);
				root.getChildren().add(myAchievement.getGroup());
				achievementShowing=true;
				counter=1;
				achievementSize++;
			}
		}

		private void removeAchievement() {
			if(counter%90==0 && achievementShowing){
				root.getChildren().remove(myAchievement.getGroup());
				achievementShowing=false;
			}
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
