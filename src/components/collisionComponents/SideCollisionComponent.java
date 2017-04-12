package components.collisionComponents;
import actions.IAction;
import components.entityComponents.ComponentType;
import components.IComponent;
import entity.Entity;
public class SideCollisionComponent implements IComponent {
	private CollisionComponentType sideCollision;
	private IAction action;
	
	public SideCollisionComponent(CollisionComponentType sideOfCollision, IAction actionForSide) {
		sideCollision = sideOfCollision;
		action = actionForSide;
	}
	
	public void executeOnCollide(Entity e) {
		action.executeAction(e);
	}
	
	public CollisionComponentType whichSide() {
		return sideCollision;
	}
	@Override
	public ComponentType getComponentType() {
		// TODO Auto-generated method stub
		return ComponentType.CollisionSide;
	}
	@Override
	public IComponent newCopy() {
		// TODO Auto-generated method stub
		return null;
	}
	
}