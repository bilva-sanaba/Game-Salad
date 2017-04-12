package engines;

import java.util.Collection;
import java.util.List;

import components.entityComponents.ComponentType;
import components.entityComponents.LocationComponent;
import components.entityComponents.VelocityComponent;
import entity.Entity;
import entity.IEntity;
import entity.IEntityManager;
import javafx.scene.input.KeyCode;

public class PhysicsEngine extends AbstractEngine {

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
	public Collection<IEntity> update(Collection<KeyCode> keys) {
		for(int currentEntity = 0; currentEntity < myComponents.get(LOCATION_LIST).size(); currentEntity++){
			LocationComponent myLocation = (LocationComponent) myComponents.get(LOCATION_LIST).get(currentEntity);
			VelocityComponent myVelocity = (VelocityComponent) myComponents.get(VELOCITY_LIST).get(currentEntity);
			myLocation.setX(myLocation.getX() + myVelocity.getX());
			myLocation.setY(myLocation.getY() + myVelocity.getY());
		}
		return null;
		
	}

	public void add(Entity myEntity) {
		myComponents.get(LOCATION_LIST).add(
				myEntity.getComponent(ComponentType.Location));
		myComponents.get(VELOCITY_LIST).add(
				myEntity.getComponent(ComponentType.Velocity));
		myComponents.get(ACCELERATION_LIST).add(
				myEntity.getComponent(ComponentType.Acceleration));
	}



}
