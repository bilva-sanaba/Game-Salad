package actions;

import class_annotations.LeftAction;
import components.entityComponents.ComponentType;
import components.entityComponents.ImagePropertiesComponent;
import components.entityComponents.LocationComponent;
import components.entityComponents.OrientationComponent;
import entity.IEntity;
import entity.IEntityManager;
import gamedata.IRestrictedGameData;

@LeftAction()
public class BlockLeftRegularCollision extends AbstractAction implements IAction {

	public BlockLeftRegularCollision() {
	}

	@Override
	public IRestrictedGameData executeAction(IEntity other, IEntity self, IEntityManager myEM,
			IRestrictedGameData currentGameData) {
		LocationComponent otherLocation = (LocationComponent) other.getComponent(ComponentType.Location);
		LocationComponent selfLocation = (LocationComponent) self.getComponent(ComponentType.Location);
		ImagePropertiesComponent otherImageProp = (ImagePropertiesComponent) other.getComponent(ComponentType.ImageProperties);
		otherLocation.setX(selfLocation.getX() - otherImageProp.getWidth());
		if (!other.hasComponent(ComponentType.Orientation)){
			other.addComponent(new OrientationComponent());
		}
		OrientationComponent otherOrientation = (OrientationComponent) other.getComponent(ComponentType.Orientation);
		otherOrientation.setOrientation(OrientationComponent.LEFT);
		return getGameDataFactory().blankEntityData(currentGameData);

	}

}
