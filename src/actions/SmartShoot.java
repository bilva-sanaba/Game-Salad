package actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import class_annotations.BottomAction;
import class_annotations.LeftAction;
import class_annotations.RightAction;
import class_annotations.TopAction;
import components.entityComponents.ComponentType;
import components.entityComponents.LocationComponent;
import components.entityComponents.ObjectCreationComponent;
import components.entityComponents.VelocityComponent;
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
public class SmartShoot extends AbstractAction implements IAction  {
	private List<LocationComponent> targets;
	public SmartShoot(){
	}
	
	@Override
	public IRestrictedGameData executeAction(IEntity other, IEntity self, IEntityManager myEM,
			IRestrictedGameData currentGameData) {
		if (targets==null){
			targets= new ArrayList<LocationComponent>();
			for (IEntity e : myEM.getEntities()){
				if (e.hasComponent(ComponentType.KeyInput)){
					targets.add((LocationComponent) e.getComponent(ComponentType.Location));
				}
			}
		}
		LocationComponent shooterLocation = (LocationComponent) other.getComponent(ComponentType.Location);
		ObjectCreationComponent occ = (ObjectCreationComponent) other.getComponent(ComponentType.ObjectCreation);
		IEntity newE = occ.getCreationEffect(); 
		GameData returnData = getGameDataFactory().blankEntityData(currentGameData);
		if (newE!=null){
			LocationComponent target = nearestTarget(shooterLocation,targets);
			LocationComponent lcnpc= (LocationComponent) newE.getComponent(ComponentType.Location);
			VelocityComponent vcnpc= (VelocityComponent) newE.getComponent(ComponentType.Velocity);
			
			lcnpc.setX(shooterLocation.getX());
			lcnpc.setY(shooterLocation.getY());
			
			occ.setEntity(newE.newCopy(myEM.getEntities().size()));
			occ.setCreating(true);
			directShot(shooterLocation,target,vcnpc);
	
			Collection<IEntity> list = new ArrayList<IEntity>();
			list.add(newE);
			EntityManager em = new EntityManager(list);
			myEM.getEntities().add(newE);
			myEM.changed(newE);
			returnData.setRestrictedEntityManager((IRestrictedEntityManager) em);
		}		
		return returnData;
	}

	private LocationComponent nearestTarget(LocationComponent shooter, List<LocationComponent> targets){
		double minDistance = 1000000000;
		LocationComponent closest = targets.get(0);
		for (LocationComponent lc : targets){
			double dis = (shooter.getX()-lc.getX())*((shooter.getX()-lc.getX()))+(shooter.getY()-lc.getY())*((shooter.getY()-lc.getY()));
			if (dis<minDistance){
				minDistance = dis;
				closest = lc;
			}
		}
		return closest;
	}
	private void directShot(LocationComponent shooter, LocationComponent target, VelocityComponent shot){
		double velY = shooter.getY()-target.getY();
		double velX = shooter.getX()-target.getX();
		shot.setY(-velY/100);
		shot.setX(-velX/100);
	}

}
