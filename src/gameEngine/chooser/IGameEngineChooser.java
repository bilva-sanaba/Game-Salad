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
	/**
	 * Returns a list of engines for engine to use to update
	 * @return
	 */
	public List<IEngine> getEngines();
}
