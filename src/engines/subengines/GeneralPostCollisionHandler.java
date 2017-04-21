package engines.subengines;

import java.util.ArrayList;
import java.util.List;

import components.entityComponents.CollidableComponent;
import components.entityComponents.CollisionComponentsHandler;
import components.entityComponents.ComponentType;
import components.entityComponents.LabelComponent;
import components.entityComponents.SideCollisionComponent;
import components.entityComponents.TypeComponent;
import entity.Entity;
import entity.IEntity;
import entity.IEntityManager;

public class GeneralPostCollisionHandler implements ISubEngine{

	public GeneralPostCollisionHandler() {
		// TODO Auto-generated constructor stub
	}

	

	@Override
	public List<IEntity> handleCollision(IEntity e0, IEntity e1, String side, IEntityManager myEM) {
		List<IEntity> createdEntities = new ArrayList<IEntity>();
		CollidableComponent collE0 = (CollidableComponent) e0.getComponent(ComponentType.Collidable);
		CollidableComponent collE1 = (CollidableComponent) e1.getComponent(ComponentType.Collidable);
		if (collE0 != null && collE1 != null && collE0.getCollide()==true && collE1.getCollide()==true) {
			CollisionComponentsHandler handlerE1 = (CollisionComponentsHandler) e1.getComponent(ComponentType.CollisionHandler);
			if (handlerE1.getCollisionComponent(side) != null) {
					createdEntities.addAll(handlerE1.getCollisionComponent(side).executeOnCollide(e0, e1,myEM));
				}
			
		}
		
		return createdEntities;

	}

	

}