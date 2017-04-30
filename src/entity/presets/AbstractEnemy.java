package entity.presets;

import actions.BlockTopRegularCollision;
import actions.BounceOffBottom;
import actions.BounceOffLeft;
import actions.BounceOffRight;
import actions.LeftDamageAction;
import actions.RightDamageAction;
import actions.BounceOffTop;
import actions.PowerupUsage;
import actions.RemoveAction;
import components.entityComponents.CollidableComponent;
import components.entityComponents.CollisionComponentType;
import components.entityComponents.CollisionComponentsHandler;
import components.entityComponents.ComponentType;
import components.entityComponents.EntityType;
import components.entityComponents.LabelComponent;
import components.entityComponents.SideCollisionComponent;
import components.entityComponents.TypeComponent;
import entity.Entity;

public class AbstractEnemy extends Entity{
	
	public AbstractEnemy(int id) {
		super(id);
		addCollisionComponents();
	}
	private void addCollisionComponents(){
		this.addComponent(new CollisionComponentsHandler());
		this.addComponent(new CollidableComponent(true));

		SideCollisionComponent scc = new SideCollisionComponent(CollisionComponentType.Top);
		scc.addActionForType(new TypeComponent(EntityType.Player), new PowerupUsage());
		scc.addActionForType(new TypeComponent(EntityType.Player), new BounceOffTop());
	//	scc.addActionForType(new TypeComponent(EntityType.Player), new RemoveAction());

		SideCollisionComponent scq = new SideCollisionComponent(CollisionComponentType.Bottom);
		scq.addActionForType(new TypeComponent(EntityType.Player), new LeftDamageAction());
		
		SideCollisionComponent scr = new SideCollisionComponent(CollisionComponentType.Left);
		scr.addActionForType(new TypeComponent(EntityType.Player), new LeftDamageAction());
		
		SideCollisionComponent scb = new SideCollisionComponent(CollisionComponentType.Right);
		scb.addActionForType(new TypeComponent(EntityType.Player), new RightDamageAction());

		((CollisionComponentsHandler) this.getComponent(ComponentType.CollisionHandler)).addCollisionComponent(scc);
		((CollisionComponentsHandler) this.getComponent(ComponentType.CollisionHandler)).addCollisionComponent(scq);
		((CollisionComponentsHandler) this.getComponent(ComponentType.CollisionHandler)).addCollisionComponent(scb);
		((CollisionComponentsHandler) this.getComponent(ComponentType.CollisionHandler)).addCollisionComponent(scr);
	}

}
