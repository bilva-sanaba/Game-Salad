package components.keyExpressions;

import java.util.ArrayList;
import java.util.List;

import actions.IAction;
import class_annotations.KeyAction;
import components.entityComponents.AccelerationComponent;
import components.entityComponents.ComponentType;
import components.entityComponents.VelocityComponent;
import entity.IEntity;
import entity.IEntityManager;
import entity.restricted.IRestrictedEntity;
import gamedata.GameDataFactory;
import gamedata.IRestrictedGameData;

@KeyAction()
/**
 * Action applicable for a key input as well as other engines
 * (Should be refactored to use input parameters but this could not be used by authoring)
 * @author Bilva
 *
 */

public class InfiniteJump implements IAction {

	@Override
	public IRestrictedGameData executeAction(IEntity player, IEntity npc, IEntityManager myEM, IRestrictedGameData currentGameData) {
		VelocityComponent vc = (VelocityComponent) player.getComponent(ComponentType.Velocity);
		AccelerationComponent ac = (AccelerationComponent) player.getComponent(ComponentType.Acceleration);
			vc.setY(-20);
			ac.setY(0.9);
		
		GameDataFactory gdf = new GameDataFactory();
		return gdf.blankEntityData(currentGameData);
	}

}
