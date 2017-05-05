package actions;

import java.util.ArrayList;
import java.util.Collection;

import class_annotations.BottomAction;
import class_annotations.LeftAction;
import class_annotations.RightAction;
import class_annotations.TopAction;
import components.entityComponents.ComponentType;
import components.entityComponents.LocationComponent;
import components.entityComponents.ObjectCreationComponent;
import components.entityComponents.OrientationComponent;
import components.entityComponents.VelocityComponent;
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
/**
 * Action which shoots the object stored in an entity in a preset direction
 * @author Bilva
 *
 */
public class ShootAction  extends AbstractAction implements IAction {

	@Override

	public IRestrictedGameData executeAction(IEntity other, IEntity self, IEntityManager myEM, IRestrictedGameData currentGameData) {
		ObjectCreationComponent occ = (ObjectCreationComponent) other.getComponent(ComponentType.ObjectCreation);
		IEntity newE = occ.getCreationEffect(); 
		GameData returnData = getGameDataFactory().blankEntityData(currentGameData);
		if (newE!=null){
			LocationComponent lcplayer= (LocationComponent) other.getComponent(ComponentType.Location);
			LocationComponent lcnpc= (LocationComponent) newE.getComponent(ComponentType.Location);
			VelocityComponent vcNPC = (VelocityComponent) newE.getComponent(ComponentType.Velocity);
			occ.setEntity(newE.newCopy(myEM.getEntities().size()));
			vcNPC.setX(vcNPC.getX());
			lcnpc.setX(lcplayer.getX());
			lcnpc.setY(lcplayer.getY());
			Collection<IEntity> list = new ArrayList<IEntity>();
			list.add( newE);
			EntityManager em = new EntityManager(list);
			myEM.getEntities().add(newE);
			myEM.changed(newE);
			returnData.setRestrictedEntityManager((IRestrictedEntityManager) em);
		}		
		return returnData;
	}

}
