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

public class MovementEngine extends AbstractEngine{
	
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
		if (e.hasComponent(ComponentType.Friction) && ((FrictionComponent) e.getComponent(ComponentType.Friction)).getFriction()){
		VelocityComponent vc = (VelocityComponent) e.getComponent(ComponentType.Velocity);
		AccelerationComponent ac = (AccelerationComponent) e.getComponent(ComponentType.Acceleration);
		LocationComponent lc = (LocationComponent) e.getComponent(ComponentType.Location);
		//DECELERATES
		if(vc.getX() > 0.5){
			ac.setX(-0.9);
		}
		else if (vc.getX() < - 0.5){
			ac.setX(0.9);
		}
		if (Math.abs(vc.getX()) < 0.9){
			ac.setX(0);
			vc.setX(0);
		}
		
		if(lc.getX() < 55){
			ac.setX(0);
			vc.setX(0);
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
			ac.setY(0.9);
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