package gameView;

import org.w3c.dom.Entity;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.stage.Stage;
import javafx.util.Duration;
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
	
	public WorldAnimator(){
	}
	
	public void start (Stage s, Collection<RestrictedEntity> restrictedEntities){
		
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
									  e-> step(SECOND_DELAY, restrictedEntities));
		Timeline animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}
	
	private void step(double elapsedTime, Collection<RestrictedEntities> restrictedEntities){
		for(Entity e : restrictedEntities){
			Entity updatedEntity = entityFactory.genEntity(DataType);
			updatedEntity.move();
		}
	}
}
