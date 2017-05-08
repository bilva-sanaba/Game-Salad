// This entire file is part of my masterpiece
// BELAL TAHER
// The reason I added this code to my master piece is because I think it serves as a good example
// for what an engine should aim to look like. Notice how all the values that should be able to be 
// changed based on user preference are specified through constants at the beginning of the class.
// As for the methods, the engine follows the convention of a broad public method (in this case update)
// that will most likely never have to be changed and very specific private helper methods that have
// compartmentalized functionality (such as updateLocation and updateMovement and decelerate). If someone
// wanted to fine tweak the way the engine works, they wouldn't have to touch the public method and this
// is good because that fine tweaking the engine doesn't require changing code anywhere else in the program.
// If there's one thing this course has taught me, it's that having this aspect to your code is extremely
// important because it greatly contributes to flexibility. So, essentially, I added this class to my 
// masterpiece because I feel like it represents good design in the sense that it has very broad public
// methods, makes use of constants so that fine tuning values is easy, specific and compartmentalized private
// helper methods that are all very appropriately named which makes locating/understanding/editing them
// very easy. I didn't actually refactor this code after the project was over, but I refactored this engine
// a great deal near the end of the project to be designed properly. 

package engines;
import java.util.*;
import components.entityComponents.AccelerationComponent;
import components.entityComponents.ComponentType;
import components.entityComponents.ControllableComponent;
import components.entityComponents.FrictionComponent;
import entity.restricted.*;
import gamedata.IRestrictedGameData;
import components.entityComponents.LocationComponent;
import components.entityComponents.TerminalVelocityComponent;
import components.entityComponents.VelocityComponent;
import entity.IEntity;
import entity.IEntityManager;
import javafx.scene.input.KeyCode;
/**
 * Movement engine which updates location using various alogrithms that utilize acceleration, velocity, and 
 * location
 * @author Belal, Bilva
 *
 */


public class MovementEngine extends AbstractEngine{
	
	public static final double DECELERATING_RANGE = 0.5;
	public static final double DECELERATING_CUTOFF = 0.9;
	public static final int DECELERATE = 1;
	public static final int STOP = 0;
	public static final double GRAVITY = 0.9;
	
	public MovementEngine(IEntityManager myEntityManager) {
		super(myEntityManager);
	}

	public IRestrictedGameData update(Collection<KeyCode> keys, IRestrictedGameData currentGameData) {
		for (IEntity e: getEManager().getEntities()) {
			if(hasComponent(e,ComponentType.KeyInput)){
				ControllableComponent cc = (ControllableComponent) e.getComponent(ComponentType.Controllable);
				if(keys.isEmpty() || (cc!=null && !(cc.checkControl())))
				decelerate(e);
			}
			if (hasComponent(e,ComponentType.Location)) {
				updateAllValues(e);
			}	
		}
		return currentGameData;
	}
	
	private void decelerate(IEntity e){
		if (e.hasComponent(ComponentType.Friction) && !((FrictionComponent) e.getComponent(ComponentType.Friction)).getFriction()){
		}else{
		VelocityComponent vc = (VelocityComponent) e.getComponent(ComponentType.Velocity);
		AccelerationComponent ac = (AccelerationComponent) e.getComponent(ComponentType.Acceleration);
		LocationComponent lc = (LocationComponent) e.getComponent(ComponentType.Location);
		//DECELERATES
		if(vc.getX() > DECELERATING_RANGE){
			ac.setX(-1 * DECELERATE);
		}
		else if (vc.getX() < -1* DECELERATING_RANGE){
			ac.setX(DECELERATE);
		}
		if (Math.abs(vc.getX()) < DECELERATING_CUTOFF){
			ac.setX(STOP);
			vc.setX(STOP);
		}
		}
	
	}
	
	private void updateAllValues(IEntity e) {
		if (hasComponent(e, ComponentType.Velocity)) {
			updateLocation(e);
			if (hasComponent(e, ComponentType.Acceleration)) {
				updateMovement(e);
			}
		}
	}
	
	protected void updateLocation(IEntity e) {
		LocationComponent lc = (LocationComponent) e.getComponent(ComponentType.Location);
		VelocityComponent vc = (VelocityComponent) e.getComponent(ComponentType.Velocity);
		//UPDATES LOCATION
		lc.setXY(lc.getX() + vc.getX(), lc.getY() + vc.getY());
		((IRestrictedEntity) e).changed(e);
	}
	
	private void updateMovement(IEntity e) {
		VelocityComponent vc = (VelocityComponent) e.getComponent(ComponentType.Velocity);
		AccelerationComponent ac = (AccelerationComponent) e.getComponent(ComponentType.Acceleration);	
		vc.setX(vc.getX() + ac.getX());		
		vc.setY(vc.getY() + ac.getY());
		//GRAVITY
		if(vc.getY() != 0 && ac.getY() == 0){
			ac.setY(GRAVITY);
		}
		//TERMINAL VELOCITY
		if(e.getComponent(ComponentType.TerminalVelocity) != null){
			TerminalVelocityComponent tvc = (TerminalVelocityComponent) e.getComponent(ComponentType.TerminalVelocity);
			checkTerminalVelocityInX(vc, tvc);
			checkTerminalVelocityInY(vc, tvc);
		}
	}
	
	private void checkTerminalVelocityInX(VelocityComponent vc, TerminalVelocityComponent tvc){
		if(Math.abs(vc.getX()) > tvc.getX()){		
			if(vc.getX() > 0){
				vc.setX(tvc.getX());
			}
			else{
				vc.setX(-tvc.getX());
			}
		}
	}
	
	private void checkTerminalVelocityInY(VelocityComponent vc, TerminalVelocityComponent tvc){
		if(Math.abs(vc.getY()) > tvc.getY()){
			if(vc.getY() > 0){
				vc.setY(tvc.getY());
			}
			else{
				vc.setY(-tvc.getY());
			}
		}
	}
}