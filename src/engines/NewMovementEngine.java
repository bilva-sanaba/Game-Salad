package engines;


import java.util.*;

import components.*;
import components.entityComponents.ComponentType;
import components.entityComponents.XYComponent;
import components.movementcomponents.AccelerationComponent;
import components.movementcomponents.TerminalVelocityComponent;
import components.movementcomponents.VelocityComponent;
import entity.*;
import entity.restricted.*;
import javafx.scene.input.KeyCode;

public class NewMovementEngine extends AbstractEngine{
	
	
	
	public NewMovementEngine(IEntityManager myEntityManager) {
		super(myEntityManager);
	}

	@Override
	protected List<ComponentType> neededComponents() {	
		return null;
	}

	public void update(Collection<KeyCode> keys) {
		for (IEntity e: getEManager().getEntities()) {
			if (hasComponent(e,ComponentType.Location)) {
				updateAllValues(e);
			}	
		}
	}

	private void updateAllValues(IEntity e) {
		if (hasComponent(e, ComponentType.Velocity)) {
			updateLocation(e);
			if (hasComponent(e, ComponentType.Acceleration)) {
				updateMovement(e);
			}
			
//			placeInMap(entityMap, e);
//			//TODO: fix cast issue
//			changed.add((Entity) e);
		}
	}
	
	protected void updateLocation(IEntity e) {
		LocationComponent lc = (LocationComponent) e.getComponent(ComponentType.Location);
		VelocityComponent vc = (VelocityComponent) e.getComponent(ComponentType.Velocity);
		lc.setXY(lc.getX() + vc.getX(), lc.getY() + vc.getY());
		((IRestrictedEntity) e).changed(e);
	}
	
	private void updateMovement(IEntity e) {
		VelocityComponent vc = (VelocityComponent) e.getComponent(ComponentType.Velocity);
		AccelerationComponent ac = (AccelerationComponent) e.getComponent(ComponentType.Acceleration);
			
		vc.setX(vc.getX() + ac.getX());
		vc.setY(vc.getY() + ac.getY());
		
		//Handles terminal velocity
		if(e.getComponent(ComponentType.TerminalVelocity) != null){
			TerminalVelocityComponent tvc = (TerminalVelocityComponent) e.getComponent(ComponentType.TerminalVelocity);
			
			checkTerminalVelocityInX(vc, tvc);
			checkTerminalVelocityInY(vc, tvc);
		}
	}
	
	private void checkTerminalVelocityInX(VelocityComponent vc, TerminalVelocityComponent tvc){
		if(vc.getX() > tvc.getX()){
			
			if(vc.getX() > 0){
				vc.setX(tvc.getX());
			}
			else{
				vc.setX(-tvc.getX());
			}
		}
	}
	
	private void checkTerminalVelocityInY(VelocityComponent vc, TerminalVelocityComponent tvc){
		if(vc.getY() > tvc.getY()){
			
			if(vc.getY() > 0){
				vc.setY(tvc.getY());
			}
			else{
				vc.setY(-tvc.getY());
			}
		}
	}
	
	protected boolean hasComponent(IEntity e, ComponentType c) {
		return (e.getComponent(c)!=null);
	}
	
//	private void placeInMap (Map <IEntity, IRestrictedEntity> entityMap, IEntity e) {
//		entityMap.put(e, ref.createRestrictedEntity(e));
//	}
}
