package actions;

import components.entityComponents.AccelerationComponent;
import components.entityComponents.ComponentType;
import entity.IEntity;
import entity.IEntityManager;
import gamedata.IRestrictedGameData;

public class BounceOffBlockSide extends AbstractAction  implements IAction {

	public BounceOffBlockSide() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public IRestrictedGameData executeAction(IEntity other,IEntity self, IEntityManager myEM, IRestrictedGameData currentGameData) {
		AccelerationComponent ac = (AccelerationComponent) other.getComponent(ComponentType.Acceleration);
//		VelocityComponent vc = (VelocityComponent) other.getComponent(ComponentType.Velocity);
//		LocationComponent lc = (LocationComponent) other.getComponent(ComponentType.Location);
		ac.setX(-1*ac.getX()); //UNSURE IF ACCELERATION SHOULD JUST REVERSED
		ac.setY(1*ac.getY());
		return getGameDataFactory().blankEntityData(currentGameData);

	}

}
