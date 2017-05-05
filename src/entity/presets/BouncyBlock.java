package entity.presets;

import actions.BounceOffTop;
import components.entityComponents.CheckCollisionComponent;
import components.entityComponents.CollidableComponent;
import components.entityComponents.CollisionComponentsHandler;
import components.entityComponents.ComponentType;
import components.entityComponents.EntityType;
import components.entityComponents.ImagePropertiesComponent;
import components.entityComponents.LabelComponent;
import components.entityComponents.LocationComponent;
import components.entityComponents.SideCollisionComponent;
import components.entityComponents.SpriteComponent;
import components.entityComponents.TypeComponent;
import entity.Entity;

public class BouncyBlock extends AbstractBlock {

	
	
	
	public BouncyBlock(int id) {
		super(id);
		initialize();
			
		
		
	}
	
	private void initialize() {
		super.initializeBasicBlock(true);
		CollisionComponentsHandler collisionComponents = (CollisionComponentsHandler) this.getComponent(ComponentType.CollisionHandler);
		SideCollisionComponent top = collisionComponents.getCollisionComponent(CollisionComponentsHandler.TOP);
		top.clearMappings(null, new TypeComponent(EntityType.Player));
		top.addActionForType(new TypeComponent(EntityType.Player), new BounceOffTop());
	}
}