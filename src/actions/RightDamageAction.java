package actions;

import components.entityComponents.ComponentType;
import components.entityComponents.LocationComponent;
import components.entityComponents.VelocityComponent;
import entity.IEntity;
import entity.IEntityManager;
import gamedata.GameDataFactory;
import gamedata.IRestrictedGameData;

public class RightDamageAction implements IAction{

	@Override
	public IRestrictedGameData executeAction(IEntity player, IEntity npc, IEntityManager myEM,
			IRestrictedGameData currentGameData) {
		
		LocationComponent lc = (LocationComponent) player.getComponent(ComponentType.Location);
		VelocityComponent vc = (VelocityComponent) player.getComponent(ComponentType.Velocity);
			
		lc.setX(lc.getX()+0.01);
			
		vc.setX(5);
		vc.setY(-1);
			
		GameDataFactory gdf = new GameDataFactory();
		
		return gdf.blankEntityData(currentGameData);
	}

}
