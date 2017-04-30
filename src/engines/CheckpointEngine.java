package engines;

import java.util.Collection;
import java.util.List;

import components.entityComponents.ComponentType;
import components.entityComponents.HealthComponent;
import entity.IEntity;
import entity.IEntityManager;
import gamedata.IRestrictedGameData;
import javafx.scene.input.KeyCode;

public class CheckpointEngine extends AbstractEngine{

	public CheckpointEngine(IEntityManager myEntityManager) {
		super(myEntityManager);
	}

	@Override
	public IRestrictedGameData update(Collection<KeyCode> keysPressed, IRestrictedGameData gameData) {
		for(IEntity e: getEManager().getEntities()){
			if(e.getComponent(ComponentType.KeyInput) != null){
				HealthComponent hc = (HealthComponent) e.getComponent(ComponentType.Health);
				if(hc.getHealth() <= 0){
					
				}
			}
		}
		return null;
	}

}
