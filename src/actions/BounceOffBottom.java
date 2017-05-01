package actions;

import class_annotations.BottomAction;
import components.entityComponents.ComponentType;
import components.entityComponents.VelocityComponent;
import entity.IEntity;
import entity.IEntityManager;
import gamedata.IRestrictedGameData;

@BottomAction()

public class BounceOffBottom extends AbstractAction  implements IAction {
	
	public static final double VELOCITY_REVERSE = -1;
	public static final double BOUNCE_FACTOR = 0.5;
	
	@Override
	public IRestrictedGameData executeAction(IEntity other, IEntity self, IEntityManager myEM, IRestrictedGameData currentGameData) {
		VelocityComponent vc = (VelocityComponent) other.getComponent(ComponentType.Velocity);
		if (vc.getY()<0) {
			vc.setY(vc.getY()*VELOCITY_REVERSE*BOUNCE_FACTOR);
		}

		return getGameDataFactory().blankEntityData(currentGameData);
	}
}
