package actions;

import class_annotations.BottomAction;
import class_annotations.LeftAction;
import class_annotations.RightAction;
import class_annotations.TopAction;
import entity.IEntity;
import entity.IEntityManager;
import gamedata.IRestrictedGameData;

@TopAction()
@LeftAction()
@BottomAction()
@RightAction()
public class Explosion  extends AbstractAction  implements IAction {
	
	public static final String EXPLOSION_IMAGE = "Feuer46.GIF";

	@Override
	public IRestrictedGameData executeAction(IEntity other, IEntity self, IEntityManager myEM, IRestrictedGameData currentGameData) {
//		ImagePropertiesComponent ic = (ImagePropertiesComponent) other.getComponent(ComponentType.ImageProperties);


		return getGameDataFactory().blankEntityData(currentGameData);
	}

}