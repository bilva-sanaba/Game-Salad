package entity.presets;

import actions.Teleport;
import components.entityComponents.CollisionComponentType;
import components.entityComponents.CollisionComponentsHandler;
import components.entityComponents.ComponentType;
import components.entityComponents.EntityType;
import components.entityComponents.SideCollisionComponent;
import components.entityComponents.TypeComponent;

public class PortalBlock extends AbstractBlock {

	public PortalBlock(int id) {
		super(id);
		initialize();
	}
	private void initialize() {
		super.initializeBasicBlock(false);
		CollisionComponentsHandler cch = (CollisionComponentsHandler) this.getComponent(ComponentType.CollisionHandler);
		SideCollisionComponent left = new SideCollisionComponent(CollisionComponentType.Left);
		left.addActionForType(new TypeComponent(EntityType.Player), new Teleport(200,400));
		SideCollisionComponent top = new SideCollisionComponent(CollisionComponentType.Top);
		left.addActionForType(new TypeComponent(EntityType.Player), new Teleport(200,400));
		cch.addCollisionComponent(left);
		cch.addCollisionComponent(top);
	}

}
