package engines;

import java.util.Arrays;
import java.util.List;

import components.ComponentType;
import components.LocationComponent;
import components.VelocityComponent;
import entity.Entity;
import entity.IEntityManager;

public class MovementEngine extends AbstractEngine {
	
	private static final int LOCATION_LIST = 0;
	private static final int VELOCITY_LIST = 1;

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
		for(int currentEntity = 0; currentEntity < myComponents.get(LOCATION_LIST).size(); currentEntity++){
			LocationComponent myLocation = (LocationComponent) myComponents.get(LOCATION_LIST).get(currentEntity);
			VelocityComponent myVelocity = (VelocityComponent) myComponents.get(VELOCITY_LIST).get(currentEntity);
			myLocation.setX(myLocation.getX() + myVelocity.getX());
			myLocation.setY(myLocation.getY() + myVelocity.getY());
		}
		
	}
	
	public void add(Entity myEntity){
		myComponents.get(LOCATION_LIST).add(myEntity.getComponent(ComponentType.Location));
		myComponents.get(VELOCITY_LIST).add(myEntity.getComponent(ComponentType.Velocity));
	}

}
