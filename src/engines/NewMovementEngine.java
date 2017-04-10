package engines;


import java.util.*;

import components.*;
import entity.*;
import entity.restricted.*;

public class NewMovementEngine extends AbstractEngine{
	private RestrictedEntityFactory ref = new RestrictedEntityFactory();
	public NewMovementEngine(IEntityManager myEntityManager) {
		super(myEntityManager);
	}

	@Override
	protected List<ComponentType> neededComponents() {	
		return null;
	}

	@Override
	public Collection<IEntity> update() {
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
			updateLocation(e);
			if (hasComponent(e, ComponentType.Acceleration)) {
				updateVelocity(e);
			}
			placeInMap(entityMap, e);
			//TODO: fix cast issue
			changed.add((Entity) e);
		}
	}
	
	private void updateLocation(IEntity e) {
		LocationComponent lc = (LocationComponent) e.getComponent(ComponentType.Location);
		VelocityComponent vc = (VelocityComponent) e.getComponent(ComponentType.Velocity);
		
		lc.setXY(lc.getX() + vc.getX(), lc.getY() + vc.getY());
	}
	
	private void updateVelocity(IEntity e) {
		VelocityComponent vc = (VelocityComponent) e.getComponent(ComponentType.Velocity);
		AccelerationComponent ac = (AccelerationComponent) e.getComponent(ComponentType.Acceleration);
		
		vc.setXY(vc.getX() + ac.getX(), vc.getY() + ac.getY());
	}
	
	private boolean hasComponent(IEntity e, ComponentType c) {
		return (!e.getComponent(c).equals(null));
	}
	
	private void placeInMap (Map <IEntity, IRestrictedEntity> entityMap, IEntity e) {
		entityMap.put(e, ref.createRestrictedEntity(e));
	}
}
