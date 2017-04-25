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

@KeyAction
public class LeftAction implements IAction {
	@Override
	public IRestrictedGameData executeAction(IEntity player, IEntity npc, IEntityManager myEM, IRestrictedGameData currentGameData) {
		LocationComponent lc = (LocationComponent) player.getComponent(ComponentType.Location);
		AccelerationComponent ac = (AccelerationComponent) player.getComponent(ComponentType.Acceleration);
		VelocityComponent vc = (VelocityComponent) player.getComponent(ComponentType.Velocity);
		
		ac.setX(-0.5);
		if(lc.getX() < 55){
			stopPlayer(vc, ac);
		}
		
		((IRestrictedEntity) player).changed(player);
		GameDataFactory gdf = new GameDataFactory();
		return gdf.blankEntityData(currentGameData);
	}
	
	private void stopPlayer(VelocityComponent vc, AccelerationComponent ac){
		ac.setX(0);
		vc.setX(0);
	}

}
