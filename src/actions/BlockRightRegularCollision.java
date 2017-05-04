package actions;

import class_annotations.RightAction;
import components.entityComponents.ComponentType;
import components.entityComponents.ImagePropertiesComponent;
import components.entityComponents.LocationComponent;
import components.entityComponents.OrientationComponent;
import components.entityComponents.VelocityComponent;
import entity.IEntity;
import entity.IEntityManager;
import gamedata.IRestrictedGameData;

@RightAction()
public class BlockRightRegularCollision extends AbstractAction implements IAction {

	

	@Override
	public IRestrictedGameData executeAction(IEntity other, IEntity self, IEntityManager myEM,
			IRestrictedGameData currentGameData) {
		LocationComponent otherLocation = (LocationComponent) other.getComponent(ComponentType.Location);
		LocationComponent selfLocation = (LocationComponent) self.getComponent(ComponentType.Location);
		ImagePropertiesComponent selfImageProp = (ImagePropertiesComponent) self.getComponent(ComponentType.ImageProperties);
		otherLocation.setX(selfLocation.getX() + selfImageProp.getWidth());
		//OrientationComponent otherOrientation = (OrientationComponent) other.getComponent(ComponentType.Orientation);
		VelocityComponent vc = (VelocityComponent) other.getComponent(ComponentType.Velocity);
		if (vc!=null && vc.getX() <0) {
			vc.setX(0);
		}
		//otherOrientation.setOrientation(OrientationComponent.RIGHT);
		return getGameDataFactory().blankEntityData(currentGameData);

	}

}
