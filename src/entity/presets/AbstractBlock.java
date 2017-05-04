package entity.presets;

import actions.BlockBottomRegularCollision;
import actions.BlockLeftRegularCollision;
import actions.BlockRightRegularCollision;
import actions.BlockTopRegularCollision;
import actions.BounceOffBottom;
import actions.BounceOffLeft;
import actions.BounceOffRight;
import components.entityComponents.CheckCollisionComponent;
import components.entityComponents.CollidableComponent;
import components.entityComponents.CollisionComponentType;
import components.entityComponents.CollisionComponentsHandler;
import components.entityComponents.ComponentType;
import components.entityComponents.EntityType;
import components.entityComponents.ImagePropertiesComponent;
import components.entityComponents.LabelComponent;
import components.entityComponents.SideCollisionComponent;
import components.entityComponents.TimeComponent;
import components.entityComponents.TypeComponent;
import entity.Entity;

public class AbstractBlock extends Entity {
	private final static double DEFAULT_SIZE = 50;
	public AbstractBlock(int id) {
		super(id);
	
		addCollisionComponents();
	}
	
	public void addCollisionComponents(){
		initializeBasicBlock(true);

	}
	
	public void initializeBasicBlock(boolean initializeCollisionComponents) {
		this.addComponent(new ImagePropertiesComponent(DEFAULT_SIZE,DEFAULT_SIZE));
		this.addComponent(new CollisionComponentsHandler());
		this.addComponent(new CollidableComponent(true));
		this.addComponent(new TypeComponent(EntityType.Block));
		this.addComponent(new CheckCollisionComponent(false));
		if (initializeCollisionComponents) {
			initializeBasicCollisionComponents();
		}
		
	}
	public void initializeBasicCollisionComponents() {
		CollisionComponentsHandler collisionRepo = (CollisionComponentsHandler) this.getComponent(ComponentType.CollisionHandler);
		SideCollisionComponent top = new SideCollisionComponent(CollisionComponentType.Top);
		System.out.println("this occurs line 50 of abstractblock.java");
		for(EntityType type : EntityType.values()) {
			top.addActionForType(new TypeComponent(type), new BlockTopRegularCollision());
		}
		SideCollisionComponent left = new SideCollisionComponent(CollisionComponentType.Left);
		for (EntityType type : EntityType.values()) {
			left.addActionForType(new TypeComponent(type), new BlockLeftRegularCollision());
		}
		SideCollisionComponent right = new SideCollisionComponent(CollisionComponentType.Right);
		for (EntityType type : EntityType.values()) {
			right.addActionForType(new TypeComponent(type), new BlockRightRegularCollision());
		}
		SideCollisionComponent bottom = new SideCollisionComponent(CollisionComponentType.Bottom);
		for(EntityType type : EntityType.values()) {
			bottom.addActionForType(new TypeComponent(type), new BlockBottomRegularCollision());
		}
		collisionRepo.addCollisionComponent(top);
		collisionRepo.addCollisionComponent(left);
		collisionRepo.addCollisionComponent(right);
		collisionRepo.addCollisionComponent(bottom);
		
	}
	

}