//This entire file is part of my masterpiece
//JACOB WEISS

package controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import achievements.Achievement;
import alerts.VoogaAlert;
import components.entityComponents.LocationComponent;
import entity.restricted.IRestrictedEntityManager;
import gameView.UIViewInterface;
import gameView.gameScreen.IGameScreenEntity;
import gameView.observers.ImageConfig;
import gameView.observers.ObserverManager;
import gamedata.IRestrictedGameData;
import gamedata.VoogaImmutableObservableList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ListChangeListener;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 *Animation and placement of images from the authoring environment into the game player
 *This class uses updated observed entities on the game screen and allows the user to update the scene based
 *on key inputs.
 *I used this class as part of the larger masterpiece including observers package to show how the observer
 *design pattern is used to improve design by means of encapsulation.
 *In this class, which displays the most important aspect of game player--the game itself opposed to game screen features
 *like buttons and HUD--, achievements and entities are observed from the backend and consequently updated. Further,
 *only observers which have actually been changed in the back end are updated in game player.
 *This code is included in part of my masterpiece to show the visible effect of using the observer design pattern 
 *and how observers can be used effectively.
 * @author Jacob
 */
public class WorldAnimator{

	public static final int FRAMES_PER_SECOND = 30;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	public static final int KEY_INPUT_SPEED = 3;
	public static final int WIDTH = 5000;
	public static final int LENGTH = 1000;

	private Scene myScene;
	private Timeline animation;
	private Pane root;

	private Camera myCamera;

	private int achievementTimer=0;
	private boolean achievementShowing = false;
	private int achievementSize=0;
	private Achievement myAchievement;
	VoogaImmutableObservableList<String> myAchievements;
	
	private UIViewInterface myView;
	private	ObserverManager myObservers;

	private Map<Integer, ImageConfig> imageMap= new HashMap<Integer, ImageConfig>();

	private Set<KeyCode> keysPressed = new HashSet<KeyCode>();
	private Set<KeyCode> keysReleased = new HashSet<KeyCode>();
	
	/**
	 * Constructor
	 * @param view
	 */
	public WorldAnimator(UIViewInterface view){
		myView = view;
	}
	public Pane getGroup(){
		return root;
	}

	/**
	 * Method to create the pane for viewing of the game
	 * Uses restricted game data to create the observer manager
	 * Uses restricted game data to create observable lists of achievements;updates list if achievement is gotten
	 * Creates camera object to follow the user's character (important for scrolling/infintite features)
	 * Creates animation timeline
	 * @param myData
	 * @param screen
	 * @throws ClassNotFoundException
	 */
	public void start (IRestrictedGameData myData, IGameScreenEntity screen) throws ClassNotFoundException{ //achievementFactory
		root = new Pane();
		root.setStyle("-fx-background-color: rgba(0,0,0,0)");
		IRestrictedEntityManager restrictedEntityManager = myData.getRestrictedEntityManager();
		myObservers = new ObserverManager(this, restrictedEntityManager);
		myAchievements = myData.getAchievement();
		myAchievements.addListener((ListChangeListener<Object>) change -> {
			try {
				updateAchievement();
			} catch (ClassNotFoundException e) {
				VoogaAlert a = new VoogaAlert(e.getMessage());
				a.showAlert();
			}
		});

			myScene = new Scene(root,LENGTH,WIDTH);
			LocationComponent lc = myData.getMainLocation();	
			myCamera = new Camera(LENGTH*5 ,myScene, lc, -1, myData.getCamera());
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
		
		/**
		 * Updates game player each step
		 * As game player steps, the controller steps and the engine handles updates
		 * Timer for achievements is updated
		 * Camera is updated to follow player; achievement location updated accordingly
		 * Changed observers only are updated each step
		 * @param elapsedTime
		 * @throws ClassNotFoundException
		 */
		private void step(double elapsedTime) throws ClassNotFoundException{
			achievementTimer++;
			myView.step(keysPressed);
			fillMapAndDisplay(myObservers.getUpdatedSet());
			updateAchievement();
			if(achievementShowing==true){
			   myAchievement.updateAchievementLoc(-1*myCamera.getX());
			}
			myObservers.clearSet();
		}

		/**
		 * Updating achievements
		 * If there are more observed achievements than collected in the list in animation, add the most recent achievement
		 * checks to add and remove achievements
		 * @throws ClassNotFoundException
		 */
		private void updateAchievement() throws ClassNotFoundException {
			String ach="";
			if(achievementSize!=myAchievements.size()-1){ //subtract one for empty string
				ach = myAchievements.get(myAchievements.size()-1);
			}
			addAchievement(ach);
			removeAchievement();
		}
		/**
		 * adds key released to list of such keys and removes pressed key
		 * @param keyCode
		 */
		private void handleKeyReleased(KeyCode keyCode) {
			keysReleased.add(keyCode);
			keysPressed.remove(keyCode);
		}
		/**
		 * adds key pressed to list of such keys
		 * @param keyCode
		 */
		private void handleKeyPressed(KeyCode keyCode) {
			keysPressed.add(keyCode);
		}
		/**
		 * Lambda to handle when keys are pressed/released
		 * @param s
		 */
		public void setKeys(Scene s) {
			s.setOnKeyPressed(e -> handleKeyPressed(e.getCode()));
			s.setOnKeyReleased(e -> handleKeyReleased(e.getCode()));
		}
		
		/**
		 * public method used for reverse
		 */
		public void fillMap() {
			fillMapAndDisplay(myObservers.getUpdatedSet());
		}
	

		/**
		 * accesses observers and for updated observers, it finds new properties (image, location, etc.) from
		 * observable list
		 * checks to update entity/create new entity (used for shoot action)
		 * @param entities
		 */
		private void fillMapAndDisplay(Set<Integer> entities){
			Map<Integer, ImageConfig> map = myObservers.getEntityMap();
			for(Integer entity : entities){
				if (map.get(entity)!=null){
					updateEntity(entity,map);
					createEntity(entity,map);
				}
			}
			myCamera.updateCamera();
		}
		/**
		 * Removes entity from map of all images on the pane
		 * Unfortunately, removed observers are no longer part of observer list
		 * so method must be public and called in observer manager when change occurs
		 * Trade off of using the observer design pattern
		 * @param entity
		 */
		public void removeEntity(Integer entity){
			if (imageMap.containsKey(entity)){
				imageMap.get(entity).getImageView().setImage(null);
				root.getChildren().remove(imageMap.get(entity));
				imageMap.remove(entity);
			}
		}
		/**
		 * adds new observer to the pane
		 * @param entity
		 * @param map
		 */
		private void createEntity(Integer entity, Map<Integer, ImageConfig> map){
			if (!imageMap.containsKey(entity) && map.get(entity)!=null){
				ImageView imageView = new ImageView();
				ImageConfig temp = new ImageConfig(imageView, "");
				imageView = updateImage(entity, temp, map.get(entity));
				imageMap.put(entity, new ImageConfig(imageView, map.get(entity).getPath()));
				root.getChildren().add(imageView);
			}
		}
		/**
		 * updates already existing pane image by using updateImage to change location and image as needed
		 * @param entity
		 * @param map
		 */
		private void updateEntity(Integer entity, Map<Integer, ImageConfig> map){
			if (imageMap.containsKey(entity)) {
				ImageConfig current = imageMap.get(entity);
				ImageConfig updated = map.get(entity);
				updateImage(entity, current, updated);
			}
		}
		/**
		 * checks to see if updated image config object has new image (uses proxy design)
		 * If the image file path has been updated, the corresponding ImageConfig object will be changed to reflect it
		 * In this manner, GIFs with changing locations do not simply reload the same image and thus the GIF animation
		 * is present on the pane to be viewed by the user
		 * Next, the imageview in the ImageConfig object has location and size updated to reflect backend changes
		 * @param entity
		 * @param currentImage
		 * @param currentPath
		 * @param re
		 * @param rePath
		 * @return
		 */
		private ImageView updateImage(int entity, ImageConfig current, ImageConfig updated){
			ImageView currentImage = current.getImageView();
			String currentPath = current.getPath();
			ImageView re = updated.getImageView();
			String rePath = updated.getPath();
			if(!rePath.equals(currentPath)){
				currentImage.setImage(re.getImage());
				ImageConfig updatedIC  = new ImageConfig(currentImage,rePath);
				imageMap.put(entity,updatedIC);
			}
			currentImage.setTranslateX(re.getTranslateX());
			currentImage.setTranslateY(re.getTranslateY());
			currentImage.setFitHeight(re.getFitHeight());
			currentImage.setFitWidth(re.getFitWidth());
			return currentImage;
		}
		/**
		 * checks to see if there is a new achievement and adds it to the pane
		 * resets the achievement timer so that it disappears after showing to the user for a few seconds
		 * @param ach
		 * @throws ClassNotFoundException
		 */
		private void addAchievement(String ach) throws ClassNotFoundException{
			if(!achievementShowing && ach!=""){ //observed generate the achievement (myData.getStr)
				myAchievement = new Achievement(ach);
				root.getChildren().add(myAchievement.getGroup());
				achievementShowing=true;
				achievementTimer=1;
				achievementSize++;
			}
		}
		/**
		 * Once timer expires, present achievement is removed
		 */
		private void removeAchievement() {
			if(achievementTimer%90==0 && achievementShowing){
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
		/**
		 * Used when a new game is loaded to that images from old game are not still present
		 */
		public void clearRoot(){
			imageMap.clear();
			root.getChildren().clear();
		}
	}
