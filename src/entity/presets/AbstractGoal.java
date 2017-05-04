package entity.presets;

import actions.BlockTopRegularCollision;
import actions.GoalAction;
import components.entityComponents.CollidableComponent;
import components.entityComponents.CollisionComponentType;
import components.entityComponents.CollisionComponentsHandler;
import components.entityComponents.ComponentType;
import components.entityComponents.EntityType;
import components.entityComponents.ImagePropertiesComponent;
import components.entityComponents.LabelComponent;
import components.entityComponents.SideCollisionComponent;
import components.entityComponents.TypeComponent;
import entity.Entity;

public class AbstractGoal extends Entity{
	public static final int DEFAULT_SIZE = 50;

	public AbstractGoal(int id) {
		super(id);
		addCollisionComponents();
		this.addComponent(new ImagePropertiesComponent(DEFAULT_SIZE,DEFAULT_SIZE));
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
		scc.addActionForType(new TypeComponent(EntityType.Player), new GoalAction(1));
		
		SideCollisionComponent scq = new SideCollisionComponent(CollisionComponentType.Bottom);
		scq.addActionForType(new TypeComponent(EntityType.Player), new GoalAction(1));
		
		SideCollisionComponent scr = new SideCollisionComponent(CollisionComponentType.Left);
		scr.addActionForType(new TypeComponent(EntityType.Player), new GoalAction(1));
		
		SideCollisionComponent scb = new SideCollisionComponent(CollisionComponentType.Right);
		scb.addActionForType(new TypeComponent(EntityType.Player), new GoalAction(1));
		
		((CollisionComponentsHandler) this.getComponent(ComponentType.CollisionHandler)).addCollisionComponent(scc);
		((CollisionComponentsHandler) this.getComponent(ComponentType.CollisionHandler)).addCollisionComponent(scq);
		((CollisionComponentsHandler) this.getComponent(ComponentType.CollisionHandler)).addCollisionComponent(scb);
		((CollisionComponentsHandler) this.getComponent(ComponentType.CollisionHandler)).addCollisionComponent(scr);
	}

}
