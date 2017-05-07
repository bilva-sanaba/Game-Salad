// This entire file is part of my masterpiece.
// Bilva Sanaba
// This class exhibits good design for several reasons. First it implements the IEngine. The high level game engine has a 
// list of IEngines that it uses to call updates and by implementing that interface this class can be used.
// It also relies on the strategy pattern as it can do various functionality given different IInfiniteAlgorithms.
// Additionally, it extends the Abstract Engine which avoids duplicate code between all IEngines as the AbstractEngine
// provides protected getters for a factory which can copy Collections to avoid concurrent modification as well as a 
// protected getter for retrieving the EntityManager which all IEngines would need to hold to make appropriate updates. 
package engines;

import java.util.Collection;

import engines.infinite.IInfiniteAlgorithm;
import entity.IEntity;
import entity.IEntityManager;
import exceptions.CopyException;
import gamedata.IRestrictedGameData;
import javafx.scene.input.KeyCode;
/**
 * Engine which takes the passed in an Infinite Enum to determine how to modify the game in order
 * to run infinite games
 * @author Bilva
 * @param myEntityManager
 * @param myInfiniteAlgorithm
 */
public class InfiniteEngine extends AbstractEngine implements IEngine{
	
	private IInfiniteAlgorithm myInfiniteAlgorithm;
	
	public InfiniteEngine(IEntityManager myEntityManager, IInfiniteAlgorithm infinite) {
		super(myEntityManager);
		myInfiniteAlgorithm=infinite;
		myInfiniteAlgorithm.initialize(myEntityManager);
	}
	
	@Override
	public IRestrictedGameData update(Collection<KeyCode> keysPressed, IRestrictedGameData gameData) throws CopyException {
		for (IEntity e : getCFactory().copyCollection(getEManager().getEntities())){
			myInfiniteAlgorithm.update(gameData, getEManager(),e);
		}
		return gameData;
	}

}
