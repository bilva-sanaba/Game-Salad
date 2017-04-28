package actions;

import java.util.ArrayList;
import java.util.Collection;

import components.entityComponents.ComponentType;
import components.entityComponents.ImagePropertiesComponent;
import components.entityComponents.LocationComponent;
import components.entityComponents.ObjectCreationComponent;
import entity.Entity;
import entity.EntityManager;
import entity.IEntity;
import entity.IEntityManager;
import entity.restricted.IRestrictedEntityManager;
import gamedata.GameData;
import gamedata.IRestrictedGameData;

public class PowerupCreation   extends AbstractAction implements IAction {
	@Override
	public IRestrictedGameData executeAction(IEntity other, IEntity self, IEntityManager myEM, IRestrictedGameData currentGameData) {
		ObjectCreationComponent occ = ((ObjectCreationComponent) self.getComponent(ComponentType.ObjectCreation));
		LocationComponent lc = ((LocationComponent) self.getComponent(ComponentType.Location));
		GameData returnData = getGameDataFactory().blankEntityData(currentGameData);
		if (occ.checkIfCreation()){
			IEntity powerup = occ.getCreationEffect();
			if (powerup!=null){
				Collection<Entity> list = new ArrayList<Entity>();
				list.add((Entity) powerup);
				LocationComponent newLC = ((LocationComponent) powerup.getComponent(ComponentType.Location));
				ImagePropertiesComponent ipc = ((ImagePropertiesComponent) powerup.getComponent(ComponentType.ImageProperties));
				newLC.setX(lc.getX());
				newLC.setY(lc.getY()-ipc.getHeight());
				EntityManager em = new EntityManager(list);
				returnData.setRestrictedEntityManager((IRestrictedEntityManager) em);
			}
		}
		return returnData; 
	}
}
