package actions;

import components.entityComponents.ComponentType;
import components.entityComponents.VelocityComponent;
import entity.IEntity;
import entity.IEntityManager;
import gamedata.IRestrictedGameData;

public class BounceOffBlockBottomOrTop extends AbstractAction implements IAction{

	public BounceOffBlockBottomOrTop() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public IRestrictedGameData executeAction(IEntity other,IEntity self, IEntityManager myEM, IRestrictedGameData currentGameData) {
		VelocityComponent vc = (VelocityComponent) other.getComponent(ComponentType.Velocity);
		vc.setY(0);
		//Does anything need to be done about acceleration?
		
		return getGameDataFactory().blankEntityData(currentGameData);

	}

}
