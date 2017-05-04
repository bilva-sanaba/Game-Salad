package entity.presets;

import actions.SlowDown;
import components.entityComponents.CollisionComponentsHandler;
import components.entityComponents.ComponentType;
import components.entityComponents.EntityType;
import components.entityComponents.SideCollisionComponent;
import components.entityComponents.TypeComponent;

public class SlowBlock extends AbstractBlock {

	public SlowBlock(int id) {
		super(id);
		initialize();
	}

	private void initialize() {
		initializeBasicBlock(true);
		CollisionComponentsHandler collisionRepo = (CollisionComponentsHandler) this.getComponent(ComponentType.CollisionHandler);
		SideCollisionComponent top = collisionRepo.getCollisionComponent(CollisionComponentsHandler.TOP);
		top.clearMappings(null, new TypeComponent(EntityType.Player));
		top.addActionForType(new TypeComponent(EntityType.Player), new SlowDown());
	}

}
