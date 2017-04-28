package actions;

import components.entityComponents.ComponentType;
import components.entityComponents.ObjectCreationComponent;
import entity.IEntity;
import entity.IEntityManager;
import gamedata.IRestrictedGameData;

public class Reload  extends AbstractAction  implements IAction {
	@Override
	public IRestrictedGameData executeAction(IEntity player, IEntity npc, IEntityManager myEM,
			IRestrictedGameData currentGameData) {
			ObjectCreationComponent occ = (ObjectCreationComponent) player.getComponent(ComponentType.ObjectCreation);
			if (occ!=null){
				occ.setCreating(true);
			}
			return getGameDataFactory().blankEntityData(currentGameData);
	}
}
