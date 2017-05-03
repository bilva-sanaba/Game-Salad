package actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class LeftDamageAction extends AbstractAction implements IAction{

	@Override
	public IRestrictedGameData executeAction(IEntity player, IEntity npc, IEntityManager myEM,
			IRestrictedGameData currentGameData) {	
		ControllableComponent cc = (ControllableComponent) player.getComponent(ComponentType.Controllable);
		if(cc!=null &&cc.checkControl() == true){
			LocationComponent lc = (LocationComponent) player.getComponent(ComponentType.Location);
			VelocityComponent vc = (VelocityComponent) player.getComponent(ComponentType.Velocity);
			HealthComponent hc = (HealthComponent) player.getComponent(ComponentType.Health);
			AccelerationComponent ac = (AccelerationComponent) player.getComponent(ComponentType.Acceleration);
			TimeComponent tc = (TimeComponent) player.getComponent(ComponentType.Time);
			StrengthComponent sc = (StrengthComponent) npc.getComponent(ComponentType.Strength);
			lc.setX(lc.getX()-0.01);
			vc.setX(-15);
			ac.setX(0);
			int counter = 0;
			Map<Integer, String> collection = new HashMap<Integer, String>();
			collection.put(counter, "transparent.png");
			counter++;
			Map<Integer, String> collection2 = new HashMap<Integer, String>();
			collection2.put(counter,"mario_step3.gif");
			for(int i = 0; i < 600; i = i + 200){
				tc.addSingleAction(new ImageChangeAction(collection), new Integer(i));
				tc.addSingleAction(new ImageChangeAction(collection2), new Integer(i+100));
			}
			tc.addSingleAction(new RestoreControl(), 600);	
			if (hc!=null && sc!=null){
			hc.setHealth(hc.getHealth() - sc.getStrength());
			}
			cc.loseControl();
		}
		GameDataFactory gdf = new GameDataFactory();
		return gdf.blankEntityData(currentGameData);
	}

}
