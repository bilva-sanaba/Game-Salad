package actions;

import components.entityComponents.SpriteComponent;
import entity.IEntity;
import entity.IEntityManager;
import gamedata.IRestrictedGameData;

public class PowerupUsage extends AbstractAction implements IAction{

	@Override
	public IRestrictedGameData executeAction(IEntity other, IEntity self, IEntityManager myEM, IRestrictedGameData currentGameData) {
		//		SpriteComponent npcsc = (SpriteComponent) self.getComponent(new SpriteComponent());
		myEM.getEntities().remove(self);
		((SpriteComponent) self.getComponent(new SpriteComponent())).setClassPath("");
		self.changed(null);
		return getGameDataFactory().blankEntityData(currentGameData);
	}

}
