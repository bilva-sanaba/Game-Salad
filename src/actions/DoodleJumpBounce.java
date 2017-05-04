package actions;

import class_annotations.TopAction;
import components.entityComponents.ComponentType;
import components.entityComponents.ImagePropertiesComponent;
import components.entityComponents.LocationComponent;
import components.entityComponents.VelocityComponent;
import entity.IEntity;
import entity.IEntityManager;
import gamedata.IRestrictedGameData;

//@TopAction()
public class DoodleJumpBounce extends AbstractAction implements IAction {

	

	@Override
	public IRestrictedGameData executeAction(IEntity other, IEntity self, IEntityManager myEM,
			IRestrictedGameData currentGameData) {
		VelocityComponent vc = (VelocityComponent) other.getComponent(ComponentType.Velocity);
		if (vc != null) {
			double yVelo = vc.getY();
			if (yVelo > 0) {
				vc.setY(0);
				LocationComponent lcE0 = (LocationComponent) other.getComponent(ComponentType.Location);
				ImagePropertiesComponent ipE0 = (ImagePropertiesComponent) other.getComponent(ComponentType.ImageProperties);
				LocationComponent lcE1 = (LocationComponent) self.getComponent(ComponentType.Location);
				lcE0.setY(lcE1.getY()-ipE0.getHeight());
			}
			
		}
		return getGameDataFactory().blankEntityData(currentGameData);
 
	}

}
