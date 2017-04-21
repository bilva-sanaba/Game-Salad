package entity.presets;

import actions.BlockTopRegularCollision;
import actions.BounceOffBlockSide;
import actions.BounceOffBottom;
import actions.BounceOffLeft;
import actions.BounceOffTop;
import components.entityComponents.CollidableComponent;
import components.entityComponents.CollisionComponentType;
import components.entityComponents.CollisionComponentsHandler;
import components.entityComponents.ComponentType;
import components.entityComponents.EntityType;
import components.entityComponents.LabelComponent;
import components.entityComponents.SideCollisionComponent;
import components.entityComponents.TypeComponent;
import entity.Entity;

public class AbstractBlock extends Entity {

	public AbstractBlock(int id) {
		super(id);
		addCollisionComponents();
	}
	private void addCollisionComponents(){
		this.addComponent(new CollisionComponentsHandler());
		this.addComponent(new CollidableComponent(true));
		SideCollisionComponent scc = new SideCollisionComponent(CollisionComponentType.Top);
		scc.addActionForLabel(new LabelComponent("grrraah"), new BounceOffTop());
		SideCollisionComponent scq = new SideCollisionComponent(CollisionComponentType.Bottom);
		scq.addActionForLabel(new LabelComponent("grrraah"), new BounceOffBottom());
		SideCollisionComponent scr = new SideCollisionComponent(CollisionComponentType.Left);
		scr.addActionForLabel(new LabelComponent("grrraah"), new BounceOffLeft());
		SideCollisionComponent scb = new SideCollisionComponent(CollisionComponentType.Right);
		scb.addActionForLabel(new LabelComponent("grrraah"), new BounceOffBlockSide());
		((CollisionComponentsHandler) this.getComponent(ComponentType.CollisionHandler)).addCollisionComponent(scc);
		((CollisionComponentsHandler) this.getComponent(ComponentType.CollisionHandler)).addCollisionComponent(scq);
		((CollisionComponentsHandler) this.getComponent(ComponentType.CollisionHandler)).addCollisionComponent(scb);
		((CollisionComponentsHandler) this.getComponent(ComponentType.CollisionHandler)).addCollisionComponent(scr);
	}

}
