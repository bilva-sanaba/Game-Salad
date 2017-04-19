package engines;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import components.entityComponents.ComponentType;
import entity.IEntity;
import entity.IEntityManager;
import entity.restricted.IRestrictedEntity;
import javafx.scene.input.KeyCode;

public class PlayerMovementEngine extends NewMovementEngine{

	public PlayerMovementEngine(IEntityManager myEntityManager) {
		super(myEntityManager);
	}

	@Override
	protected List<ComponentType> neededComponents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Collection<KeyCode> keys) {
		if(keys.contains(arg0))
	}
	
	
	private void updateAllValues(IEntity e) {
		if (hasComponent(e, ComponentType.Velocity)) {
			updateLocation(e);
			if (hasComponent(e, ComponentType.Acceleration)) {
				updateMovement(e, ComponentType.Velocity, ComponentType.Acceleration);
				//resetAcceleration(e);
			}
			
			
//			placeInMap(entityMap, e);
//			//TODO: fix cast issue
//			changed.add((Entity) e);
		}
	}


}
