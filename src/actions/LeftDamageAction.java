package actions;

import components.entityComponents.AccelerationComponent;
import components.entityComponents.ComponentType;
import components.entityComponents.HealthComponent;
import components.entityComponents.LocationComponent;
import components.entityComponents.StrengthComponent;
import components.entityComponents.VelocityComponent;
import entity.IEntity;
import entity.IEntityManager;
import gamedata.GameDataFactory;
import gamedata.IRestrictedGameData;

public class LeftDamageAction implements IAction{

	@Override
	public IRestrictedGameData executeAction(IEntity player, IEntity npc, IEntityManager myEM,
			IRestrictedGameData currentGameData) {
			
		LocationComponent lc = (LocationComponent) player.getComponent(ComponentType.Location);
		VelocityComponent vc = (VelocityComponent) player.getComponent(ComponentType.Velocity);
		HealthComponent hc = (HealthComponent) player.getComponent(ComponentType.Health);
		
		StrengthComponent sc = (StrengthComponent) npc.getComponent(ComponentType.Strength);
			
		lc.setX(lc.getX()-0.01);
			
		vc.setX(-5);
		vc.setY(-1);
		
		hc.setHealth(hc.getHealth() - sc.getStrength());
		
		GameDataFactory gdf = new GameDataFactory();
			
		return gdf.blankEntityData(currentGameData);
	}

}
