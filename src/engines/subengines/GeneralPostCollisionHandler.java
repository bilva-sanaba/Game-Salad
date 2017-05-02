package engines.subengines;

import java.util.ArrayList;
import java.util.Collection;
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
import gamedata.IRestrictedGameData;

public class GeneralPostCollisionHandler implements ISubEngine{

	public GeneralPostCollisionHandler() {
		// TODO Auto-generated constructor stub
	}

	

	@Override
	public IRestrictedGameData handleCollision(IEntity e0, IEntity e1, String side, IEntityManager myEM, IRestrictedGameData gd) {
		List<IEntity> createdEntities = new ArrayList<IEntity>();
		Collection<IEntity> col = new ArrayList<IEntity>();
		col.add(e0);col.add(e1);
		if (myEM.getEntities().containsAll(col)){
		CollisionComponentsHandler handlerE1 = (CollisionComponentsHandler) e1.getComponent(ComponentType.CollisionHandler);
		CollisionComponentsHandler handlerE0 = (CollisionComponentsHandler) e0.getComponent(ComponentType.CollisionHandler);
//		if (e0.getID()==0){
//			
//			System.out.println("Main Player collided");
//			System.out.println(e1.getID());
//		}
		if (handlerE1 != null && handlerE1.getCollisionComponent(side) != null) {
			gd = handlerE1.getCollisionComponent(side).executeOnCollide(e0, e1,myEM,gd);
		}
		if (handlerE0 != null && handlerE0.getCollisionComponent(side) != null) {
			gd = handlerE0.getCollisionComponent(side).executeOnCollide(e1, e0, myEM,gd);
		}
	}
		
			
			
		
		
		return gd;

	}

	

}