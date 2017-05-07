// This entire file is part of my masterpiece.
// Bilva Sanaba
// This class is another concrete version of the IInfiniteAlgorithm. It highlights good design as it shows how a complex
// infinite platformer can be made while reusing a significant amount of code. In particular this one does every thing that the standard
// looping algorithm does but also incorporates a check of an IComponenet. It highlights how the overall design of my masterpiece 
// facilitates ease in creating new similar Infinite algorithms 
package engines.infinite;

import entity.IEntity;
import entity.IEntityManager;
import exceptions.CopyException;
import gamedata.IRestrictedGameData;

public class ComplexInfiniteLoopAlgorithm extends InfiniteLoopAlgorithm implements IInfiniteAlgorithm {
	public ComplexInfiniteLoopAlgorithm(double x, double y) {
		super(x, y);
	}
	/**
	 * Runs infinite looping algorithm but keeps all entities with a true stationary component in place
	 */
	@Override
	public void update(IRestrictedGameData gameData, IEntityManager myEntityManager, IEntity e) throws CopyException {
		if (!isStationary(e)){
			super.update(gameData,myEntityManager,e);
		}
	}
}