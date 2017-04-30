package entity.presets;

import actions.BlockTopRegularCollision;
import actions.BounceOffBottom;
import actions.BounceOffLeft;
import actions.BounceOffRight;
import components.entityComponents.CollidableComponent;
import components.entityComponents.CollisionComponentType;
import components.entityComponents.CollisionComponentsHandler;
import components.entityComponents.ComponentType;
import components.entityComponents.ImagePropertiesComponent;
import components.entityComponents.LabelComponent;
import components.entityComponents.SideCollisionComponent;
import entity.Entity;

public class AbstractBlock extends Entity {
	private final static double DEFAULT_SIZE = 50;
	public AbstractBlock(int id) {
		super(id);
		addCollisionComponents();
	}
	private void addCollisionComponents(){
		this.addComponent(new LabelComponent("Defult Block"));
		this.addComponent(new ImagePropertiesComponent(DEFAULT_SIZE,DEFAULT_SIZE));
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
		scc.addActionForLabel(new LabelComponent("grrraah"), new BlockTopRegularCollision());
		SideCollisionComponent scq = new SideCollisionComponent(CollisionComponentType.Bottom);
		scq.addActionForLabel(new LabelComponent("grrraah"), new BounceOffBottom());
		SideCollisionComponent scr = new SideCollisionComponent(CollisionComponentType.Left);
		scr.addActionForLabel(new LabelComponent("grrraah"), new BounceOffLeft());
		SideCollisionComponent scb = new SideCollisionComponent(CollisionComponentType.Right);
		scb.addActionForLabel(new LabelComponent("grrraah"), new BounceOffRight());
		((CollisionComponentsHandler) this.getComponent(ComponentType.CollisionHandler)).addCollisionComponent(scc);
		((CollisionComponentsHandler) this.getComponent(ComponentType.CollisionHandler)).addCollisionComponent(scq);
		((CollisionComponentsHandler) this.getComponent(ComponentType.CollisionHandler)).addCollisionComponent(scb);
		((CollisionComponentsHandler) this.getComponent(ComponentType.CollisionHandler)).addCollisionComponent(scr);
	}

}
