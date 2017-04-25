package components.keyExpressions;

import java.util.ArrayList;
import java.util.List;

import actions.IAction;
import class_annotations.KeyAction;

import components.entityComponents.ComponentType;
import components.entityComponents.LocationComponent;
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
		lc.setX(lc.getX()-5);
		((IRestrictedEntity) player).changed(player);
		GameDataFactory gdf = new GameDataFactory();
		return gdf.blankEntityData(currentGameData);
	}

}
