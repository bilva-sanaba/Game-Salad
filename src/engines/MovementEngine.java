package engines;
import java.util.*;
import components.*;
import components.entityComponents.AccelerationComponent;
import components.entityComponents.ComponentType;
import components.entityComponents.XYComponent;
import entity.*;
import entity.restricted.*;
import gamedata.IRestrictedGameData;
import components.entityComponents.ComponentType;
import components.entityComponents.LocationComponent;
import components.entityComponents.TerminalVelocityComponent;
import components.entityComponents.VelocityComponent;
import entity.Entity;
import entity.IEntity;
import entity.IEntityManager;
import javafx.scene.input.KeyCode;
public class MovementEngine extends AbstractEngine{
	
	public MovementEngine(IEntityManager myEntityManager) {
		super(myEntityManager);
	}
	@Override
	protected List<ComponentType> neededComponents() {	
		return null;
	}
	public IRestrictedGameData update(Collection<KeyCode> keys, IRestrictedGameData currentGameData) {
		for (IEntity e: getEManager().getEntities()) {
			if (hasComponent(e,ComponentType.Location)) {
				updateAllValues(e);
			}	
		}
		return currentGameData;
	}
	private void updateAllValues(IEntity e) {
		if (hasComponent(e, ComponentType.Velocity)) {
			updateLocation(e);
			if (hasComponent(e, ComponentType.Acceleration)) {
				updateMovement(e);
			}
			
//			placeInMap(entityMap, e);
//			//TODO: fix cast issue
//			changed.add((Entity) e);
		}
	}
	
	protected void updateLocation(IEntity e) {
		LocationComponent lc = (LocationComponent) e.getComponent(ComponentType.Location);
		VelocityComponent vc = (VelocityComponent) e.getComponent(ComponentType.Velocity);
		
		lc.setXY(lc.getX() + vc.getX(), lc.getY() + vc.getY());
		((IRestrictedEntity) e).changed(e);
	}
	
	private void updateMovement(IEntity e) {
		VelocityComponent vc = (VelocityComponent) e.getComponent(ComponentType.Velocity);
		AccelerationComponent ac = (AccelerationComponent) e.getComponent(ComponentType.Acceleration);
			
		vc.setX(vc.getX() + ac.getX());
		vc.setY(vc.getY() + ac.getY());
		
		//Handles terminal velocity
		if(e.getComponent(ComponentType.TerminalVelocity) != null){
			TerminalVelocityComponent tvc = (TerminalVelocityComponent) e.getComponent(ComponentType.TerminalVelocity);
			
			checkTerminalVelocityInX(vc, tvc);
			checkTerminalVelocityInY(vc, tvc);
		}
	}
	
	private void checkTerminalVelocityInX(VelocityComponent vc, TerminalVelocityComponent tvc){
		if(Math.abs(vc.getX()) > tvc.getX()){
			
			if(vc.getX() > 0){
				vc.setX(tvc.getX());
				System.out.println("TerminalVelocity in X is hit!");
			}
			else{
				vc.setX(-tvc.getX());
				System.out.println("TerminalVelocity in X is hit!");
			}
		}
	}
	
	private void checkTerminalVelocityInY(VelocityComponent vc, TerminalVelocityComponent tvc){
		if(Math.abs(vc.getY()) > tvc.getY()){
			
			if(vc.getY() > 0){
				vc.setY(tvc.getY());
			}
			else{
				vc.setY(-tvc.getY());
			}
		}
	}
	
//	private void placeInMap (Map <IEntity, IRestrictedEntity> entityMap, IEntity e) {
//		entityMap.put(e, ref.createRestrictedEntity(e));
//	}
}