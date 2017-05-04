package entity.presets;

import actions.BlockTopRegularCollision;
import actions.ChangeMusicAction;
import actions.DoubleSize;
import actions.MusicPlayAction;
import actions.PowerupUsage;

import components.entityComponents.CollidableComponent;
import components.entityComponents.CollisionComponentType;
import components.entityComponents.CollisionComponentsHandler;
import components.entityComponents.ComponentType;
import components.entityComponents.ImagePropertiesComponent;
import components.entityComponents.LabelComponent;
import components.entityComponents.SideCollisionComponent;
import entity.Entity;

public class AbstractPowerup extends Entity {
	private final static double DEFAULT_SIZE = 50;

	public AbstractPowerup(int id) {
		super(id);
		addPowerupUsage();
	}
	private void addPowerupUsage(){
		this.addComponent(new ImagePropertiesComponent(DEFAULT_SIZE,DEFAULT_SIZE));
		this.addComponent(new LabelComponent("Defult Power Up"));
		this.addComponent(new CollisionComponentsHandler());
		this.addComponent(new CollidableComponent(true));
		SideCollisionComponent scc = new SideCollisionComponent(CollisionComponentType.Top);
		scc.addActionForLabel(new LabelComponent("grrraah"), new PowerupUsage());
		scc.addActionForLabel(new LabelComponent("grrraah"), new DoubleSize(true));
		scc.addActionForLabel(new LabelComponent("grrraah"), new MusicPlayAction("badboujee.wav"));
		SideCollisionComponent scq = new SideCollisionComponent(CollisionComponentType.Bottom);
		scq.addActionForLabel(new LabelComponent("grrraah"), new PowerupUsage());
		scq.addActionForLabel(new LabelComponent("grrraah"), new DoubleSize(true));
		scq.addActionForLabel(new LabelComponent("grrraah"), new MusicPlayAction("badboujee.wav"));
		SideCollisionComponent scr = new SideCollisionComponent(CollisionComponentType.Left);
		scr.addActionForLabel(new LabelComponent("grrraah"), new PowerupUsage());
		scr.addActionForLabel(new LabelComponent("grrraah"), new DoubleSize(true));
		scr.addActionForLabel(new LabelComponent("grrraah"), new MusicPlayAction("badboujee.wav"));
		SideCollisionComponent scb = new SideCollisionComponent(CollisionComponentType.Right);
		scb.addActionForLabel(new LabelComponent("grrraah"), new PowerupUsage());
		scb.addActionForLabel(new LabelComponent("grrraah"), new DoubleSize(true));
		scb.addActionForLabel(new LabelComponent("grrraah"), new MusicPlayAction("badboujee.wav"));
		((CollisionComponentsHandler) this.getComponent(ComponentType.CollisionHandler)).addCollisionComponent(scc);
		((CollisionComponentsHandler) this.getComponent(ComponentType.CollisionHandler)).addCollisionComponent(scq);
		((CollisionComponentsHandler) this.getComponent(ComponentType.CollisionHandler)).addCollisionComponent(scb);
		((CollisionComponentsHandler) this.getComponent(ComponentType.CollisionHandler)).addCollisionComponent(scr);
	}

}
