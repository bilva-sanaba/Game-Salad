package actions;

import class_annotations.BottomAction;
import class_annotations.TopAction;
import components.entityComponents.ComponentType;
import components.entityComponents.VelocityComponent;
import entity.IEntity;
import entity.IEntityManager;
import gamedata.IRestrictedGameData;

@TopAction()

public class ReverseYVelocityTop extends AbstractAction implements IAction{

	@Override
	public IRestrictedGameData executeAction(IEntity other, IEntity self, IEntityManager myEM,
			IRestrictedGameData currentGameData) {
		VelocityComponent vc = (VelocityComponent) other.getComponent(ComponentType.Velocity);
		
		if (vc.getY()>0 && vc.getY()<0.25) {
			vc.setY(0);
			
		} else {
			if (vc.getY()>0) {
				vc.setY(vc.getY()*-1);
			} 
		}
		
		return getGameDataFactory().blankEntityData(currentGameData);
	}
}

	

