package actions;

import class_annotations.RightAction;
import components.entityComponents.AccelerationComponent;
import components.entityComponents.ComponentType;
import components.entityComponents.ImagePropertiesComponent;
import components.entityComponents.LocationComponent;
import components.entityComponents.VelocityComponent;
import entity.IEntity;
import entity.IEntityManager;
import gamedata.IRestrictedGameData;

@RightAction()
public class BounceOffRight extends AbstractAction  implements IAction {

	public static final double VELOCITY_REVERSE = -1;
	public static final double BOUNCE_FACTOR = 0.5;

	@Override

	public IRestrictedGameData executeAction(IEntity other,IEntity self, IEntityManager myEM, IRestrictedGameData currentGameData) {

		AccelerationComponent ac = (AccelerationComponent) other.getComponent(ComponentType.Acceleration);
		VelocityComponent vc = (VelocityComponent) other.getComponent(ComponentType.Velocity);
		LocationComponent lcE0 = (LocationComponent) other.getComponent(ComponentType.Location);
		LocationComponent lcE1 = (LocationComponent) self.getComponent(ComponentType.Location);
		ImagePropertiesComponent ipE0 = (ImagePropertiesComponent) self.getComponent(ComponentType.ImageProperties);
		lcE0.setX(lcE1.getX()+ipE0.getWidth());
		
		if(vc.getX() < 0) {
			vc.setX(vc.getX()*VELOCITY_REVERSE*BOUNCE_FACTOR);
		}
		

		return getGameDataFactory().blankEntityData(currentGameData);

	}
}
