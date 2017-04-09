package engines;

import java.util.List;

import components.ComponentType;
import components.LocationComponent;
import components.VelocityComponent;
import entity.Entity;
import entity.IEntityManager;

public class PhysicsEngine extends AbstractEngine{
	
	public static final int LOCATION_LIST = 0;
	public static final int VELOCITY_LIST = 1;
	public static final int ACCELERATION_LIST = 2;

	public PhysicsEngine(IEntityManager myEntityManager) {
		super(myEntityManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected List<ComponentType> neededComponents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update() {
		for(int currentEntity = 0; currentEntity < myComponents.get(LOCATION_LIST).size(); currentEntity++){
			LocationComponent myLocation = (LocationComponent) myComponents.get(LOCATION_LIST).get(currentEntity);
			VelocityComponent myVelocity = (VelocityComponent) myComponents.get(VELOCITY_LIST).get(currentEntity));
			myLocation.setX(myLocation.getX() + myVelocity.getX());
			myLocation.setY(myLocation.getY() + myVelocity.getY());
		}
		
	}
	
	public void add(Entity myEntity){
		myComponents.get(LOCATION_LIST).add(myEntity.getComponent(ComponentType.Location));
		myComponents.get(VELOCITY_LIST).add(myEntity.getComponent(ComponentType.Velocity));
		myComponents.get(ACCELERATION_LIST).add(myEntity.getComponent(ComponentType.Acceleration));
	}

}
