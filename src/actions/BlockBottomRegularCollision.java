package actions;

import class_annotations.BottomAction;
import components.entityComponents.ComponentType;
import components.entityComponents.ImagePropertiesComponent;
import components.entityComponents.LabelComponent;
import components.entityComponents.LocationComponent;
import components.entityComponents.VelocityComponent;
import entity.IEntity;
import entity.IEntityManager;
import gamedata.IRestrictedGameData;

@BottomAction()
public class BlockBottomRegularCollision extends AbstractAction implements IAction {
	
	@Override
	public IRestrictedGameData executeAction(IEntity other, IEntity self, IEntityManager myEM, IRestrictedGameData currentGameData) {
		LabelComponent lc = (LabelComponent) other.getComponent(ComponentType.Label);
		LocationComponent lcE0 = (LocationComponent) other.getComponent(ComponentType.Location);
		ImagePropertiesComponent ipE0 = (ImagePropertiesComponent) other.getComponent(ComponentType.ImageProperties);
		LocationComponent lcE1 = (LocationComponent) self.getComponent(ComponentType.Location);
		
		
			VelocityComponent vc = (VelocityComponent) other.getComponent(ComponentType.Velocity);
//			AccelerationComponent ac = (AccelerationComponent) other.getComponent(ComponentType.Acceleration);
			if (vc.getY() < 0) {
				vc.setY(vc.getY()*-1);
				
//				ac.setY(0);
			}
			
		return getGameDataFactory().blankEntityData(currentGameData);
	}
}
