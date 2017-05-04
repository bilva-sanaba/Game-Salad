package actions;

import components.entityComponents.ComponentType;
import components.entityComponents.ControllableComponent;
import entity.IEntity;
import entity.IEntityManager;
import gamedata.GameDataFactory;
import gamedata.IRestrictedGameData;

public class RestoreControl extends AbstractAction implements IAction{

	@Override
	public IRestrictedGameData executeAction(IEntity other, IEntity self, IEntityManager myEM,
			IRestrictedGameData currentGameData) {
		ControllableComponent cc = (ControllableComponent) other.getComponent(ComponentType.Controllable);
		cc.regainControl();
		GameDataFactory gdf = new GameDataFactory();
		return gdf.blankEntityData(currentGameData);
	}

}
