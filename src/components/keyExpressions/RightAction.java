package components.keyExpressions;

import java.util.ArrayList;
import java.util.List;

import actions.IAction;
import class_annotations.KeyAction;
import components.entityComponents.AccelerationComponent;
import components.entityComponents.ComponentType;
import components.entityComponents.LocationComponent;
import components.entityComponents.VelocityComponent;
import entity.IEntity;
import entity.IEntityManager;
import entity.restricted.IRestrictedEntity;
import gamedata.GameDataFactory;
import gamedata.IRestrictedGameData;
@KeyAction()
public class RightAction implements IAction{

	@Override
	public IRestrictedGameData executeAction(IEntity player, IEntity npc, IEntityManager myEM, IRestrictedGameData currentGameData) {
		AccelerationComponent ac = (AccelerationComponent) player.getComponent(ComponentType.Acceleration);
		LocationComponent lc = (LocationComponent) player.getComponent(ComponentType.Location);
		
		VelocityComponent vc = (VelocityComponent) player.getComponent(ComponentType.Velocity);
		
		//lc.setX(lc.getX() +2);
		vc.setX(4);
		((IRestrictedEntity) player).changed(player);
		GameDataFactory gdf = new GameDataFactory();
		return gdf.blankEntityData(currentGameData);
	}

}
