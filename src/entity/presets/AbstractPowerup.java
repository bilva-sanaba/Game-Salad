package entity.presets;

import actions.BlockTopRegularCollision;
import actions.BounceOffBlockSide;
import actions.DoubleSize;
import actions.PowerupUsage;
<<<<<<< HEAD
import actions.Teleport;
import components.collisionComponents.CollisionComponentType;
import components.collisionComponents.CollisionComponentsHandler;
import components.collisionComponents.SideCollisionComponent;
=======
>>>>>>> master
import components.entityComponents.CollidableComponent;
import components.entityComponents.CollisionComponentType;
import components.entityComponents.CollisionComponentsHandler;
import components.entityComponents.ComponentType;
import components.entityComponents.LabelComponent;
import components.entityComponents.SideCollisionComponent;
import entity.Entity;

public class AbstractPowerup extends Entity {

	public AbstractPowerup(int id) {
		super(id);
		addPowerupUsage();
	}
	private void addPowerupUsage(){
		this.addComponent(new CollisionComponentsHandler());
		this.addComponent(new CollidableComponent(true));
		SideCollisionComponent scc = new SideCollisionComponent(CollisionComponentType.Top);
		scc.addActionForLabel(new LabelComponent("grrraah"), new PowerupUsage());
		scc.addActionForLabel(new LabelComponent("grrraah"), new DoubleSize(false));

		SideCollisionComponent scq = new SideCollisionComponent(CollisionComponentType.Bottom);
		scq.addActionForLabel(new LabelComponent("grrraah"), new PowerupUsage());
		scq.addActionForLabel(new LabelComponent("grrraah"), new DoubleSize(false));
		SideCollisionComponent scr = new SideCollisionComponent(CollisionComponentType.Left);
		scr.addActionForLabel(new LabelComponent("grrraah"), new PowerupUsage());
		scr.addActionForLabel(new LabelComponent("grrraah"), new DoubleSize(true));
		SideCollisionComponent scb = new SideCollisionComponent(CollisionComponentType.Right);
		scb.addActionForLabel(new LabelComponent("grrraah"), new PowerupUsage());
		scb.addActionForLabel(new LabelComponent("grrraah"), new DoubleSize(true));
		((CollisionComponentsHandler) this.getComponent(ComponentType.CollisionHandler)).addCollisionComponent(scc);
		((CollisionComponentsHandler) this.getComponent(ComponentType.CollisionHandler)).addCollisionComponent(scq);
		((CollisionComponentsHandler) this.getComponent(ComponentType.CollisionHandler)).addCollisionComponent(scb);
		((CollisionComponentsHandler) this.getComponent(ComponentType.CollisionHandler)).addCollisionComponent(scr);
	}

}
