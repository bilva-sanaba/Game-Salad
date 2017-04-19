package engines.subengines;

import java.util.ArrayList;
import java.util.List;

import components.collisionComponents.CollisionComponentsHandler;
import components.collisionComponents.SideCollisionComponent;
import components.entityComponents.CollidableComponent;
import components.entityComponents.ComponentType;
import components.entityComponents.LabelComponent;
import components.entityComponents.TypeComponent;
import entity.Entity;
import entity.IEntity;

public class GeneralPostCollisionHandler implements ISubEngine{

	public GeneralPostCollisionHandler() {
		// TODO Auto-generated constructor stub
	}

	

	@Override
	public List<IEntity> handleCollision(IEntity e0, IEntity e1, String side) {
		List<IEntity> createdEntities = new ArrayList<IEntity>();
		CollidableComponent collE0 = (CollidableComponent) e0.getComponent(ComponentType.Collidable);
		CollidableComponent collE1 = (CollidableComponent) e1.getComponent(ComponentType.Collidable);
		if (collE0 != null && collE1 != null && collE0.getCollide()==true && collE1.getCollide()==true) {
			CollisionComponentsHandler handlerE1 = (CollisionComponentsHandler) e1.getComponent(ComponentType.CollisionHandler);
//			System.out.println(handlerE1);
//			System.out.println(side);
//			System.out.println(handlerE1.getCollisionComponent(side));
//			System.out.println(handlerE1.getCollisionComponent(side).executeOnCollide(e0));
			if (handlerE1.getCollisionComponent(side) != null) {
					createdEntities.addAll(handlerE1.getCollisionComponent(side).executeOnCollide(e0, e1));
				}
			
		}
		
		return createdEntities;

	}

	

}