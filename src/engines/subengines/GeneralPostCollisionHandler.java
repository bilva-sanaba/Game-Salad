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
		CollisionComponentsHandler handlerE1 = (CollisionComponentsHandler) e1.getComponent(ComponentType.CollisionHandler);
		CollisionComponentsHandler handlerE0 = (CollisionComponentsHandler) e0.getComponent(ComponentType.CollisionHandler);
		if (handlerE1 != null && handlerE1.getCollisionComponent(side) != null) {
				createdEntities.addAll(handlerE1.getCollisionComponent(side).executeOnCollide(e0, e1,myEM));
		}
		if (handlerE0 != null && handlerE0.getCollisionComponent(side) != null) {
			createdEntities.addAll(handlerE0.getCollisionComponent(side).executeOnCollide(e1, e0, myEM));
	}
		
			
			
		
		
		return createdEntities;

	}

	

}