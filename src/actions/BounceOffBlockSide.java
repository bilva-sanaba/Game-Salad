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
	public IRestrictedGameData executeAction(IEntity e,IEntity e2, IEntityManager myEM, IRestrictedGameData currentGameData) {
		AccelerationComponent ac = (AccelerationComponent) e.getComponent(ComponentType.Acceleration);
//		VelocityComponent vc = (VelocityComponent) e.getComponent(ComponentType.Velocity);
//		LocationComponent lc = (LocationComponent) e.getComponent(ComponentType.Location);
		ac.setX(-1*ac.getX()); //UNSURE IF ACCELERATION SHOULD JUST REVERSED
		ac.setY(1*ac.getY());
		return getGameDataFactory().blankEntityData(currentGameData);

	}

}
