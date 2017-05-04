package actions;

import components.entityComponents.ComponentType;
import components.entityComponents.InvincibilityComponent;
import entity.IEntity;
import entity.IEntityManager;
import gamedata.IGameData;
import gamedata.IRestrictedGameData;

public class DeathAction extends AbstractAction implements IAction{

	@Override
	public IRestrictedGameData executeAction(IEntity other, IEntity self, IEntityManager myEM,
			IRestrictedGameData currentGameData) {
		IGameData gd = getGameDataFactory().blankEntityData(currentGameData);
		if (!other.hasComponent(ComponentType.Invincible)){
			other.addComponent(new InvincibilityComponent(false));
		}
		gd.setLives(gd.getLives().doubleValue()-1);
	
		return gd;
	}

}
