package actions;
import java.util.ArrayList;
import java.util.List;

import class_annotations.TopAction;
import components.entityComponents.AccelerationComponent;
import components.entityComponents.CollidableComponent;
import components.entityComponents.CollisionComponentType;
import components.entityComponents.CollisionComponentsHandler;
import components.entityComponents.ComponentType;
import components.entityComponents.ImagePropertiesComponent;
import components.entityComponents.LabelComponent;
import components.entityComponents.LocationComponent;
import components.entityComponents.SideCollisionComponent;
import components.entityComponents.SpriteComponent;
import components.entityComponents.VelocityComponent;
import entity.Entity;
import entity.IEntity;
import entity.IEntityManager;


@TopAction()
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