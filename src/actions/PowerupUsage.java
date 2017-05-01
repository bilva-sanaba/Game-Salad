package actions;

import entity.IEntity;
import entity.IEntityManager;
import gamedata.IRestrictedGameData;

public class PowerupUsage extends AbstractAction implements IAction{

	@Override
	public IRestrictedGameData executeAction(IEntity other, IEntity self, IEntityManager myEM, IRestrictedGameData currentGameData) {
		//		SpriteComponent npcsc = (SpriteComponent) self.getComponent(new SpriteComponent());
		myEM.getEntities().remove(self);
		self.changed(null);
		return getGameDataFactory().blankEntityData(currentGameData);
	}

}
