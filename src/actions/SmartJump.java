package actions;

import class_annotations.BottomAction;
import class_annotations.LeftAction;
import class_annotations.RightAction;
import class_annotations.TopAction;
import components.entityComponents.ComponentType;
import components.entityComponents.EntityType;
import components.entityComponents.ImagePropertiesComponent;
import components.entityComponents.LocationComponent;
import components.entityComponents.TypeComponent;
import components.keyExpressions.JumpAction;
import entity.IEntity;
import entity.IEntityManager;
import gamedata.IRestrictedGameData;

@TopAction()
@LeftAction()
@RightAction()
@BottomAction()
public class SmartJump extends AbstractAction implements IAction {

	@Override
	public IRestrictedGameData executeAction(IEntity other, IEntity self, IEntityManager myEM,
			IRestrictedGameData currentGameData) {
		for (IEntity e : myEM.getEntities()){
			if (e.hasComponent(ComponentType.Type)){
				if (((TypeComponent) e.getComponent(ComponentType.Type)).getType().equals(EntityType.Projectile)){
					LocationComponent proj = (LocationComponent) e.getComponent(ComponentType.Location);
					LocationComponent lc = (LocationComponent) other.getComponent(ComponentType.Location);
					ImagePropertiesComponent ic = (ImagePropertiesComponent) other.getComponent(ComponentType.ImageProperties);
					
						return new JumpAction().executeAction(other, self, myEM, currentGameData);
					

				}
			}
		}
		return getGameDataFactory().blankEntityData(currentGameData);
	}

}
