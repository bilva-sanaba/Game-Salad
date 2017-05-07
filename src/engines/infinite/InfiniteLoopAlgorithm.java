// This entire file is part of my masterpiece.
// Bilva Sanaba
// This class highlights good design as it is a class which reduces code for several infinite algorithms. 
// In particular this class provides the framework for any class which loops through objects to create more
// infinitely. Initially the class was abstract and a vertical and horizontal algorithm class extended it. 
// However, by adding parameters intelligently, the class can now represent an infinite platformer in any direction significantly
// reducing the number of classes and code initially needed. 
package engines.infinite;

import components.entityComponents.ComponentType;
import components.entityComponents.LocationComponent;
import entity.IEntity;
import entity.IEntityManager;
import exceptions.CopyException;
import gamedata.IRestrictedGameData;

public class InfiniteLoopAlgorithm extends AbstractInfiniteAlgorithm implements IInfiniteAlgorithm{
	private double size;
	private double xPercent;
	private double yPercent;
	public InfiniteLoopAlgorithm(double x, double y){
		xPercent = Math.cos(x);
		yPercent = Math.sin(y);
	}
	@Override
	public void update(IRestrictedGameData gameData, IEntityManager myEntityManager, IEntity e) throws CopyException {
		LocationComponent lc = (LocationComponent) e.getComponent(ComponentType.Location);
		LocationComponent mainPlayer = getMainPlayer(myEntityManager);
		if (getDimensionalPosition(mainPlayer)-getDimensionalPosition(lc)>getSize()/2){
			IEntity newEntity = e.newCopy(e.getID());
			newEntity.addComponent(generateLocation(lc));
			e.changed(null);
			myEntityManager.getEntities().remove(e);
			myEntityManager.getEntities().add(newEntity);
			myEntityManager.changed(newEntity);
		}
	}

	private LocationComponent generateLocation(LocationComponent lc) {
		return new LocationComponent(lc.getX()+(size*xPercent), lc.getY() + (size*yPercent));
	}

	@Override
	public void initialize(IEntityManager myEntityManager) {
		size = getSize(myEntityManager);
	}
	
	private double getSize(){
		return size;
	}

	@Override
	protected double getDimensionalPosition(LocationComponent lc) {
		return lc.getX()*xPercent+lc.getY()*yPercent;
	}
}