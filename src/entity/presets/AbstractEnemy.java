package entity.presets;

import actions.BlockTopRegularCollision;
import actions.BounceOffBottom;
import actions.BounceOffLeft;
import actions.BounceOffRight;
import actions.BounceOffTop;
import actions.PowerupUsage;
import actions.RemoveAction;
import components.entityComponents.CollidableComponent;
import components.entityComponents.CollisionComponentType;
import components.entityComponents.CollisionComponentsHandler;
import components.entityComponents.ComponentType;
import components.entityComponents.LabelComponent;
import components.entityComponents.SideCollisionComponent;
import entity.Entity;

public class AbstractEnemy extends Entity {
//	MonsterRadioButton = Label, ImageProperties, Health, Strength, Monster, Step, Collidable

	public AbstractEnemy(int id) {
		super(id);
		this.addComponent(new CollisionComponentsHandler());
		this.addComponent(new CollidableComponent(true));
		SideCollisionComponent scc = new SideCollisionComponent(CollisionComponentType.Top);
		scc.addActionForLabel(new LabelComponent("grrraah"), new PowerupUsage());
		scc.addActionForLabel(new LabelComponent("grrraah"), new BounceOffTop());
		scc.addActionForLabel(new LabelComponent("grrraa"), new RemoveAction());
		scc.addActionForLabel(new LabelComponent("grrraa"), new PowerupUsage());
		SideCollisionComponent scq = new SideCollisionComponent(CollisionComponentType.Bottom);
		scq.addActionForLabel(new LabelComponent("grrraa"), new RemoveAction());
		scq.addActionForLabel(new LabelComponent("grrraa"), new PowerupUsage());
		SideCollisionComponent scr = new SideCollisionComponent(CollisionComponentType.Left);
		scr.addActionForLabel(new LabelComponent("grrraa"), new RemoveAction());
		scr.addActionForLabel(new LabelComponent("grrraa"), new PowerupUsage());
		SideCollisionComponent scb = new SideCollisionComponent(CollisionComponentType.Right);
		scr.addActionForLabel(new LabelComponent("grrraa"), new RemoveAction());
		scr.addActionForLabel(new LabelComponent("grrraa"), new PowerupUsage());
		((CollisionComponentsHandler) this.getComponent(ComponentType.CollisionHandler)).addCollisionComponent(scc);
		((CollisionComponentsHandler) this.getComponent(ComponentType.CollisionHandler)).addCollisionComponent(scq);
		((CollisionComponentsHandler) this.getComponent(ComponentType.CollisionHandler)).addCollisionComponent(scb);
		((CollisionComponentsHandler) this.getComponent(ComponentType.CollisionHandler)).addCollisionComponent(scr);
	}

}
