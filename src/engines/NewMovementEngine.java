package engines;


import java.util.*;

import components.*;
import entity.*;
import entity.restricted.*;
import javafx.scene.input.KeyCode;

public class NewMovementEngine extends AbstractEngine{
	private RestrictedEntityFactory ref = new RestrictedEntityFactory();
	public NewMovementEngine(IEntityManager myEntityManager) {
		super(myEntityManager);
	}

	@Override
	protected List<ComponentType> neededComponents() {	
		return null;
	}

	public Collection<IEntity> update(Collection<KeyCode> keys) {
		Collection<IEntity> changed = new ArrayList<IEntity>();
		Map<IEntity, IRestrictedEntity> entityMap = getEManager().getEntityMap();
		for (IEntity e: entityMap.keySet()) {
			if (hasComponent(e,ComponentType.Location)) {
				updateAllValues(e, entityMap, changed);
			}
		}
		return changed;
	}

	private void updateAllValues(IEntity e, Map<IEntity, IRestrictedEntity> entityMap, Collection<IEntity> changed) {
		if (hasComponent(e, ComponentType.Velocity)) {
			updateMovement(e, ComponentType.Location, ComponentType.Velocity);
			if (hasComponent(e, ComponentType.Acceleration)) {
				updateMovement(e, ComponentType.Velocity, ComponentType.Acceleration);
			}
			placeInMap(entityMap, e);
			//TODO: fix cast issue
			changed.add((Entity) e);
		}
	}
	
	private void updateLocation(IEntity e) {
		LocationComponent lc = (LocationComponent) e.getComponent(ComponentType.Location);
		VelocityComponent vc = (VelocityComponent) e.getComponent(ComponentType.Velocity);
		AccelerationComponent ac = (AccelerationComponent) e.getComponent(ComponentType.Acceleration);
		
		lc.setXY(lc.getX() + vc.getX(), lc.getY() + vc.getY());
//		if (lc.getY()>200){
//			vc.setY(0);
//			ac.setY(0);
//		}
	}
	
	private void updateVelocity(IEntity e) {
		VelocityComponent vc = (VelocityComponent) e.getComponent(ComponentType.Velocity);
		AccelerationComponent ac = (AccelerationComponent) e.getComponent(ComponentType.Acceleration);
		
		vc.setXY(vc.getX() + ac.getX(), vc.getY() + ac.getY());
	}
	
	private void updateMovement(IEntity e, ComponentType c1, ComponentType c2) {
		XYComponent xy1 = (XYComponent) e.getComponent(c1);
		XYComponent xy2 = (XYComponent) e.getComponent(c2);
		
		xy1.setXY(xy1.getX() + xy2.getX(), xy1.getY() + xy2.getY());
	}
	
	private boolean hasComponent(IEntity e, ComponentType c) {
		return (e.getComponent(c)!=null);
	}
	
	private void placeInMap (Map <IEntity, IRestrictedEntity> entityMap, IEntity e) {
		entityMap.put(e, ref.createRestrictedEntity(e));
	}
}
