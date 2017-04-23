package actions;

import java.util.ArrayList;
import java.util.List;

import components.entityComponents.ComponentType;
import components.entityComponents.ObjectCreationComponent;
import entity.IEntity;
import entity.IEntityManager;
import entity.restricted.IRestrictedEntity;
import gamedata.GameData;
import gamedata.GameDataFactory;
import gamedata.IRestrictedGameData;

public class PowerupCreation implements IAction {
	@Override
	public IRestrictedGameData executeAction(IEntity e, IEntity e2, IEntityManager myEM, IRestrictedGameData currentGameData) {
		List<IEntity> entities = new ArrayList<IEntity>();
		ObjectCreationComponent occ = ((ObjectCreationComponent) e2.getComponent(ComponentType.ObjectCreation));
		GameDataFactory gdf = new GameDataFactory();
		GameData returnData = gdf.blankEntityData(currentGameData);
		if (occ.checkIfCreation()){
		
		IEntity powerup = occ.getCreationEffect();
		System.out.println(powerup.getID());
		if (powerup!=null){
			returnData.getRestrictedEntityManager().getRestrictedEntities().add((IRestrictedEntity) powerup);
		}
		}
		return returnData; 
	}
}
