package components.keyExpressions;

import actions.IAction;
import components.entityComponents.AccelerationComponent;
import components.entityComponents.ComponentType;
import components.entityComponents.VelocityComponent;
import entity.IEntity;
import entity.IEntityManager;
import gamedata.GameDataFactory;
import gamedata.IRestrictedGameData;

public class DownAction implements IAction {

	@Override
	public IRestrictedGameData executeAction(IEntity other, IEntity self, IEntityManager myEM,
			IRestrictedGameData currentGameData) {
		VelocityComponent vc = (VelocityComponent) other.getComponent(ComponentType.Velocity);
		AccelerationComponent ac = (AccelerationComponent) other.getComponent(ComponentType.Acceleration);
		if (vc.getY()==0){
			vc.setY(20);
			ac.setY(-0.9);
		}
		
		GameDataFactory gdf = new GameDataFactory();
		return gdf.blankEntityData(currentGameData);
	}

}
