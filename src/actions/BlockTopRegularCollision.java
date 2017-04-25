package actions;

import class_annotations.TopAction;
import components.entityComponents.ComponentType;
import components.entityComponents.ImagePropertiesComponent;
import components.entityComponents.LabelComponent;
import components.entityComponents.LocationComponent;
import components.entityComponents.VelocityComponent;
import entity.IEntity;
import entity.IEntityManager;
import gamedata.IRestrictedGameData;


@TopAction()
public class BlockTopRegularCollision extends AbstractAction implements IAction {
	
	@Override
	public IRestrictedGameData executeAction(IEntity e, IEntity e1, IEntityManager myEM, IRestrictedGameData currentGameData) {
		LabelComponent lc = (LabelComponent) e.getComponent(ComponentType.Label);
		LocationComponent lcE0 = (LocationComponent) e.getComponent(ComponentType.Location);
		ImagePropertiesComponent ipE0 = (ImagePropertiesComponent) e.getComponent(ComponentType.ImageProperties);
		LocationComponent lcE1 = (LocationComponent) e1.getComponent(ComponentType.Location);
		lcE0.setY(lcE1.getY()-ipE0.getHeight());
		if (!lc.getLabel().equals("Block")) {
			VelocityComponent vc = (VelocityComponent) e.getComponent(ComponentType.Velocity);
//			AccelerationComponent ac = (AccelerationComponent) e.getComponent(ComponentType.Acceleration);
			if (vc.getY() > 0) {
				vc.setY(0);
//				ac.setY(0);
			}
			
		}
		return getGameDataFactory().blankEntityData(currentGameData);

	}
}