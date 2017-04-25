package actions;

import components.entityComponents.ComponentType;
import components.entityComponents.ObjectCreationComponent;
import entity.IEntity;
import entity.IEntityManager;
import gamedata.GameDataFactory;
import gamedata.IRestrictedGameData;

public class Reload implements IAction {
	@Override
	public IRestrictedGameData executeAction(IEntity player, IEntity npc, IEntityManager myEM,
			IRestrictedGameData currentGameData) {
			ObjectCreationComponent occ = (ObjectCreationComponent) player.getComponent(ComponentType.ObjectCreation);
			if (occ!=null){
				occ.setCreating(true);
			}
			
			GameDataFactory gdf = new GameDataFactory();

			return gdf.blankEntityData(currentGameData);
	}
}
