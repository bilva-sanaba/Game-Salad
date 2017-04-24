package entity.presets;

import actions.BlockTopRegularCollision;
import actions.BounceOffTop;
import actions.PowerupCreation;
import components.entityComponents.CollidableComponent;
import components.entityComponents.CollisionComponentType;
import components.entityComponents.CollisionComponentsHandler;
import components.entityComponents.ComponentType;
import components.entityComponents.EntityType;
import components.entityComponents.LabelComponent;
import components.entityComponents.ObjectCreationComponent;
import components.entityComponents.SideCollisionComponent;
import components.entityComponents.TypeComponent;
import entity.Entity;
import entity.IEntity;

public class AbstractMysteryBlock extends AbstractBlock{
	public AbstractMysteryBlock(int id, IEntity e) {
		super(id);
		addMysteryComponent(e);
	}
	private void addMysteryComponent(IEntity e){
		ObjectCreationComponent occ = new ObjectCreationComponent(e);
		this.addComponent(occ);
		((CollisionComponentsHandler) this.getComponent(ComponentType.CollisionHandler)).getCollisionComponent("Bottom").addActionForLabel(new LabelComponent("grrraah"), new PowerupCreation());


	}

}
