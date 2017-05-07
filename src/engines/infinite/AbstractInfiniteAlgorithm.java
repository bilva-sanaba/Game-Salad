// This entire file is part of my masterpiece.
// Bilva Sanaba
// This class shows good design by saving code through the use of an abstract class which provides several methods
// that are of use to  most infinite algorithms.
package engines.infinite;

import components.entityComponents.ComponentType;
import components.entityComponents.LocationComponent;
import components.entityComponents.StationaryComponent;
import entity.IEntity;
import entity.IEntityManager;

public abstract class AbstractInfiniteAlgorithm implements IInfiniteAlgorithm{
	protected LocationComponent getMainPlayer(IEntityManager entityManager){
		LocationComponent mainPlayer = null;
		for (IEntity e : entityManager.getEntities()){
			if (e.hasComponent(ComponentType.KeyInput)){
				mainPlayer = (LocationComponent) e.getComponent(ComponentType.Location);
				break;
			}
		}
		return mainPlayer;
	}
	protected double getSize(IEntityManager entityManager){
		Double max=0.;
		Double min=0.;
		for (IEntity e : entityManager.getEntities()){
			LocationComponent lc = (LocationComponent) e.getComponent(ComponentType.Location);
			double currentPos = getDimensionalPosition(lc);
			if (!isStationary(e)){
				max = Math.max(max, currentPos);
				min = Math.min(min, currentPos);
			}
		}
		return max-min;
	}
	
	protected abstract double getDimensionalPosition(LocationComponent lc);
	
	protected boolean isStationary(IEntity e){
		if (!e.hasComponent(ComponentType.Stationary)){
			return false;
		}else {
			StationaryComponent sc = (StationaryComponent) e.getComponent(ComponentType.Stationary);
			return sc.getBoolean();
		}
	}
}