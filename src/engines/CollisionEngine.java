package engines;

import java.util.Collection;
import java.util.List;

import components.ComponentType;
import entity.Entity;
import entity.IEntity;
import entity.IEntityManager;

/**
 * This engine handles all collisions When update is called it should use all
 * needed CollisionSubEngines (This provides an important area for design
 * choices as the neededComponents method would need to be changed if more
 * collision sub engines are added which use different components)
 * 
 * @author Bilva
 *
 */
public class CollisionEngine extends AbstractEngine {

	public CollisionEngine(IEntityManager myEntityManager) {
		super(myEntityManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<ComponentType> neededComponents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<IEntity> update() {
		return null;
		// TODO Auto-generated method stub

	}

}
