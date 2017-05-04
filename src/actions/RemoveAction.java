package actions;

import class_annotations.BottomAction;
import class_annotations.LeftAction;
import class_annotations.RightAction;
import class_annotations.TopAction;
import entity.IEntity;
import entity.IEntityManager;
import gamedata.GameData;
import gamedata.IGameData;
import gamedata.IRestrictedGameData;

@TopAction()
@LeftAction()
@RightAction()
@BottomAction()
public class RemoveAction extends AbstractAction  implements IAction {

	
	@Override
	public IRestrictedGameData executeAction(IEntity other, IEntity self, IEntityManager myEM,
			IRestrictedGameData currentGameData) {
		return new PowerupUsage().executeAction(self,other,myEM,currentGameData);
	}

}
