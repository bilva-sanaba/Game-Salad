package gameEngine.chooser;

import java.util.List;

import engines.AbstractEngine;
import engines.IEngine;
/**
 * Interface for GameEngineChooser which returns a list of engines to be used by the game engine
 * @author Bilva
 *
 */
public interface IGameEngineChooser {
	public List<IEngine> getEngines();
}
