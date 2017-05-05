package components.keyExpressions;


import actions.AbstractAction;
import actions.IAction;
import java.util.ArrayList;
import java.util.List;

import actions.IAction;
import class_annotations.KeyAction;
import components.entityComponents.AccelerationComponent;
import components.entityComponents.ComponentType;
import components.entityComponents.VelocityComponent;
import entity.IEntity;
import entity.IEntityManager;
import gamedata.GameDataFactory;
import gamedata.IRestrictedGameData;
/**
 * Action applicable for a key input
 * (Should be refactored to use input parameters but this could not be used by authoring)
 * @author Bilva
 *
 */
public class DownAction extends AbstractAction implements IAction {

	@Override
	public IRestrictedGameData executeAction(IEntity other, IEntity self, IEntityManager myEM,
			IRestrictedGameData currentGameData) {
		VelocityComponent vc = (VelocityComponent) other.getComponent(ComponentType.Velocity);
		AccelerationComponent ac = (AccelerationComponent) other.getComponent(ComponentType.Acceleration);
		if (vc.getY()==0){
			vc.setY(20);
			ac.setY(-0.9);
		}
		GameDataFactory gdf = getGameDataFactory();
		return gdf.blankEntityData(currentGameData);
	}


}

