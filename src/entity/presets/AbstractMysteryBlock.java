package entity.presets;

import actions.BlockTopRegularCollision;
import actions.PowerupCreation;
import components.collisionComponents.CollisionComponentType;
import components.collisionComponents.CollisionComponentsHandler;
import components.collisionComponents.SideCollisionComponent;
import components.entityComponents.CollidableComponent;
import components.entityComponents.ComponentType;
import components.entityComponents.EntityType;
import components.entityComponents.LabelComponent;
import components.entityComponents.ObjectCreationComponent;
import components.entityComponents.TypeComponent;
import entity.Entity;
import entity.IEntity;

public class AbstractMysteryBlock extends Entity{
	public AbstractMysteryBlock(int id, IEntity e) {
		super(id);
		addMysteryComponent(e);
	}
	private void addMysteryComponent(IEntity e){
		this.addComponent(new CollisionComponentsHandler());
		this.addComponent(new CollidableComponent(true));
//		SideCollisionComponent scq = new SideCollisionComponent(CollisionComponentType.Bottom);
//		scq.addActionForType(new TypeComponent(EntityType.Player), new PowerupCreation());
//		((CollisionComponentsHandler) this.getComponent(ComponentType.CollisionHandler)).addCollisionComponent(scq);
		ObjectCreationComponent occ = new ObjectCreationComponent(e);
		this.addComponent(occ);
		SideCollisionComponent scc = new SideCollisionComponent(CollisionComponentType.Top);
		scc.addActionForLabel(new LabelComponent("grrraah"), new PowerupCreation());
		scc.addActionForLabel(new LabelComponent("grrraah"), new BlockTopRegularCollision());
		SideCollisionComponent scv = new SideCollisionComponent(CollisionComponentType.Bottom);
		scv.addActionForLabel(new LabelComponent("grrraah"), new PowerupCreation());
		scv.addActionForLabel(new LabelComponent("grrraah"), new BlockTopRegularCollision());
		SideCollisionComponent scb = new SideCollisionComponent(CollisionComponentType.Right);
		scb.addActionForLabel(new LabelComponent("grrraah"), new PowerupCreation());
		scb.addActionForLabel(new LabelComponent("grrraah"), new BlockTopRegularCollision());
		SideCollisionComponent scn = new SideCollisionComponent(CollisionComponentType.Left);
		scn.addActionForLabel(new LabelComponent("grrraah"), new PowerupCreation());
		scn.addActionForLabel(new LabelComponent("grrraah"), new BlockTopRegularCollision());
		((CollisionComponentsHandler) this.getComponent(ComponentType.CollisionHandler)).addCollisionComponent(scc);	
		((CollisionComponentsHandler) this.getComponent(ComponentType.CollisionHandler)).addCollisionComponent(scv);
		((CollisionComponentsHandler) this.getComponent(ComponentType.CollisionHandler)).addCollisionComponent(scb);
		((CollisionComponentsHandler) this.getComponent(ComponentType.CollisionHandler)).addCollisionComponent(scn);
	}

}
