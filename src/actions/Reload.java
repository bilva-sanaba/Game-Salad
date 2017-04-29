package actions;

import components.entityComponents.ComponentType;
import components.entityComponents.ObjectCreationComponent;
import entity.IEntity;
import entity.IEntityManager;
import gamedata.IRestrictedGameData;

public class Reload  extends AbstractAction  implements IAction {
	@Override
	public IRestrictedGameData executeAction(IEntity other, IEntity self, IEntityManager myEM,
			IRestrictedGameData currentGameData) {
			ObjectCreationComponent occ = (ObjectCreationComponent) other.getComponent(ComponentType.ObjectCreation);
			if (occ!=null){
				occ.setCreating(true);
			}
			return getGameDataFactory().blankEntityData(currentGameData);
	}
}
