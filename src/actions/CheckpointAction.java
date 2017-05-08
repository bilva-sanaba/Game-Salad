package actions;

import class_annotations.*;
import components.entityComponents.CheckpointComponent;
import components.entityComponents.ComponentType;
import entity.IEntity;
import entity.IEntityManager;
import gameEngine_interface.EntityLoader;
import gameEngine_interface.IEntityLoader;
import gamedata.IRestrictedGameData;
/**
 * Action which when run reloads the game state to be that of the last point in the player's checkpoint component
 * @author Bilva
 *
 */

public class CheckpointAction extends AbstractAction implements IAction {

	@Override
	public IRestrictedGameData executeAction(IEntity other, IEntity self, IEntityManager myEM,
			IRestrictedGameData currentGameData) {
		CheckpointComponent cc = (CheckpointComponent) other.getComponent(ComponentType.Checkpoint);
		IEntityLoader el = new EntityLoader(myEM);
		el.loadNew(cc.getState());
		return getGameDataFactory().blankEntityData(cc.getData());
	}

}
