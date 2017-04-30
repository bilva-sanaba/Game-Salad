package engines;

import java.util.Collection;
import java.util.List;

import components.entityComponents.ComponentType;
import data_interfaces.InfiniteEnum;
import entity.IEntityManager;
import gamedata.IRestrictedGameData;
import javafx.scene.input.KeyCode;

public class InfiniteEngine extends AbstractEngine {
	private InfiniteEnum infinite;
	public InfiniteEngine(IEntityManager myEntityManager, InfiniteEnum infinite) {
		super(myEntityManager);
		this.infinite=infinite;
	}

	@Override
	protected List<ComponentType> neededComponents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IRestrictedGameData update(Collection<KeyCode> keysPressed, IRestrictedGameData gameData) {
		// TODO Auto-generated method stub
		return null;
	}

}
