package engines;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import components.AccelerationComponent;
import components.ComponentType;
import components.VelocityComponent;
import entity.IEntity;
import entity.IEntityManager;
import entity.restricted.IRestrictedEntity;
import javafx.scene.input.KeyCode;

public class PlayerMovementEngine extends AbstractEngine{

	public PlayerMovementEngine(IEntityManager myEntityManager) {
		super(myEntityManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected List<ComponentType> neededComponents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<IEntity> update(Collection<KeyCode> keysPressed) {
		Collection<IEntity> changed = new ArrayList<IEntity>();
		Map<IEntity, IRestrictedEntity> entityMap = getEManager().getEntityMap();
		for (IEntity e: entityMap.keySet()) {
			if (hasComponent(e,ComponentType.KeyInput)) {
				updateAllValues(e, entityMap, changed);
			}
			
		}
		return changed;
		return null;
	}

	
	/* 
	 * if(hasComponent(e, ComponentType.KeyInput) && keys.isEmpty()){
					if(hasComponent(e, ComponentType.Velocity)){
						VelocityComponent vc = (VelocityComponent) e.getComponent(ComponentType.Velocity);
						if(vc.getX() != 0){
							AccelerationComponent ac = (AccelerationComponent) e.getComponent(ComponentType.Acceleration);
							ac.setX(-1);
						}
						else{
							vc.setX(0);
						}
					}
				}
	 */

}
