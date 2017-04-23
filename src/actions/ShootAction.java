package actions;

import java.util.ArrayList;
import java.util.List;


import components.entityComponents.ComponentType;
import components.entityComponents.LocationComponent;
import components.entityComponents.ObjectCreationComponent;
import entity.IEntity;
import entity.IEntityManager;
import entity.restricted.IRestrictedEntity;
import gamedata.GameData;
import gamedata.GameDataFactory;
import gamedata.IRestrictedGameData;

public class ShootAction implements IAction {

	@Override
	public IRestrictedGameData executeAction(IEntity player, IEntity npc, IEntityManager myEM, IRestrictedGameData currentGameData) {
		ObjectCreationComponent occ = (ObjectCreationComponent) player.getComponent(ComponentType.ObjectCreation);
		npc = occ.checkCreationEffect(); 
		LocationComponent lcplayer= (LocationComponent) player.getComponent(ComponentType.Location);
		LocationComponent lcnpc= (LocationComponent) npc.getComponent(ComponentType.Location);
		lcnpc.setX(lcplayer.getX()+60);
		lcnpc.setY(lcplayer.getY());
		occ.setEntity(npc.newCopy());
		List<IEntity> entities = new ArrayList<IEntity>();
		
		GameDataFactory gdf = new GameDataFactory();
		GameData returnData = gdf.blankEntityData(currentGameData);
		if (npc!=null){
			returnData.getRestrictedEntityManager().getRestrictedEntities().add((IRestrictedEntity) npc);
		}
		return returnData;
	}

}
