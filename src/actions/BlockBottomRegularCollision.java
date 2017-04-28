package actions;

import class_annotations.BottomAction;
import components.entityComponents.ComponentType;
import components.entityComponents.VelocityComponent;
import entity.IEntity;
import entity.IEntityManager;
import gamedata.IRestrictedGameData;

@BottomAction()
public class BlockBottomRegularCollision extends AbstractAction implements IAction {
	
	@Override
	public IRestrictedGameData executeAction(IEntity e, IEntity e2, IEntityManager myEM, IRestrictedGameData currentGameData) {
		VelocityComponent velo = (VelocityComponent) e.getComponent(ComponentType.Velocity);
		velo.setY(-1*velo.getY());

		return getGameDataFactory().blankEntityData(currentGameData);
	}
}
