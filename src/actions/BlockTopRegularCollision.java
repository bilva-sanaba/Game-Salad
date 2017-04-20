package actions;
import java.util.ArrayList;
import java.util.List;

import components.collisionComponents.CollisionComponentType;
import components.collisionComponents.CollisionComponentsHandler;
import components.collisionComponents.SideCollisionComponent;
import components.entityComponents.CollidableComponent;
import components.entityComponents.ComponentType;
import components.entityComponents.ImagePropertiesComponent;
import components.entityComponents.LabelComponent;
import components.entityComponents.SpriteComponent;
import components.movementcomponents.AccelerationComponent;
import components.movementcomponents.LocationComponent;
import components.movementcomponents.VelocityComponent;
import entity.Entity;
import entity.IEntity;
import entity.IEntityManager;

public class BlockTopRegularCollision implements IAction {
	
	@Override
	public List<IEntity> executeAction(IEntity e, IEntity e1, IEntityManager myEM) {
		LabelComponent lc = (LabelComponent) e.getComponent(ComponentType.Label);
		if (lc == null) {
		}
		if (!lc.getLabel().equals("Block")) {
			VelocityComponent vc = (VelocityComponent) e.getComponent(ComponentType.Velocity);
			AccelerationComponent ac = (AccelerationComponent) e.getComponent(ComponentType.Acceleration);
			
			
			if (vc.getY() > 0) {
				vc.setY(0);
//				ac.setY(0);
			}
		}
		return new ArrayList<IEntity>();

	}
}