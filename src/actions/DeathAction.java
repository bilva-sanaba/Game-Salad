package actions;

import class_annotations.BottomAction;
import class_annotations.LeftAction;
import class_annotations.RightAction;
import class_annotations.TopAction;
import components.entityComponents.ComponentType;
import components.entityComponents.InvincibilityComponent;
import entity.IEntity;
import entity.IEntityManager;
import gamedata.IGameData;
import gamedata.IRestrictedGameData;

//@TopAction()
//@LeftAction()
//@RightAction()
//@BottomAction()
/**
 * Action which when run, lowers the lives of the game
 * @author Bilva
 *
 */

public class DeathAction extends AbstractAction implements IAction{

	@Override
	public IRestrictedGameData executeAction(IEntity other, IEntity self, IEntityManager myEM,
			IRestrictedGameData currentGameData) {
		IGameData gd = getGameDataFactory().blankEntityData(currentGameData);
//		if (!other.hasComponent(ComponentType.Invincible)){
//			other.addComponent(new InvincibilityComponent(false));
//		}
		gd.setLives(gd.getLives().doubleValue()-1);
	
		return gd;
	}

}
