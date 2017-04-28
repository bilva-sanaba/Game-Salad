package actions;

import java.util.ArrayList;
import java.util.Collection;

import components.entityComponents.ComponentType;
import components.entityComponents.LocationComponent;
import components.entityComponents.ObjectCreationComponent;
import entity.Entity;
import entity.EntityManager;
import entity.IEntity;
import entity.IEntityManager;
import entity.restricted.IRestrictedEntityManager;
import gamedata.GameData;
import gamedata.IRestrictedGameData;

public class ShootAction  extends AbstractAction implements IAction {

	@Override
	public IRestrictedGameData executeAction(IEntity other, IEntity self, IEntityManager myEM, IRestrictedGameData currentGameData) {
		ObjectCreationComponent occ = (ObjectCreationComponent) other.getComponent(ComponentType.ObjectCreation);
		self = occ.getCreationEffect(); 
		GameData returnData = getGameDataFactory().blankEntityData(currentGameData);
		if (self!=null){
			LocationComponent lcplayer= (LocationComponent) other.getComponent(ComponentType.Location);
			LocationComponent lcnpc= (LocationComponent) self.getComponent(ComponentType.Location);
			lcnpc.setX(lcplayer.getX()+60);
			lcnpc.setY(lcplayer.getY());
			occ.setEntity(self.newCopy());
			Collection<Entity> list = new ArrayList<Entity>();
			list.add((Entity) self);
			EntityManager em = new EntityManager(list);
			returnData.setRestrictedEntityManager((IRestrictedEntityManager) em);
		}		
		return returnData;
	}

}
