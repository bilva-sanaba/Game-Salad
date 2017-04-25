package engines;

import java.util.Collection;
import java.util.List;

import components.entityComponents.ComponentType;
import entity.IEntityManager;
import gamedata.IRestrictedGameData;
import javafx.scene.input.KeyCode;

public class HealthEngine extends AbstractEngine{

	public HealthEngine(IEntityManager myEntityManager) {
		super(myEntityManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected List<ComponentType> neededComponents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Collection<KeyCode> keysPressed, IRestrictedGameData gameData) {
		// TODO Auto-generated method stub
		
	}

}
