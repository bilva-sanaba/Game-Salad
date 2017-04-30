package actions;

import class_annotations.LeftAction;
import components.entityComponents.AccelerationComponent;
import components.entityComponents.ComponentType;
import components.entityComponents.ControllableComponent;
import components.entityComponents.DamagedComponent;
import components.entityComponents.HealthComponent;
import components.entityComponents.LocationComponent;
import components.entityComponents.StrengthComponent;
import components.entityComponents.TimeComponent;
import components.entityComponents.VelocityComponent;
import entity.IEntity;
import entity.IEntityManager;
import gamedata.GameDataFactory;
import gamedata.IRestrictedGameData;

@LeftAction()
public class LeftDamageAction implements IAction{

	@Override
	public IRestrictedGameData executeAction(IEntity player, IEntity npc, IEntityManager myEM,
			IRestrictedGameData currentGameData) {
			
		ControllableComponent cc = (ControllableComponent) player.getComponent(ComponentType.Controllable);
		LocationComponent lc = (LocationComponent) player.getComponent(ComponentType.Location);
		VelocityComponent vc = (VelocityComponent) player.getComponent(ComponentType.Velocity);
		HealthComponent hc = (HealthComponent) player.getComponent(ComponentType.Health);
		AccelerationComponent ac = (AccelerationComponent) player.getComponent(ComponentType.Acceleration);
		
		//TODO: WHEN BILVA EDITS TIME ENGINE CHANGE DamagedComponent dc = (DamagedComponent) player.getComponent(ComponentType.Damaged);
		
		StrengthComponent sc = (StrengthComponent) npc.getComponent(ComponentType.Strength);
			
		lc.setX(lc.getX()-0.01);
			
		vc.setX(-15);
		vc.setY(-2);
		ac.setX(0);
				
		hc.setHealth(hc.getHealth() - sc.getStrength());
		System.out.println(hc.getHealth());
		cc.loseControl();
		
		GameDataFactory gdf = new GameDataFactory();
			
		return gdf.blankEntityData(currentGameData);
	}

}
