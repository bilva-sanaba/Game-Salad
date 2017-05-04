package actions;

import class_annotations.BottomAction;
import class_annotations.LeftAction;
import class_annotations.RightAction;
import class_annotations.TopAction;
import components.entityComponents.ComponentType;
import components.entityComponents.ControllableComponent;
import entity.IEntity;
import entity.IEntityManager;
import gamedata.GameDataFactory;
import gamedata.IRestrictedGameData;

@TopAction()
@LeftAction()
@RightAction()
@BottomAction()
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
