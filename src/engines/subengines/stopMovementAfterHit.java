package engines.subengines;
import java.util.List;
import components.entityComponents.ComponentType;
import components.entityComponents.LabelComponent;
import components.collisionComponents.SideCollisionComponent;
import engines.CollisionPair;
import entity.Entity;
import entity.IEntity;
import entity.IEntityManager;
public class stopMovementAfterHit implements ISubEngine {
	private boolean alreadyHit=false;
	
	@Override
	public void addEntityManager(IEntityManager entManager) {
		// TODO Auto-generated method stub
		
	}
	
	public void handleCollision(Entity e0, Entity e1) {
		LabelComponent lc0 = (LabelComponent) e0.getComponent(ComponentType.Label);
		LabelComponent lc1 = (LabelComponent) e1.getComponent(ComponentType.Label);
		if (alreadyHit != true || alreadyHit == true) {
			if (e0 == null || e1 == null) {
				System.out.println("wtf");
			}
			if (e0.getComponent(ComponentType.Label) == null || e1.getComponent(ComponentType.Label) == null) {
				System.out.println("more wtfs");
			}
			
			if (lc1.getLabel().equals("Block")) {
				System.out.println("should be printing");
				SideCollisionComponent scc = (SideCollisionComponent) e1.getComponent(ComponentType.CollisionSide);
				scc.executeOnCollide(e0);
				alreadyHit = true;
			}
		}
		
		
	}
	@Override
	public List<IEntity> handleCollision(CollisionPair collisionObjectComponents) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<ComponentType> getNecessaryComponents(String sideOfCollision) {
		// TODO Auto-generated method stub
		return null;
	}
}