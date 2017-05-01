package engines.infinite;

import java.util.Collection;

import entity.IEntity;
import entity.IEntityManager;
import gamedata.IRestrictedGameData;
import javafx.scene.input.KeyCode;

public interface IInfiniteAlgorithm {
	public String getLabelName();
	
	public void update(Collection<KeyCode> keysPressed, IRestrictedGameData gameData, IEntity myEntity, IEntityManager myEntityManager);
}
