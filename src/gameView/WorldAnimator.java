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
	public final String TITLE = "Example Game";
	public static final int FRAMES_PER_SECOND = 60;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	public static final int KEY_INPUT_SPEED = 3;
	private EntityFactory entityFactory = new EntityFactory();
	
	public void start (Stage s){
		
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
									  e-> step(SECOND_DELAY));
		Timeline animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}
	
	private void step(double elapsedTime){
		//loop through list of entities and make appropriate update to their position by using Factory/reflection
			Entity updatedEntity = entityFactory.genEntity(DataType);
			updatedEntity.move();
	}
	
	private void handleKeyInput(KeyCode code){
		if(code==KeyCode.RIGHT){
			
		}
		else if(code==KeyCode.LEFT){
			
		}
		else if(code==KeyCode.UP){
			
		}
		else if(code==KeyCode.DOWN){
			
		}
	}
}
