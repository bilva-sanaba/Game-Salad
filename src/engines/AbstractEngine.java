// This entire file is part of my masterpiece.
// Bilva Sanaba
// It highlights good design as it is an abstract class which any class, especially IEngines, can easily extend and use 
// in order to reduce duplicate if they want access to the stored IEntityManager or CollectionFactory<IEntity>
package engines;

import engines.utility.CollectionFactory;
import entity.IEntity;
import entity.IEntityManager;
/**
 * Abstract class which all engines can extend in order to get the protected EntityManager
 * @author Bilva
 */

public abstract class AbstractEngine implements IEngine {
	private IEntityManager myEManager;
	private CollectionFactory<IEntity> myCFactory;
	
	public AbstractEngine(IEntityManager myEntityManager){
		myEManager = myEntityManager;
		myCFactory = new CollectionFactory<IEntity>();
	}	

	/**
	 * Returns the stored entity manager
	 */
	protected IEntityManager getEManager () {
		return myEManager;
	}
	/**
	 * Returns a factory which can be helpful for creating new Collections
	 * @return CollectionFactory<IEntity> factory for manipulating collections
	 */
	protected CollectionFactory<IEntity> getCFactory(){
		return myCFactory;
	}
}