package actions;

import java.util.ArrayList;
import java.util.Collection;

import class_annotations.BottomAction;
import class_annotations.LeftAction;
import class_annotations.RightAction;
import class_annotations.TopAction;
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

@TopAction()
@LeftAction()
@RightAction()
@BottomAction()
public class PowerupCreation   extends AbstractAction implements IAction {
	@Override
	public IRestrictedGameData executeAction(IEntity other, IEntity self, IEntityManager myEM, IRestrictedGameData currentGameData) {
		ObjectCreationComponent occ = ((ObjectCreationComponent) self.getComponent(ComponentType.ObjectCreation));
		LocationComponent lc = ((LocationComponent) self.getComponent(ComponentType.Location));
		GameData returnData = getGameDataFactory().blankEntityData(currentGameData);
		if (occ.checkIfCreation()){
			IEntity powerup = occ.getCreationEffect();
			if (powerup!=null){
				Collection<IEntity> list = new ArrayList<IEntity>();
				list.add( powerup);
				powerup.setID(myEM.getEntities().size());
				LocationComponent newLC = ((LocationComponent) powerup.getComponent(ComponentType.Location));
				ImagePropertiesComponent ipc = ((ImagePropertiesComponent) powerup.getComponent(ComponentType.ImageProperties));
				newLC.setX(lc.getX());
				newLC.setY(lc.getY()-ipc.getHeight());
				myEM.getEntities().add(powerup);
				myEM.changed(powerup);
				EntityManager em = new EntityManager(list);
				returnData.setRestrictedEntityManager((IRestrictedEntityManager) em);
			}
		}
		return returnData; 
	}
}
