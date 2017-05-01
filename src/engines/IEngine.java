package engines;

import java.util.Collection;

import gamedata.IRestrictedGameData;
import javafx.scene.input.KeyCode;

public interface IEngine {
	public IRestrictedGameData update(Collection<KeyCode> keysPressed, IRestrictedGameData gameData);
}
