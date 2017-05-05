package components.keyExpressions;


import actions.IAction;
import class_annotations.KeyAction;
import components.entityComponents.AccelerationComponent;
import components.entityComponents.ComponentType;
import components.entityComponents.ControllableComponent;
import components.entityComponents.OrientationComponent;
import entity.IEntity;
import entity.IEntityManager;
import entity.restricted.IRestrictedEntity;
import gamedata.GameDataFactory;
import gamedata.IRestrictedGameData;

@KeyAction
/**
 * Action applicable for a key input as well as other engines
 * (Should be refactored to use input parameters but this could not be used by authoring)
 * @author Bilva
 *
 */

public class LeftAction implements IAction {
	@Override
	public IRestrictedGameData executeAction(IEntity player, IEntity npc, IEntityManager myEM, IRestrictedGameData currentGameData) {
		
		ControllableComponent cc = (ControllableComponent) player.getComponent(ComponentType.Controllable);
		
		if(cc.checkControl() == true){
			AccelerationComponent ac = (AccelerationComponent) player.getComponent(ComponentType.Acceleration);
			ac.setX(-0.2);
			if (player.getComponent(ComponentType.Orientation)!=null){
			OrientationComponent oc = (OrientationComponent) player.getComponent(ComponentType.Orientation);
			oc.setOrientation(180);
			}
		}
		
		((IRestrictedEntity) player).changed(player);
		GameDataFactory gdf = new GameDataFactory();
		return gdf.blankEntityData(currentGameData);
	}

}
