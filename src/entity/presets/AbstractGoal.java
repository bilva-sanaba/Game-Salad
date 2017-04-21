package entity.presets;

import actions.BlockTopRegularCollision;
import actions.BounceOffBlockSide;
import actions.GoalAction;
import components.collisionComponents.CollisionComponentType;
import components.collisionComponents.CollisionComponentsHandler;
import components.collisionComponents.SideCollisionComponent;
import components.entityComponents.CollidableComponent;
import components.entityComponents.ComponentType;
import components.entityComponents.EntityType;
import components.entityComponents.LabelComponent;
import components.entityComponents.TypeComponent;
import entity.Entity;

public class AbstractGoal extends Entity{

	public AbstractGoal(int id) {
		super(id);
		addCollisionComponents();
	}
	
	private void addCollisionComponents(){
		this.addComponent(new CollisionComponentsHandler());
		this.addComponent(new CollidableComponent(true));
//		SideCollisionComponent scc = new SideCollisionComponent(CollisionComponentType.Top);
//		scc.addActionForType(new TypeComponent(EntityType.Player), new BlockTopRegularCollision());
//		SideCollisionComponent scq = new SideCollisionComponent(CollisionComponentType.Bottom);
//		scq.addActionForType(new TypeComponent(EntityType.Player), new BlockTopRegularCollision());
//		SideCollisionComponent scr = new SideCollisionComponent(CollisionComponentType.Left);
//		scr.addActionForType(new TypeComponent(EntityType.Player), new BounceOffBlockSide());
//		SideCollisionComponent scb = new SideCollisionComponent(CollisionComponentType.Right);
//		scb.addActionForType(new TypeComponent(EntityType.Player), new BounceOffBlockSide());
		
		SideCollisionComponent scc = new SideCollisionComponent(CollisionComponentType.Top);	
		scc.addActionForType(new TypeComponent(EntityType.Player), new GoalAction());
		
		SideCollisionComponent scq = new SideCollisionComponent(CollisionComponentType.Bottom);
		scq.addActionForType(new TypeComponent(EntityType.Player), new GoalAction());
		
		SideCollisionComponent scr = new SideCollisionComponent(CollisionComponentType.Left);
		scr.addActionForType(new TypeComponent(EntityType.Player), new GoalAction());
		
		SideCollisionComponent scb = new SideCollisionComponent(CollisionComponentType.Right);
		scb.addActionForType(new TypeComponent(EntityType.Player), new GoalAction());
		
		((CollisionComponentsHandler) this.getComponent(ComponentType.CollisionHandler)).addCollisionComponent(scc);
		((CollisionComponentsHandler) this.getComponent(ComponentType.CollisionHandler)).addCollisionComponent(scq);
		((CollisionComponentsHandler) this.getComponent(ComponentType.CollisionHandler)).addCollisionComponent(scb);
		((CollisionComponentsHandler) this.getComponent(ComponentType.CollisionHandler)).addCollisionComponent(scr);
	}

}
