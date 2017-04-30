package engines;

import java.util.Collection;
import java.util.List;

import components.entityComponents.ComponentType;
import entity.IEntityManager;
import gamedata.IRestrictedGameData;
import javafx.scene.input.KeyCode;

public class DamagedEngine extends AbstractEngine{

	public DamagedEngine(IEntityManager myEntityManager) {
		super(myEntityManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public IRestrictedGameData update(Collection<KeyCode> keysPressed, IRestrictedGameData gameData) {
		return null;
	}

}
