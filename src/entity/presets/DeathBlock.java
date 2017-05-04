package entity.presets;

import actions.BlockTopRegularCollision;
import actions.BounceOffBottom;
import actions.BounceOffLeft;
import actions.BounceOffRight;
import actions.DeathAction;
import actions.Teleport;
import components.entityComponents.CheckCollisionComponent;
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

public class DeathBlock extends Entity {
	private final static double DEFAULT_SIZE = 50;
	public DeathBlock(int id) {
		super(id);
		addCollisionComponents();
	}
	private void addCollisionComponents(){
		this.addComponent(new TypeComponent(EntityType.Block));
		this.addComponent(new LabelComponent("Default Block"));
		this.addComponent(new ImagePropertiesComponent(DEFAULT_SIZE,DEFAULT_SIZE));
		this.addComponent(new CollisionComponentsHandler());
		this.addComponent(new CollidableComponent(true));
		this.addComponent(new CheckCollisionComponent(true));
		this.addComponent(new TimeComponent());
//		SideCollisionComponent scc = new SideCollisionComponent(CollisionComponentType.Top);
//		scc.addActionForType(new TypeComponent(EntityType.Player), new BlockTopRegularCollision());
//		SideCollisionComponent scq = new SideCollisionComponent(CollisionComponentType.Bottom);
//		scq.addActionForType(new TypeComponent(EntityType.Player), new BlockTopRegularCollision());
//		SideCollisionComponent scr = new SideCollisionComponent(CollisionComponentType.Left);
//		scr.addActionForType(new TypeComponent(EntityType.Player), new BounceOffBlockSide());
//		SideCollisionComponent scb = new SideCollisionComponent(CollisionComponentType.Right);
//		scb.addActionForType(new TypeComponent(EntityType.Player), new BounceOffBlockSide());
		SideCollisionComponent scc = new SideCollisionComponent(CollisionComponentType.Top);
		scc.addActionForType(new TypeComponent(EntityType.Player), new DeathAction());

		SideCollisionComponent scq = new SideCollisionComponent(CollisionComponentType.Bottom);
		scq.addActionForType(new TypeComponent(EntityType.Player), new DeathAction());

		SideCollisionComponent scr = new SideCollisionComponent(CollisionComponentType.Left);
		scr.addActionForType(new TypeComponent(EntityType.Player), new DeathAction());

		SideCollisionComponent scb = new SideCollisionComponent(CollisionComponentType.Right);
		scb.addActionForType(new TypeComponent(EntityType.Player), new DeathAction());


		((CollisionComponentsHandler) this.getComponent(ComponentType.CollisionHandler)).addCollisionComponent(scc);
		((CollisionComponentsHandler) this.getComponent(ComponentType.CollisionHandler)).addCollisionComponent(scq);
		((CollisionComponentsHandler) this.getComponent(ComponentType.CollisionHandler)).addCollisionComponent(scb);
		((CollisionComponentsHandler) this.getComponent(ComponentType.CollisionHandler)).addCollisionComponent(scr);
	}

}
