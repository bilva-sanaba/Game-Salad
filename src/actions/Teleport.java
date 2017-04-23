package actions;

import java.util.ArrayList;
import java.util.List;


import components.entityComponents.ComponentType;
import components.entityComponents.LocationComponent;
import entity.IEntity;
import entity.IEntityManager;
import gamedata.GameData;
import gamedata.GameDataFactory;
import gamedata.IRestrictedGameData;

public class Teleport implements IAction {
	private double teleportXLocation;
	private double teleportYLocation;
	
	public Teleport(double newX, double newY) {
		teleportXLocation = newX;
		teleportYLocation = newY;
	}

	@Override
	public IRestrictedGameData executeAction(IEntity player, IEntity npc, IEntityManager myEM, IRestrictedGameData currentGameData) {
		((LocationComponent) player.getComponent(ComponentType.Location)).setXY(teleportXLocation, teleportYLocation);
		player.changed(player);
		GameDataFactory gdf = new GameDataFactory();
		return gdf.blankEntityData(currentGameData);
	}

	

}
