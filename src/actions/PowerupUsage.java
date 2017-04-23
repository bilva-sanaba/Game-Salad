package actions;

import java.util.ArrayList;
import java.util.List;

import components.entityComponents.ComponentType;
import components.entityComponents.SpriteComponent;
import entity.IEntity;
import entity.IEntityManager;
import gamedata.GameDataFactory;
import gamedata.IRestrictedGameData;

public class PowerupUsage implements IAction{

	@Override
	public IRestrictedGameData executeAction(IEntity player, IEntity npc, IEntityManager myEM, IRestrictedGameData currentGameData) {
		System.out.println("remove this powerup");
		SpriteComponent npcsc = (SpriteComponent) npc.getComponent(new SpriteComponent());
		myEM.getEntities().remove(npc);
		((SpriteComponent) npc.getComponent(new SpriteComponent())).setClassPath("");
		npc.changed(null);
		
		GameDataFactory gdf = new GameDataFactory();
		return gdf.blankEntityData(currentGameData);
	}

}
