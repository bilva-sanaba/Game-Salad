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
	
	
	
	public List<IEntity> handleCollision(Entity e0, Entity e1) {
		LabelComponent lc0 = (LabelComponent) e0.getComponent(ComponentType.Label);
		LabelComponent lc1 = (LabelComponent) e1.getComponent(ComponentType.Label);
		
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
		
		
