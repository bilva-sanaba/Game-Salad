package actions;

import components.entityComponents.SpriteComponent;
import entity.IEntity;
import entity.IEntityManager;
import gamedata.IRestrictedGameData;

public class PowerupUsage  extends AbstractAction implements IAction{

	@Override
	public IRestrictedGameData executeAction(IEntity player, IEntity npc, IEntityManager myEM, IRestrictedGameData currentGameData) {
		//		SpriteComponent npcsc = (SpriteComponent) npc.getComponent(new SpriteComponent());
		myEM.getEntities().remove(npc);
		((SpriteComponent) npc.getComponent(new SpriteComponent())).setClassPath("");
		npc.changed(null);
		return getGameDataFactory().blankEntityData(currentGameData);
	}

}
