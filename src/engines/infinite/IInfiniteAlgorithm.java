// This entire file is part of my masterpiece.
// Bilva Sanaba
// This file exhibits good design as it provides an interface for Infinite Algorithms to follow. It follows the strategy
// design pattern so that the infinite engine can create infinite platforms using various algorithms. 
// Currently there are algorithms for looping infinitely in any direction, as well as looping while keeping
// certain Entities in place. However, if one wanted they could create an algorithm which better mimics a DoodleJump
// random generation or other varieties all by simply creating a new concrete implementation of this class.
package engines.infinite;

import entity.IEntity;
import entity.IEntityManager;
import exceptions.CopyException;
import gamedata.IRestrictedGameData;

public interface IInfiniteAlgorithm {	
	/**
	 * The infinite engine will call this update method with every currently existing IEntity 
	 * @param gameData current Gamedata
	 * @param myEntityManager Contains Collection<IEntity> with all IEntity in their current state
	 * @param e current IEntity being examined
	 * @throws CopyException
	 */
	void update(IRestrictedGameData gameData, IEntityManager myEntityManager, IEntity e) throws CopyException;
	/**
	 * Useful method for initializing attributes needed for looping such as size of the screen
	 * @param myEntityManager
	 */
	void initialize(IEntityManager myEntityManager);
}