package controller;

import java.util.Collection;

import org.w3c.dom.Entity;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.stage.Stage;
import javafx.util.Duration;
import entitiy.restricted.RestrictedEntity;
import entitiy.restricted.RestrictedEntityManager;
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
	
	private EntityFactory entityFactory = new EntityFactory();
	private CollisionTracker collisionTracker;
	private MovementTracker movementTracker;
	
	public WorldAnimator(){
	}
	
	public void start (Stage s, RestrictedEntityManager restrictedEntities){
		
		Collection<RestrictedEntity> entities = restrictedEntities.getEntities();
		
		collisionTracker = new CollisionTracker("No", entities);
		movementTracker = new MovementTracker("Go", entities);
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
									  e-> step(SECOND_DELAY, entities));
		Timeline animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}
	
	private void step(double elapsedTime, Collection<RestrictedEntity> entities){	
		boolean collision = false;
		for(RestrictedEntity e : entities){
			//check for collision
			if(collision){
				collisionTracker.changeMessage("Yes");
			}
		}
	}
}
