package actions;

import class_annotations.TopAction;
import components.entityComponents.AccelerationComponent;
import components.entityComponents.ComponentType;
import components.entityComponents.VelocityComponent;
import entity.IEntity;
import entity.IEntityManager;
import gamedata.IRestrictedGameData;

@TopAction()
public class DoubleJump  extends AbstractAction implements IAction {

	@Override
	public IRestrictedGameData executeAction(IEntity other, IEntity self, IEntityManager myEM,
			IRestrictedGameData currentGameData) {
		VelocityComponent vc = (VelocityComponent) other.getComponent(ComponentType.Velocity);
		AccelerationComponent ac = (AccelerationComponent) other.getComponent(ComponentType.Acceleration);
		vc.setY(-5);
		ac.setY(.2);
		return getGameDataFactory().blankEntityData(currentGameData);
	}
	

}
