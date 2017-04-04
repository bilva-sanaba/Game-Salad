package engines;

import java.util.Arrays;
import java.util.List;

import components.ComponentType;
import entity.IEntityManager;

public class MovementEngine extends AbstractEngine {
	public MovementEngine(IEntityManager myEntityManager) {
		super(myEntityManager);
	}

	/**
	 * Consider replacing this need in the constructor
	 */
	@Override
	protected List<ComponentType> neededComponents() {
		return Arrays.asList(ComponentType.Location,ComponentType.Velocity);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}