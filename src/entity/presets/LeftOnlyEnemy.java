package entity.presets;

import actions.BounceOffTop;
import actions.LeftDamageAction;
import actions.PowerupUsage;
import actions.RightDamageAction;
import components.entityComponents.CollidableComponent;
import components.entityComponents.CollisionComponentType;
import components.entityComponents.CollisionComponentsHandler;
import components.entityComponents.ComponentType;
import components.entityComponents.EntityType;
import components.entityComponents.ImagePropertiesComponent;
import components.entityComponents.LabelComponent;
import components.entityComponents.MonsterType;
import components.entityComponents.MonsterTypeComponent;
import components.entityComponents.SideCollisionComponent;
import components.entityComponents.StepComponent;
import components.entityComponents.TypeComponent;
import components.entityComponents.VelocityComponent;
import entity.Entity;

public class LeftOnlyEnemy extends Entity{

	public LeftOnlyEnemy(int id) {
		super(id);
		addCollisionComponents();
		}
	
		private void addCollisionComponents(){
			this.addComponent(new LabelComponent("Defult Enemy"));
			this.addComponent(new CollisionComponentsHandler());
			this.addComponent(new CollidableComponent(true));
			this.addComponent(new MonsterTypeComponent(MonsterType.Left));
			this.addComponent(new StepComponent(10));
			this.addComponent(new VelocityComponent(-3,0));
			this.addComponent(new ImagePropertiesComponent(50,50));

			SideCollisionComponent scc = new SideCollisionComponent(CollisionComponentType.Top);
			scc.addActionForType(new TypeComponent(EntityType.Player), new PowerupUsage());
			scc.addActionForType(new TypeComponent(EntityType.Player), new BounceOffTop());
		//	scc.addActionForType(new TypeComponent(EntityType.Player), new RemoveAction());

			SideCollisionComponent scq = new SideCollisionComponent(CollisionComponentType.Bottom);
			scq.addActionForType(new TypeComponent(EntityType.Player), new LeftDamageAction());
//			scq.addActionForType(new TypeComponent(EntityType.Player), new DeathAction());
			
			SideCollisionComponent scr = new SideCollisionComponent(CollisionComponentType.Left);
			scr.addActionForType(new TypeComponent(EntityType.Player), new LeftDamageAction());
//			scr.addActionForType(new TypeComponent(EntityType.Player), new DeathAction());
			SideCollisionComponent scb = new SideCollisionComponent(CollisionComponentType.Right);
			scb.addActionForType(new TypeComponent(EntityType.Player), new RightDamageAction());
//			scb.addActionForType(new TypeComponent(EntityType.Player), new DeathAction());
			((CollisionComponentsHandler) this.getComponent(ComponentType.CollisionHandler)).addCollisionComponent(scc);
			((CollisionComponentsHandler) this.getComponent(ComponentType.CollisionHandler)).addCollisionComponent(scq);
			((CollisionComponentsHandler) this.getComponent(ComponentType.CollisionHandler)).addCollisionComponent(scb);
			((CollisionComponentsHandler) this.getComponent(ComponentType.CollisionHandler)).addCollisionComponent(scr);
		}

}
