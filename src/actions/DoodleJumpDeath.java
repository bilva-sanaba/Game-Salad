package actions;

import class_annotations.TopAction;
import components.entityComponents.ComponentType;
import components.entityComponents.ImagePropertiesComponent;
import components.entityComponents.LocationComponent;
import components.entityComponents.VelocityComponent;
import entity.IEntity;
import entity.IEntityManager;
import gamedata.IRestrictedGameData;

@TopAction()

public class DoodleJumpDeath extends AbstractAction implements IAction{

	@Override
	public IRestrictedGameData executeAction(IEntity other, IEntity self, IEntityManager myEM,
			IRestrictedGameData currentGameData) {
		VelocityComponent vc = (VelocityComponent) other.getComponent(ComponentType.Velocity);
		IRestrictedGameData ret = currentGameData;
		if (vc != null) {
			double yVelo = vc.getY();
			if (yVelo > 0) {
				ret = new DeathAction().executeAction(other, self, myEM, currentGameData);
			}
			
		}
		return getGameDataFactory().blankEntityData(ret);
	}

	

}
