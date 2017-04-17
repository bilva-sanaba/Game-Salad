package engines;


import java.util.*;

import components.*;
import components.entityComponents.ComponentType;
import components.entityComponents.XYComponent;
import components.movementcomponents.AccelerationComponent;
import components.movementcomponents.LocationComponent;
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
		Collection<IEntity> changed = new ArrayList<IEntity>();
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
				updateMovement(e, ComponentType.Velocity, ComponentType.Acceleration);
				resetAcceleration(e);
			}
			
			
//			placeInMap(entityMap, e);
//			//TODO: fix cast issue
//			changed.add((Entity) e);
		}
	}
	
	private void updateLocation(IEntity e) {
		LocationComponent lc = (LocationComponent) e.getComponent(ComponentType.Location);
		VelocityComponent vc = (VelocityComponent) e.getComponent(ComponentType.Velocity);
		lc.setXY(lc.getX() + vc.getX(), lc.getY() + vc.getY());
		((IRestrictedEntity) e).changed(e);
		
//		if (lc.getY()>200){
//			vc.setY(0);
//			ac.setY(0);
//		}
	}
	
	private void updateVelocity(IEntity e) {
		VelocityComponent vc = (VelocityComponent) e.getComponent(ComponentType.Velocity);
		AccelerationComponent ac = (AccelerationComponent) e.getComponent(ComponentType.Acceleration);
		vc.setX(vc.getX()+ac.getX());
		vc.setY(vc.getY()+ac.getY());
////		TerminalVelComponent tvc = (TerminalVelComponent) e.getComponent(ComponentType.TerminalVelComponent);
//		if(Math.abs(vc.getX()) < tvc.getX()){
//			vc.setXY(vc.getX() + ac.getX(), vc.getY() + ac.getY());
//		}
//		else{
//			if(ac.getX() > 0){
//				if(vc.getX() < 0){
//					vc.setX(-tvc.getX());
//				}
//				if(vc.getX() > 0){
//					vc.setX(tvc.getX());
//				}
//			}
//			else if(vc.getX() != 0){
//				vc.setX(vc.getX() + ac.getX());
//				System.out.println("Velocity:" + vc.getX());
//			}
//		}
	}
	private void resetAcceleration(IEntity e){
		((AccelerationComponent) e.getComponent(ComponentType.Acceleration)).setY(0);
	}
	private void updateMovement(IEntity e, ComponentType c1, ComponentType c2) {
		XYComponent xy1 = (XYComponent) e.getComponent(c1);
		XYComponent xy2 = (XYComponent) e.getComponent(c2);
		
		xy1.setXY(xy1.getX() + xy2.getX(), xy1.getY() + xy2.getY());
	}
	
	private boolean hasComponent(IEntity e, ComponentType c) {
		return (e.getComponent(c)!=null);
	}
	
//	private void placeInMap (Map <IEntity, IRestrictedEntity> entityMap, IEntity e) {
//		entityMap.put(e, ref.createRestrictedEntity(e));
//	}
}
