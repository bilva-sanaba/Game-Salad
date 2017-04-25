package entity.presets;

import actions.IAction;
import actions.PowerupCreation;
import actions.RemoveAction;
import components.entityComponents.CollisionComponentsHandler;
import components.entityComponents.ComponentType;
import components.entityComponents.EntityType;
import components.entityComponents.LabelComponent;
import components.entityComponents.TypeComponent;

public class AbstractBreakableBox extends AbstractBlock{

	public AbstractBreakableBox(int id) {
		super(id);
		addBreakability();
	}
	private void addBreakability(){
		((CollisionComponentsHandler) this.getComponent(ComponentType.CollisionHandler)).getCollisionComponent("Bottom").addActionForType(new TypeComponent(EntityType.Projectile), new RemoveAction());
		((CollisionComponentsHandler) this.getComponent(ComponentType.CollisionHandler)).getCollisionComponent("Left").addActionForType(new TypeComponent(EntityType.Projectile), new RemoveAction());
		((CollisionComponentsHandler) this.getComponent(ComponentType.CollisionHandler)).getCollisionComponent("Right").addActionForType(new TypeComponent(EntityType.Projectile), new RemoveAction());
		((CollisionComponentsHandler) this.getComponent(ComponentType.CollisionHandler)).getCollisionComponent("Top").addActionForType(new TypeComponent(EntityType.Projectile), new RemoveAction());
	}

}
