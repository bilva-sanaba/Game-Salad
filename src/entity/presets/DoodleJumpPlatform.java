package entity.presets;

import actions.BlockTopRegularCollision;
import actions.BounceOffBottom;
import actions.BounceOffLeft;
import actions.BounceOffRight;
import actions.BounceOffTop;
import actions.DeathAction;
import actions.GoalAction;
import components.entityComponents.CollidableComponent;
import components.entityComponents.CollisionComponentType;
import components.entityComponents.CollisionComponentsHandler;
import components.entityComponents.ComponentType;
import components.entityComponents.EntityType;
import components.entityComponents.ImagePropertiesComponent;
import components.entityComponents.LabelComponent;
import components.entityComponents.SideCollisionComponent;
import components.entityComponents.TimeComponent;
import components.entityComponents.TypeComponent;
import entity.Entity;

public class DoodleJumpPlatform extends Entity {
	private final static double DEFAULT_SIZE = 50;
	public DoodleJumpPlatform(int id){
		super(id);
		this.addComponent(new LabelComponent("Default Block"));
		this.addComponent(new ImagePropertiesComponent(DEFAULT_SIZE,DEFAULT_SIZE));
		this.addComponent(new CollisionComponentsHandler());
		this.addComponent(new CollidableComponent(true));
		this.addComponent(new TypeComponent(EntityType.Block));
		this.addComponent(new TimeComponent());
		SideCollisionComponent scc = new SideCollisionComponent(CollisionComponentType.Top);
		scc.addActionForType(new TypeComponent(EntityType.Player), new BounceOffTop());
		scc.addActionForType(new TypeComponent(EntityType.Player), new GoalAction(3));
		((CollisionComponentsHandler) this.getComponent(ComponentType.CollisionHandler)).addCollisionComponent(scc);
	}
}
