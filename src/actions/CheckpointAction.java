package actions;

import class_annotations.*;
import components.entityComponents.CheckpointComponent;
import components.entityComponents.ComponentType;
import entity.IEntity;
import entity.IEntityManager;
import gameEngine_interface.EntityLoader;
import gamedata.IRestrictedGameData;

public class CheckpointAction extends AbstractAction implements IAction {

	@Override
	public IRestrictedGameData executeAction(IEntity other, IEntity self, IEntityManager myEM,
			IRestrictedGameData currentGameData) {
		CheckpointComponent cc = (CheckpointComponent) other.getComponent(ComponentType.Checkpoint);
		EntityLoader el = new EntityLoader(myEM);
		el.loadNew(cc.getState());
		return getGameDataFactory().blankEntityData(cc.getData());
	}

}
